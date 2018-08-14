/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Repository.Repository;
import Sale.Order;
import Product.Product;
import Sale.OrderType;
import Sale.OrderSQLGenerator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author loma
 */
public class PurchasedOrder extends Order {

    OrderSQLGenerator sql = new OrderSQLGenerator(OrderType.PO);
    public PurchasedOrder(){
        sql.setOrder(this);
    }
    public void save() {
        if (this.id > 0) {
            String query = sql.getUpdateSQL();
            Repository.executeUpdate(query);
            for(Product p: products){
                if (p.getPOId() > 0) {
                    p.update();
                    p.setPOId(this.id);
                } else {
                    p.setPOId(this.id);
                    p.save(this.id);
                    p.increaseQuantity();
                }
            }
        } else {
            String insertSQL = sql.getInsertSQL();
            int saleId = Repository.executeUpdateWithLastId(insertSQL);

            this.id = saleId;
            if (saleId > 0)
                for(Product p: products){
                    p.setPOId(saleId);
                    p.save(saleId);
                    p.increaseQuantity();
                }
        }
    }

}
