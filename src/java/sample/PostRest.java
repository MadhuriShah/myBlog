/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import connection.myConnection;
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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0647610
 */
@Path("insert")
public class PostRest {

    @GET
    @Produces("application/json")
    public Response get() {
        return Response.ok(getResults("select * from post")).build();
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") int id) {
        return Response.ok(getResults("SELECT * FROM post WHERE p_id = ?", String.valueOf(id))).build();
    }
    @POST
    @Consumes("application/json")
    public Response postData(String str) {

        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        String title = json.getString("title");
        String description = json.getString("description");
        int category = 1;
        doUpdate("INSERT INTO post(title,description, category,date) VALUES (?, ?, ?, NOW())", title, description, String.valueOf(category));
                 return Response.ok(getResults("select * from post")).build();

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
                        .add("p_id", rs.getInt("p_id"))
                        .add("title", rs.getString("title"))
                        .add("description", rs.getString("description"))
                        .add("date", rs.getDate("date").toString())
                        .add("category", rs.getInt("category"))
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
    public Response remove(@PathParam("id") int id) throws Exception {
        int result = doUpdate("Delete from post where p_id=?", String.valueOf(id));
          return Response.ok(getResults("select * from post")).build();

    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response putData(String str, @PathParam("id") int id){
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        String name = json.getString("title");
        String description = json.getString("description");
       
        doUpdate("UPDATE post SET title= ?, description = ?, date = NOW(), category = 0 WHERE p_id = ?", name, description,String.valueOf(id));
         return Response.ok(getResults("select * from post")).build();
    }
}
