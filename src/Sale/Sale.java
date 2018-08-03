/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

import Product.SaleProduct;
import Repository.Repository;
import Product.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Product.POProduct;
import minipos.MiniPOS;

/**
 *
 * @author Keo
 */
public class Sale extends Order {

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
                
                Product product = MiniPOS.findSaleProduct(productId);
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


    private String getInsertSQL() {
        return String.format(
            "insert into sales (total, discount, paid, vat, sale_on, sale_by, status) "
                + "values(%f, %f, %f, %f, '%s', '%s', %d);", 
            getTotalPrice(), 
            getTotalDiscount(), 
            getTotalPayment(),
            VAT(),
            getSaleOnString(),
            getSaleBy(),
            getStatus().ordinal()
        );
    }

    public void save() {
        if (this.id > 0) {
            String query = getUpdateSQL();
            Repository.executeUpdate(query);
            for(Product p: products){
                if (p.getSaleId() > 0)
                    p.update(this.id);
                else {
                    p.setSaleId(this.id);
                    p.save(this.id);
                    p.decreaseQuantity();
                }
            }
        } else {
            String insertSQL = getInsertSQL();
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

    private String getUpdateSQL() {
        return String.format(
            "update sales set total=%f, discount=%f, paid=%f "
                + " where id=%d;", 
            getTotalPrice(), 
            getTotalDiscount(), 
            getTotalPayment(),
            getId()
        );
    }
}
