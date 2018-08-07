/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loma
 */
public class ProductGenerator {

    private final Product product;

    public ProductGenerator(Product p) {
        this.product = p;
    }
    public Product createProduct(ResultSet resultSet) {
        switch(product.getType()){
            case PO:
            case SALE:
            case PRODUCT:
                return createProduct(resultSet, product.getType());
        }
        return null;
    }

    private Product createProduct(ResultSet resultSet, ProductType type) {
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String productId = resultSet.getString("id");
                double price = resultSet.getDouble("price");
                double poPrice = resultSet.getDouble("purchased_price");
                return new Product(productId, name, price, poPrice, type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
