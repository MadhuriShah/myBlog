/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author c0647610
 */
@WebServlet("/show")
public class PostShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //response.setHeader("Content-Type", "text/plain-text");
         //response.setHeader("Content-Type", "text/plain-text");
        String s="helloworld";
        int id=0;
         PreparedStatement pstmt=null;
        try (PrintWriter out = response.getWriter()) {
             try (Connection cn = connection.myConnection.getConnection()) {
            if (!request.getParameterNames().hasMoreElements()) {
                s="SELECT * FROM post";
                 pstmt = cn.prepareStatement(s);
            } else {
                 id = Integer.parseInt(request.getParameter("id"));
                s="SELECT * FROM post WHERE p_id = ?";
                  pstmt = cn.prepareStatement(s);
                 pstmt.setInt(1,id);
            }
           
            
           
            
            ResultSet rs = pstmt.executeQuery();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>    <link href=\"https://bootswatch.com/flatly/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<title>My Blog</title>");            
            out.println("</head>");
            out.println("<body> <div class=\"container\"> <p align=right><a href=\"faces/login.xhtml\" >Login</a></p><h2>Welcome to My Blog</h2> &nbsp;&nbsp;<div class=\" form-inline form-group panel panel-info\">");
           
            while (rs.next()) {
            out.println("<div class=\"panel-heading\">" +rs.getString("title"));
            out.println(" <small><em>" +rs.getString("date") + "</em></small></div>");
            out.println("<div class=\"panel-body\"><p>" +rs.getString("description") + "</p></div>");
	
           
        }
            out.println("</div></div></body>");
            out.println("</html>");     
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostShowServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

}
