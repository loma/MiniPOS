/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

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

    protected static String getFindSQL(String id) {
        return String.format("select * from products where id='%s';", id);
    }

    private static Product createProduct(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String productId = resultSet.getString("id");
                double price = resultSet.getDouble("price");
                double poPrice = resultSet.getDouble("purchased_price");
                return new Product(productId, name, price, poPrice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    String id;
    String name;
    double price;
    int quantity = 1;
    private final Repository repo;
    protected int saleId;
    protected int poId;
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
    public int poId() {
        return poId;
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
    }


    public void delete() {
        Repository.deleteProduct(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public static Product find(String id) {
        String findSQL = getFindSQL(id);
        ResultSet resultSet = Repository.getResultSet(findSQL);
        return createProduct(resultSet);
    }

    public void increaseQuantity(int i) {
        this.quantity += i;
    }

    public void decreaseQuantity(int i) {
        this.quantity -= i;
    }

    public void update() {
        String updateSQL = getUpdateSQL();
        Repository.executeUpdate(updateSQL);
    }

    public void update(int saleId) {
    }

    public void setSaleId(int id) {
        this.saleId = id;
    }
    public void setPOId(int id) {
        this.poId = id;
    }

    private String getInsertSQL() {
        return String.format(
            "insert into products (id, name, price, purchased_price, quantity) values('%s', '%s', %f, %f, 0);", 
            id(), 
            name(), 
            price(),
            this.poPrice
        );
    }

    private String getUpdateSQL() {
        return String.format(
            "update products set name='%s', price=%f where id ='%s';", 
            name(), 
            price(),
            id()
        );
    }

}
