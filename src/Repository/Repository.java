/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Config.MiniPOSConfig;
import Stock.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author loma
 */
public class Repository {

    static Connection connection;

    public static void updateProduct(Product product) {
        String query = String.format(
            "update products set name='%s', price=%f where id ='%s';", 
            product.name(), 
            product.price(),
            product.id()
        );
        executeUpdate(query);
    }

    public static void deleteProduct(Product product) {
        String query = String.format("delete from products where id='%s'", product.id());
        executeUpdate(query);
    }
    public Repository(){
        getConnection();
    }

    public void insertNewProduct(Product product) {
        String query = String.format(
            "insert into products values('%s', '%s', %f);", 
            product.id(), 
            product.name(), 
            product.price()
        );
        executeUpdate(query);
        
    }

    public static void executeUpdate(String query) {
        try (Statement stmt = connection.createStatement()) {
            int result = stmt.executeUpdate(query);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        if (connection != null) return connection;

        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            String driverName = "com.mysql.jdbc.Driver";
            // Create a connection to the database
            String serverName = "192.168.0.99";
            String schema = MiniPOSConfig.DB_NAME +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String url = "jdbc:mysql://" + serverName +  "/" + schema;
            String username = "mini";
            String password = "mini";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully Connected to the database!");
            this.connection = connection;
            return connection;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return null;
    }

    public static Product findProduct(String id) {
        String query = String.format("select * from products where id='%s';", id);
        
        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String productId = rs.getString("id");
                double price = rs.getDouble("price");
                return new Product(id, name, price);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
