/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Repository.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import minipos.MiniPOS;

/**
 *
 * @author loma
 */
public class SaleProduct extends Product {

    public SaleProduct(String id, String name, double price, double poPrice) {
        super(id, name, price, poPrice);
    }

    public SaleProduct() {
    }
    public void update(int saleId) {
        this.saleId = saleId;
        this.update();
    }

    private String getUpdateSQL() {
        return String.format(
            "update sale_details set quantity=%d, price=%f "
                + "where sale_id=%d and product_id='%s'; ",
            getQuantity(),
            price(),
            getSaleId(),
            getId()
        );
    }
}
