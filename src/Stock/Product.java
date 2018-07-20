/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Repository.Repository;

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
    private int saleId;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.repo = new Repository();
    }

    public int saleId() {
        return saleId;
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
        Repository.deleteProduct(this);
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

    public void increaseQuantity(int i) {
        this.quantity += i;
    }

    public void decreaseQuantity(int i) {
        this.quantity -= i;
    }

    public void save(int saleId) {
        this.saleId = saleId;
        Repository.insertNewSaleDetails(this);
    }

    public void update(int saleId) {
        this.saleId = saleId;
        Repository.updateSaleDetails(this);
    }
}
