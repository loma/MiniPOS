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
    protected final double poPrice;


    public Product(String id, String name, double price, double poPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.poPrice = poPrice;
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
        String insertSQL = getInsertSQL();
        Repository.executeUpdate(insertSQL);
    }

    public void save(int saleId) {
        this.saleId = saleId;
        String insertSQL = getInsertDetailSQL();
        Repository.executeUpdate(insertSQL);
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

    public void update(int saleId) {
        this.saleId = saleId;
        Repository.updateSaleDetails(this);
    }

    public void setSaleId(int id) {
        this.saleId = id;
    }

    private String getInsertSQL() {
        return String.format(
            "insert into products values('%s', '%s', %f);", 
            id(), 
            name(), 
            price()
        );
    }

    private String getInsertDetailSQL() {
        return String.format(
            "insert into sale_details (sale_id, product_id, quantity, price) "
                + "values(%d, '%s', %d, %f);",
            saleId(),
            id(), 
            quantity(),
            price()
        );
    }
}
