/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Repository.Repository;
import java.sql.ResultSet;

/**
 *
 * @author Trivico
 */
public class Product {

    String name;
    int quantity = 1;
    String id;
    protected int refId;
    protected double price;
    protected double poPrice;

    ProductSQLGenerator sqlGenerator;
    ProductGenerator productGenerator;
    private ProductType type;

    public Product(ProductType type) {
        this.type = type;
        this.sqlGenerator = new ProductSQLGenerator(this);
        this.productGenerator = new ProductGenerator(this);
    }

    public Product(String id, String name, double price, double poPrice, ProductType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.poPrice = poPrice;
        this.type = type;
        this.sqlGenerator = new ProductSQLGenerator(this);
        this.productGenerator = new ProductGenerator(this);
    }

    public int getRefId() {
        return refId;
    }

    public double price() {
        return (type == ProductType.PO) ? poPrice :price;
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

    public Product find(String id) {
        String findSQL = getFindSQL(id);
        ResultSet resultSet = Repository.getResultSet(findSQL);
        return productGenerator.createProduct(resultSet);
    }
    public void update() {
        String updateSQL = sqlGenerator.getUpdateSQL();
        Repository.executeUpdate(updateSQL);
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

    public void setRefId(int id) {
        this.refId = id;
    }





    public ProductType getType() {
        return type;
    }

}
