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
        Repository.updateProduct(this);
    }
    public static Product find(String id) {
        return Repository.findProduct(id);
    }
}
