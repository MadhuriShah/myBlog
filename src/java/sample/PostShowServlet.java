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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //response.setHeader("Content-Type", "text/plain-text");
        try (PrintWriter out = response.getWriter()) {
            if (!request.getParameterNames().hasMoreElements()) {
                out.println(getResults("SELECT * FROM post"));
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                out.println(getResults("SELECT * FROM post WHERE p_id = ?", String.valueOf(id)));
            }
        } catch (IOException ex) {
            Logger.getLogger(PostShowServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private String getResults(String query, String... params) {
        StringBuilder sb = new StringBuilder();
        try (Connection cn = connection.myConnection.getConnection()) {
            PreparedStatement pstmt = cn.prepareStatement(query);
            for (int i = 1; i <= params.length; i++) {
                pstmt.setString(i, params[i - 1]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                sb.append(String.format("%s\t%s\t%s\t%s\t%s\n", rs.getInt("p_id"), rs.getString("title"), rs.getString("description"), rs.getString("date"), rs.getInt("category")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostShowServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

}
