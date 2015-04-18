/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author c0647610
 */
@Path("insert")
public class insertPost {

    @GET
    public String getAll() {
        return "Hello World";
    }

    @POST
    @Consumes("application/json")
    public void postData(String str) {

        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        String title = json.getString("title");
        String description = json.getString("description");
        String category = null;
        doUpdate("INSERT INTO post(title,description, c_id) VALUES (?, ?, ?)", title, description, category);
    }

    public int doUpdate(String query, String... params) {
        int changes = 0;
        try (Connection cn = connection.myConnection.getConnection()) {
            PreparedStatement pstmt = cn.prepareStatement(query);
            for (int i = 1; i <= params.length; i++) {
                pstmt.setString(i, params[i - 1]);
            }
            changes = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(insertPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return changes;
    }
}
