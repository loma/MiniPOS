/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

import Product.Product;

/**
 *
 * @author loma
 */
public class OrderSQLGenerator {

    private final OrderType type;
    private Order order;

    public OrderSQLGenerator(OrderType type) {
        this.type = type;
    }

    public String getUpdateQuantitySQL(Product p) {
        switch(this.type){
            case PO:
                return getUpdateQuantitySQLPO(p);
            case SALE:
                return getUpdateQuantitySQLSale(p);
        }
        return "";
    }

    private String getUpdateQuantitySQLPO(Product p) {
        String query = "update products set quantity=quantity + " + p.getQuantity() + " where id='" + p.getId() + "';";
        return query;
    }


    private String getUpdateQuantitySQLSale(Product p) {
        String query = "update products set quantity=quantity - " + p.getQuantity() + " where id='" + p.getId() + "';";
        return query;
    }

    public String getInsertSQL() {
        switch(this.type){
            case PO:
                return getInsertSQLPO();
            case SALE:
                return getInsertSQLSale();
        }
        return "";
    }

    public String getUpdateSQL() {
        switch(this.type){
            case PO:
                return getUpdateSQLPO();
            case SALE:
                return getUpdateSQLSale();
        }
        return "";
    }
    private String getUpdateSQLPO() {
        return String.format(
            "update purchased_order set total=%f "
                + " where id=%d;", 
            order.getTotalPrice(), 
            order.getId()
        );
    }
    private String getUpdateSQLSale() {
        return String.format(
            "update sales set total=%f, discount=%f, paid=%f "
                + " where id=%d;", 
            order.getTotalPrice(), 
            order.getTotalDiscount(), 
            order.getTotalPayment(),
            order.getId()
        );
    }


    private String getInsertSQLPO() {
        return String.format(
            "insert into purchased_order (total, purchased_on, purchased_by) "
                + "values(%f, '%s', '%s');", 
            order.getTotalPrice(), 
            order.getSaleOnString(),
            order.getSaleBy()
        );
    }


    private String getInsertSQLSale() {
        return String.format(
            "insert into sales (total, discount, paid, vat, sale_on, sale_by, status) "
                + "values(%f, %f, %f, %f, '%s', '%s', %d);", 
            order.getTotalPrice(), 
            order.getTotalDiscount(), 
            order.getTotalPayment(),
            order.VAT(),
            order.getSaleOnString(),
            order.getSaleBy(),
            order.getStatus().ordinal()
        );
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
}
