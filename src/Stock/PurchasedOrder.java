/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Repository.Repository;
import Sale.Order;
import Stock.Product;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author loma
 */
public class PurchasedOrder extends Order {
    public void save() {
        if (this.id > 0) {
            String query = getUpdateSQL();
            Repository.executeUpdate(query);
            for(Product p: products){
                if (p.poId() > 0)
                    p.update(this.id);
                else
                    p.save(this.id);
            }
        } else {
            String insertSQL = getInsertSQL();
            int saleId = Repository.executeUpdateWithLastId(insertSQL);

            this.id = saleId;
            if (saleId > 0)
                for(Product p: products){
                    p.save(saleId);
                }
        }
    }

    private String getUpdateSQL() {
        return String.format(
            "update purchased_order set total=%f "
                + " where id=%d;", 
            getTotalPrice(), 
            getId()
        );
    }

    private String getInsertSQL() {
        return String.format(
            "insert into purchased_order (total, purchased_on, purchased_by) "
                + "values(%f, '%s', '%s');", 
            getTotalPrice(), 
            getSaleOnString(),
            getSaleBy()
        );
    }
}
