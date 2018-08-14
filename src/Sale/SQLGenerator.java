/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

/**
 *
 * @author loma
 */
public class SQLGenerator {

    private final OrderType type;
    private Order order;

    public SQLGenerator(OrderType type) {
        this.type = type;
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
