/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author c0647610
 */
@ManagedBean(name="user")
@SessionScoped

public class loginBean {
    private String username;
    private String pass;
    private boolean loggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String password) {
        this.pass = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

   
    public void doLogin() {
        
        Connection cn=connection.myConnection.getConnection();
         PreparedStatement stmt=null;
         String pass1=null;
        String query="select password from login where username=?";
        try {
            stmt=cn.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
             pass1=rs.getString("password");
           // System.out.println(pass1);  
            }   
            
        }    
        catch (SQLException ex) {
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pass.equals(pass1)){
            loggedIn=true;
        }
        else{
            loggedIn=false;
        }
    }
    
}