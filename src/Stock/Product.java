/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Trivico
 */
public class Product {

    public static Product find(Connection connection, String id) {
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
    String id;
    String name;
    double price;
    int quantity = 1;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double price() {
        return price;
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }

    public void setPrice(int i) {
        this.price =i;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int quantity() {
        return quantity;
    }

    public void save(Connection connection) {
        String query = String.format("insert into products values('%s', '%s', %f);", id, name, price);
        try (Statement stmt = connection.createStatement()) {
            int result = stmt.executeUpdate(query);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Connection connection) {
        String query = String.format("delete from products where id='%s'", id);
        try (Statement stmt = connection.createStatement()) {
            int result = stmt.executeUpdate(query);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
