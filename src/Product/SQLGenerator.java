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
        switch(className){
            case "Product.POProduct":
                return getInsertSQLPODetails();
            case "Product.SaleProduct":
                return getInsertSQLSaleDetails();
            case "Product.Product":
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
    
}
