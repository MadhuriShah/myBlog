/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author c0647610
 */
public class test {
    
    public static void main (String args[]) throws SQLException{
        Connection cn1 =connection.myConnection.getConnection();
        boolean check= cn1.isValid(6);
        System.out.println(check);
    }
    
}
