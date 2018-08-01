/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Repository.Repository;
import Stock.Product;

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
        return Repository.findPOProduct(id);
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
