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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author c0647610
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        try (PrintWriter out = response.getWriter()) {
            if (keySet.contains("username") && keySet.contains("password")) {
                Connection cn = connection.myConnection.getConnection();
                String username = request.getParameter("username");
                String pass = request.getParameter("password");
                String query = "SELECT username, password FROM LOGIN WHERE username = ?";
                PreparedStatement pstmt = cn.prepareStatement(query);
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                String password1 = "";
                 String username1 = "";

                boolean loggedIn= false;
                while (rs.next()) {
                    password1 = rs.getString("password");
                    username1 = rs.getString("username");
                }
                loggedIn = pass.equals(password1);
                System.out.println(loggedIn);
                if (loggedIn) {
                    username1 = username;
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("loggedIn", loggedIn);
                    response.sendRedirect("EditDeletePost.jsp");
                }
                else{
                    response.sendRedirect("show");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
}
