/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Repository.Repository;
import Stock.Product;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author loma
 */
public class PurchasedOrder {

    private ArrayList<POProduct> products = new ArrayList<POProduct>();
    private Date saleOn = new Date();
    private String saleBy;
    private int id;

    void addProduct(POProduct newProduct) {
        for (Product p : this.products) 
            if (p.id().equals(newProduct.id())) {
                p.increaseQuantity(1);
                return;
            }
        this.products.add(newProduct);
    }

    public String getSaleOnString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(this.saleOn);
    }

    public String getSaleBy(){
        return this.saleBy;
    }

    void printReceipt() {
        System.out.println("Date: \t\t" + getSaleOnString());
        System.out.println("Order by: \t" + getSaleBy());
        System.out.println("Order Id: \t" + getId());
        System.out.println("Name\t\tQty\tPrice\t\tTotal");
        System.out.println("-------------------------------------------------------");
        for (Product p : this.products)
            System.out.println(
                String.format("%s\t\t%d\t%.2f\t\t%.2f", 
                    p.name(), p.quantity(), p.price(), p.price() * p.quantity()));
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t\tTotal:\t" +getTotalPrice());
        System.out.println();
        System.out.println();
    }

    public double getTotalPrice() {
        int sum=0;
        for(POProduct p: this.products){
            sum += p.price() * p.quantity();
        }
        return sum;
    }

    private int getId() {
        return this.id;
    }

    void removeProduct(Product newProduct) {
        Product matchedProduct = null;
        for(Product p: this.products){
            if(p.id().equals(newProduct.id())) {
                matchedProduct = p;
                matchedProduct.decreaseQuantity(1);
            }
        }

        if (matchedProduct != null &&
            matchedProduct.quantity() == 0)
            this.products.remove(matchedProduct);
    }

    public void save() {
        /*
        if (this.id > 0) {
            Repository.updatePO(this);
            for(Product p: this.products){
                if (p.poId() > 0)
                    p.update(this.id);
                else
                    p.save(this.id);
            }
        } else {
*/
        String insertSQL = getInsertSQL();
        int poId = Repository.executeUpdateWithLastId(insertSQL);

        this.id = poId;
        if (poId > 0)
            for(POProduct p: this.products){
                p.save(poId);
            }
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
