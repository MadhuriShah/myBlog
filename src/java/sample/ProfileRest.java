/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0647610
 */
@Path("Profile")
public class ProfileRest {
     @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(getResults("select * from user")).build();
    }

    @POST
    @Consumes("application/json")
    public void postData(String str) {

        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        String first = json.getString("FirstName");
        String last = json.getString("LastName");
        String image="image";
         String email=json.getString("email");
          String phone=json.getString("phone");
           String description=json.getString("description");
            String username="user";
               
        String category = null;
        doUpdate("INSERT INTO user(FirstName,LastName, imageUrl,email,phone,description,username) VALUES (?, ?, ?, ?, ?, ?, ?)", first,last,image,email,phone,description,username);
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
            Logger.getLogger(PostRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return changes;
    }

    public static JsonArray getResults(String sql, String... params) {
        JsonArray json = null;
        try {
            JsonArrayBuilder array = Json.createArrayBuilder();
            Connection conn = connection.myConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                array.add(Json.createObjectBuilder()
                        .add("id",rs.getInt("id"))
                        .add("FirstName", rs.getString("FirstName"))
                        .add("LastName", rs.getString("LastName"))
                        .add("image", rs.getString("imageUrl"))
                        .add("email", rs.getString("email"))
                        .add("phone", rs.getString("phone"))
                         .add("description", rs.getString("description"))
                         .add("username", rs.getString("username"))
                        .build());
            }
            conn.close();
            json = array.build();
        } catch (SQLException ex) {
            Logger.getLogger(PostRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") int id) throws Exception {
        int result = doUpdate("Delete from user where id=?", String.valueOf(id));
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void putData(String str, @PathParam("id") int id){
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
       String first = json.getString("FirstName");
        String last = json.getString("LastName");
        String image=json.getString("imageUrl");
         String email=json.getString("email");
          String phone=json.getString("phone");
           String description=json.getString("description");
            String username=json.getString("username");
       
        doUpdate("UPDATE user SET FirstName= ?, LastName = ?, imageUrl = ?, email = ?,phone = ?, description = ?, username = ?, WHERE id = ?", first, last,image,email,phone,description,username,String.valueOf(id));
    }
    
}
