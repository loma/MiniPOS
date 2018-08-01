/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Repository.Repository;
import Stock.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loma
 */
public class POProduct extends Product {

    private int poId;
    
    public POProduct(String id, String name, double price, double poPrice) {
        super(id, name, price, poPrice);
    }

    public double price() {
        return poPrice;
    }

    public static POProduct find(String id) {
        String findSQL = getFindSQL(id);
        ResultSet resultSet = Repository.getResultSet(findSQL);
        return createProduct(resultSet);
    }

    private static POProduct createProduct(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String productId = resultSet.getString("id");
                double price = resultSet.getDouble("price");
                double poPrice = resultSet.getDouble("purchased_price");
                return new POProduct(productId, name, price, poPrice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void save(int poId) {
        this.poId = poId;
        String insertSQL = getInsertDetailSQL();
        Repository.executeUpdate(insertSQL);
    }

    public int poId() {
        return this.poId;
    }

    private String getInsertDetailSQL() {
        return String.format(
            "insert into purchased_order_details (purchased_order_id, product_id, quantity, price) "
                + "values(%d, '%s', %d, %f);",
            poId(),
            id(), 
            quantity(),
            price()
        );
    }
}
