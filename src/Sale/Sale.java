/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

import Repository.Repository;
import Stock.Product;
import java.util.ArrayList;

/**
 *
 * @author Keo
 */
public class Sale {
    String name;
    int id,price;
    ArrayList<Product> prod   = new ArrayList<Product>();
    ArrayList<Integer> payment   = new ArrayList<Integer>();
    private double discount;
    private double VAT = 0;

    public int id(){
        return this.id;
    }
    public double VAT(){
        return this.VAT;
    }
    public void setVAT(double vat){
        this.VAT = vat;
    }
    public void addProduct(Product p1) {
        for (Product p : prod) 
            if (p.id().equals(p1.id())) {
                p.increaseQuantity(1);
                return;
            }
        prod.add(p1);
    }

    public double getTotalPrice() {
        int sum=0;
        for(Product p: prod){
            sum += p.price() * p.quantity();
        }
        return sum;
    }

    public Product findProduct(String id) {
        
        for(Product p: prod){
            if(p.id().equals(id))
                return p;
        }
        return null;        
    }
    public void removeProduct(Product removeProduct) {
        Product matchedProduct = null;
        for(Product p: prod){
            if(p.id().equals(p.id())) {
                matchedProduct = p;
                matchedProduct.decreaseQuantity(1);
            }
        }

        if (matchedProduct != null &&
            matchedProduct.quantity() == 0)
            prod.remove(matchedProduct);
    }

    public ArrayList<Product> getAllProducts() {
        return prod;
    }

    public double getVAT() {
        return (getTotalPrice()*(1-discount)) * this.VAT;
    }

    public double subTotal() {
        return getVAT()+(getTotalPrice()*(1-discount));
    }

    public void addPayment(int i) {
        payment.add(i);
    }

    public double getTotalPayment() {
        int sum = 0;
        for (int eachPayment : payment)
            sum += eachPayment;
        return sum;
    }

    public double getTotalRemaining() {
        return subTotal()-getTotalPayment();
    }

    public double getTotalChanges() {
        return getTotalPayment() - subTotal();
    }

    public void setDiscount(double d) {
        this.discount = d;
    }

    public double getTotalDiscount() {
        return getTotalPrice()*discount;
    }

    public void printReceipt() {

        System.out.println("Name\t\tQty\tPrice\t\tTotal");
        System.out.println("-------------------------------------------------------");
        for (Product p : getAllProducts())
            System.out.println(
                String.format("%s\t\t%d\t%.2f\t\t%.2f", 
                    p.name(), p.quantity(), p.price(), p.price() * p.quantity()));
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t\tTotal:\t" +getTotalPrice());
        System.out.println("\t\t\t     Discount:\t" +getTotalDiscount());
        System.out.println("\t\t\t     --------------------------");
        System.out.println(String.format("\t\t\t\t  VAT:\t%.2f", getVAT()));
        System.out.println("\t\t\t     Subtotal:\t" +subTotal());
        System.out.println("\t\t\t     --------------------------");
        System.out.println("\t\t\t\t Paid:\t" +getTotalPayment());
        if (getTotalRemaining() >0)
            System.out.println("\t\t\t    Remaining:\t" +getTotalRemaining());

        if (getTotalChanges() >= 0)
            System.out.println("\t\t      Changes:\t" +getTotalChanges());

        System.out.println();
        System.out.println();
    }

    public void save() {
        if (this.id > 0) {
            Repository.updateSale(this);
            for(Product p: prod){
                if (p.saleId() > 0)
                    p.update(this.id);
                else
                    p.save(this.id);
            }
        } else {
            int saleId = Repository.insertNewSale(this);
            this.id = saleId;
            if (saleId > 0)
                for(Product p: prod){
                    p.save(saleId);
                }
        }
        
    }
}
