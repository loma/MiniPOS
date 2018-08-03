/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

import Repository.Repository;
import Stock.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loma
 */
public class SaleProduct extends Product {

    public static ArrayList<Product> findBySaleId(int id) {
        String query = String.format("select * from sale_details where sale_id=%d;", id);
        
        ResultSet rs = Repository.getResultSet(query);
        try {
            ArrayList<Product> returnProducts = new ArrayList<Product>();
            while (rs.next()) {

                String productId = rs.getString("product_id");
                SaleProduct product = SaleProduct.find(productId);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setSaleId(id);
                returnProducts.add(product);
            }
            return returnProducts;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public SaleProduct(String id, String name, double price, double poPrice) {
        super(id, name, price, poPrice);
    }

    public static SaleProduct find(String id) {
        String findSQL = getFindSQL(id);
        ResultSet resultSet = Repository.getResultSet(findSQL);
        return createProduct(resultSet);
    }

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

    public void save(int saleId) {
        this.saleId = saleId;
        String insertSQL = getInsertSQL();
        Repository.executeUpdate(insertSQL);
        decreaseQuantity();
    }

    public void update(int saleId) {
        this.saleId = saleId;
        this.update();
    }

    private String getUpdateSQL() {
        return String.format(
            "update sale_details set quantity=%d, price=%f "
                + "where sale_id=%d and product_id='%s'; ",
            quantity(),
            price(),
            saleId(),
            id()
        );
    }

    private String getInsertSQL() {
        return String.format(
            "insert into sale_details (sale_id, product_id, quantity, price) "
                + "values(%d, '%s', %d, %f);",
            saleId(),
            id(), 
            quantity(),
            price()
        );
    }

    private void decreaseQuantity() {
        String query = "update products set quantity = quantity-"+this.quantity()+" where id='"+ this.id() +"';";
        Repository.executeUpdate(query);
    }
}
