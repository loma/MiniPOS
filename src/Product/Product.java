/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Repository.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trivico
 */
public class Product {

    String name;
    int quantity = 1;

    String id;
    protected int saleId;
    protected int poId;

    protected double price;
    protected double poPrice;

    SQLGenerator sqlGenerator;
    ProductGenerator productGenerator;

    public Product(String id, String name, double price, double poPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.poPrice = poPrice;
    }

    public Product() {
    }

    public int getSaleId() {
        return saleId;
    }
    public int getPOId() {
        return poId;
    }

    public double price() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setPrice(double i) {
        this.price =i;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int getQuantity() {
        return quantity;
    }

    public void delete() {
        Repository.deleteProduct(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    
    protected static String getFindSQL(String id) {
        return String.format("select * from products where id='%s';", id);
    }

    public void setProductGenerator(ProductGenerator generator){
        this.productGenerator = generator;
    }
    public Product find(String id) {
        String findSQL = getFindSQL(id);
        ResultSet resultSet = Repository.getResultSet(findSQL);
        return productGenerator.createProduct(resultSet);
    }
    private String getUpdateSQL() {
        return String.format("update products set name='%s', price=%f where id ='%s';", 
            getName(), 
            price(),
            getId()
        );
    }
    public void update() {
        String updateSQL = getUpdateSQL();
        Repository.executeUpdate(updateSQL);
    }

    public void setSQLGenerator(SQLGenerator generator){
        this.sqlGenerator = generator;
    }
    public void save(int id) {
        String insertSQL = sqlGenerator.getInsertSQL();
        Repository.executeUpdate(insertSQL);
    }




    public void increaseQuantity(int i) {
        this.quantity += i;
    }

    public void decreaseQuantity(int i) {
        this.quantity -= i;
    }


    public void update(int saleId) {
    }

    public void setSaleId(int id) {
        this.saleId = id;
    }
    public void setPOId(int id) {
        this.poId = id;
    }

    public void decreaseQuantity() {
        String query = "update products set quantity = quantity-" + this.getQuantity() + " where id='" + this.getId() + "';";
        Repository.executeUpdate(query);
    }

    public void increaseQuantity() {
        String query = "update products set quantity=quantity +" + this.getQuantity() + " where id='" + this.getId() + "';";
        Repository.executeUpdate(query);
    }



}
