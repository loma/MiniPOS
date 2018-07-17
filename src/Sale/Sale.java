/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

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
    public void addProduct(Product p1) {
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
    public void removeProduct(String string) {

        for(Product p: prod){
            if(p.id().equals(string))
                prod.remove(p);
                
        }
        
    }

    public ArrayList<Product> getAllProducts() {
        return prod;
    }

    public double getVAT() {
        return (getTotalPrice()*(1-discount)) * 0.1;
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

        System.out.println("Name\t\tQty\tPrice\tTotal");
        System.out.println("-----------------------------------------------");
        for (Product p : getAllProducts())
            System.out.println(
                String.format("%s\t\t%d\t%f\t%f", 
                    p.name(), p.quantity(), p.price(), p.price() * p.quantity()));
        System.out.println("-----------------------------------------------");
        System.out.println("\t\t\tTotal:\t" +getTotalPrice());
        System.out.println("\t\t     Discount:\t" +getTotalDiscount());
        System.out.println("\t\t     --------------------------");
        System.out.println(String.format("\t\t\t  VAT:\t%.2f", getVAT()));
        System.out.println("\t\t     Subtotal:\t" +subTotal());
        System.out.println("\t\t     --------------------------");
        System.out.println("\t\t\t Paid:\t" +getTotalPayment());
        if (getTotalRemaining() >0)
            System.out.println("\t\t    Remaining:\t" +getTotalRemaining());

        if (getTotalChanges() >= 0)
            System.out.println("\t\t      Changes:\t" +getTotalChanges());

        System.out.println();
        System.out.println();
    }

}
