/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Repository.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Trivico
 */
public class Product {

    String id;
    String name;
    double price;
    int quantity = 1;
    private final Repository repo;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.repo = new Repository();
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

    public void setPrice(double i) {
        this.price =i;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int quantity() {
        return quantity;
    }

    public void save() {
        repo.insertNewProduct(this);
    }

    public void delete() {
        String query = String.format("delete from products where id='%s'", id);
        repo.executeUpdate(query);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update() {
        String query = String.format("update products set name='%s', price=%f where id='%s';", name, price, id);
        repo.executeUpdate(query);
    }
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
}
