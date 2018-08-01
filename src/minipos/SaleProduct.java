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
public class SaleProduct extends Product {

    public SaleProduct(String id, String name, double price, double poPrice) {
        super(id, name, price, poPrice);
    }

    public static SaleProduct find(String id) {
        String findSQL = getFindSQL(id);
        ResultSet resultSet = Repository.getResultSet(findSQL);
        return createProduct(resultSet);
    }
    /*
    private static String getFindSQL(String id) {
        return String.format("select * from products where id='%s';", id);
    }
*/
    private static SaleProduct createProduct(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String productId = resultSet.getString("id");
                double price = resultSet.getDouble("price");
                double poPrice = resultSet.getDouble("purchased_price");
                return new SaleProduct(productId, name, price, poPrice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
