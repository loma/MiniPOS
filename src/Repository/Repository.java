/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Config.MiniPOSConfig;
import Sale.Sale;
import Stock.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minipos.POProduct;
import minipos.PurchasedOrder;
import minipos.SaleProduct;
import minipos.User;

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

    public static boolean checkUsernamePassword(String username, String password) {
        try {
            CallableStatement cs = getConnection().prepareCall(
                "{call login(?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            ResultSet rs = cs.executeQuery();
            rs.last();
            return rs.getRow() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static List<User> getAllUsers() {

        String query = String.format("select id, username, role from users;");
        
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            List<User> users = new ArrayList<User>(); 
            while (rs.next()) {
                String name = rs.getString("username");
                int id = rs.getInt("id");
                int role = rs.getInt("role");
                users.add(new User(id, name, role));
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        
        return new ArrayList<User>();
    }

    public static void insertNewUser(User user) {
        String query = String.format(
            "insert into users (id, username, password, role) "
                + "values(%d, '%s', '%s', %d);",
            user.getId(),
            user.getName(), 
            user.getPassword(),
            user.getRole().ordinal()
        );
        executeUpdate(query);
    }

    public static User findUser(int id) {
        String query = String.format("select * from users where id=%d;", id);
        
        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("username");
                int userId = rs.getInt("id");
                int role = rs.getInt("role");
                return new User(userId, name, role);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void deleteUser(User user) {
        String query = String.format("delete from users where id=%d;", user.getId());
        executeUpdate(query);
    }

    public static void updateUser(User user) {
        String query = String.format(
            "update users set username='%s', password='%s', role=%d where id=%d;",
            user.getName(), 
            user.getPassword(),
            user.getRole().ordinal(),
            user.getId()
        );
        executeUpdate(query);
    }

    public static int executeUpdateWithLastId(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static void updateSale(Sale sale) {
        String query = String.format(
            "update sales set total=%f, discount=%f, paid=%f "
                + " where id=%d;", 
            sale.getTotalPrice(), 
            sale.getTotalDiscount(), 
            sale.getTotalPayment(),
            sale.id()
        );
        executeUpdate(query);
    }

    public static void updateSaleDetails(Product product) {
        String query = String.format(
            "update sale_details set quantity=%d, price=%f "
                + "where sale_id=%d and product_id='%s'; ",
            product.quantity(),
            product.price(),
            product.saleId(),
            product.id()
        );
        executeUpdate(query);
    }

    public static Sale findSale(int id) {
        String query = String.format("select * from sales where id=%d;", id);
        
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                double payment  = rs.getDouble("paid");
                double discount  = rs.getDouble("discount");
                double vat  = rs.getDouble("vat");
                int status  = rs.getInt("status");
                Date saleOn  = rs.getDate("sale_on");
                String saleBy  = rs.getString("sale_by");
                return new Sale(id, discount, vat, payment, status, saleOn, saleBy);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<SaleProduct> findProductsBySaleId(int id) {
        String query = String.format("select * from sale_details where sale_id=%d;", id);
        
        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            ArrayList<SaleProduct> returnProducts = new ArrayList<SaleProduct>();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                SaleProduct product = SaleProduct.find(productId);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setSaleId(id);
                returnProducts.add(product);
            }
            return returnProducts;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ResultSet getResultSet(String query) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Repository(){
        getConnection();
    }

    public static void executeUpdate(String query) {
        try (Statement stmt = connection.createStatement()) {
            int result = stmt.executeUpdate(query);
            System.out.println(result + " records updates!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
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
            Repository.connection = connection;
            return connection;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return null;
    }

}
