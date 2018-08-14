/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

import Repository.Repository;
import Product.Product;
import Product.ProductType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import minipos.MiniPOS;

/**
 *
 * @author Keo
 */
public class Sale extends Order {

    OrderSQLGenerator sql = new OrderSQLGenerator(OrderType.SALE);

    public Sale() {
    }

    public Sale(int id, double discount, double vat, double payment, int status, Date saleOn, String saleBy) {
        this.id = id;
        this.discount = discount;
        this.VAT = vat;
        this.payment.add((int)payment);
        this.status = SaleStatus.values()[status];
        this.saleOn = saleOn;
        this.saleBy = saleBy;

        // temporary
        sql.setOrder(this);
    }

    public static Sale find(int id) {
        String query = String.format("select * from sales where id=%d;", id);

        ResultSet rs = Repository.getResultSet(query);
        try {
            while (rs.next()) {
                double payment  = rs.getDouble("paid");
                double discount  = rs.getDouble("discount");
                double vat  = rs.getDouble("vat");
                int status  = rs.getInt("status");
                Date saleOn  = rs.getDate("sale_on");
                String saleBy  = rs.getString("sale_by");
                Sale sale = new Sale(id, discount, vat, payment, status, saleOn, saleBy);

                sale.addProducts(findBySaleId(id));

                return sale;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static ArrayList<Product> findBySaleId(int id) {
        String query = String.format("select * from sale_details where sale_id=%d;", id);
        
        ResultSet rs = Repository.getResultSet(query);
        try {
            ArrayList<Product> returnProducts = new ArrayList<Product>();
            while (rs.next()) {

                String productId = rs.getString("product_id");
                
                Product product = MiniPOS.findProduct(productId, ProductType.SALE);
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

    @Override
    public void save() {
        if (this.id > 0) {
            String query = sql.getUpdateSQL();
            Repository.executeUpdate(query);
            for(Product p: products){
                if (p.getSaleId() > 0) {
                    p.update();
                    p.setSaleId(this.id);
                } else {
                    p.setSaleId(this.id);
                    p.save(this.id);
                    p.decreaseQuantity();
                }
            }
        } else {
            String insertSQL = sql.getInsertSQL();
            int saleId = Repository.executeUpdateWithLastId(insertSQL);

            this.id = saleId;
            if (saleId > 0)
                for(Product p: products){
                    p.setSaleId(saleId);
                    p.save(saleId);
                    p.decreaseQuantity();
                }
        }
    }

}
