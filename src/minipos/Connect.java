/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Keo
 */
public class Connect {
          public static Connection getConnection(){
        try {
            String sql = "jdbc:sqlserver://KEO\\SQLEXPRESS; databaseName =DBsale";
            String user = "sa";
            String pas = "Keo123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection(sql, user, pas);
            return c;  
           
        } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        }
        return null;
      }
}
