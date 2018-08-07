/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

/**
 *
 * @author loma
 */
public class SQLGenerator {

    private final Product product;

    public SQLGenerator(Product p){
        this.product = p;
    }

    String getInsertSQL() {
        String className = product.getClass().getName(); 
        switch(product.getType()){
            case PO:
                return getInsertSQLPODetails();
            case SALE:
                return getInsertSQLSaleDetails();
            case PRODUCT:
                return getInsertSQLProduct();
        }
        return "";
    }

    private String getInsertSQLSaleDetails() {
        return String.format(
            "insert into sale_details "
            + "(sale_id, product_id, quantity, price) "
            + "values(%d, '%s', %d, %f);",
            product.getSaleId(), product.getId(), 
            product.getQuantity(), product.price()
        );
    }

    String getInsertSQLProduct() {
        return String.format(""
            + "insert into products "
            + "(id, name, price, purchased_price, quantity) "
            + "values('%s', '%s', %f, %f, 0);", 
            product.getId(), product.getName(), 
            product.price(), product.price()
        );
    }

    private String getInsertSQLPODetails() {
        return String.format(
            "insert into purchased_order_details "
            + "(purchased_order_id,product_id,quantity,price) "
            + "values(%d, '%s', %d, %f);",
            product.getPOId(), product.getId(), 
            product.getQuantity(), product.price()
        );
    }

    String getUpdateSQL() {
        String className = product.getClass().getName(); 
        switch(product.getType()){
            case SALE:
                return getUpdateSQLSaleDetails();
            case PRODUCT:
                return getUpdateSQLProduct();
        }
        return "";
    }
    private String getUpdateSQLProduct() {
        return String.format("update products set name='%s', price=%f where id ='%s';", 
            product.getName(), 
            product.price(),
            product.getId()
        );
    }

    private String getUpdateSQLSaleDetails() {
        return String.format(
            "update sale_details set quantity=%d, price=%f "
                + "where sale_id=%d and product_id='%s'; ",
            product.getQuantity(),
            product.price(),
            product.getSaleId(),
            product.getId()
        );
    }
    
}
