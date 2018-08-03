/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale;

import Stock.Product;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Keo
 */
public class Order {

    protected String name;
    protected int id;
    protected ArrayList<Product> products   = new ArrayList<Product>();
    protected ArrayList<Integer> payment   = new ArrayList<Integer>();
    protected double discount;
    protected double VAT = 0;
    protected Date saleOn;
    protected String saleBy = "";
    protected SaleStatus status;

    public Order() {
        this.saleOn = new Date();
    }

    public Order(int id, double discount, double vat, double payment, int status, Date saleOn, String saleBy) {
        this.id = id;
        this.discount = discount;
        this.VAT = vat;
        this.payment.add((int)payment);
        this.status = SaleStatus.values()[status];
        this.saleOn = saleOn;
        this.saleBy = saleBy;
    }

    public String getSaleOnString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(this.saleOn);
    }

    public String getSaleBy(){
        return this.saleBy;
    }

    public SaleStatus getStatus(){
        return this.status;
    }

    public int getId(){
        return this.id;
    }
    public double VAT(){
        return this.VAT;
    }
    public void setVAT(double vat){
        this.VAT = vat;
    }
    public void addProduct(Product p1) {
        this.status = SaleStatus.PROGRESS;
        for (Product p : products) 
            if (p.id().equals(p1.id())) {
                p.increaseQuantity(1);
                return;
            }
        products.add(p1);
    }

    public double getTotalPrice() {
        int sum=0;
        for(Product p: products){
            sum += p.price() * p.quantity();
        }
        return sum;
    }

    public Product findProduct(String id) {
        
        for(Product p: products){
            if(p.id().equals(id))
                return p;
        }
        return null;        
    }
    public void removeProduct(Product removeProduct) {
        Product matchedProduct = null;
        for(Product p: products){
            if(p.id().equals(p.id())) {
                matchedProduct = p;
                matchedProduct.decreaseQuantity(1);
            }
        }

        if (matchedProduct != null &&
            matchedProduct.quantity() == 0)
            products.remove(matchedProduct);
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public double getVAT() {
        return (getTotalPrice()*(1-discount)) * this.VAT;
    }

    public double subTotal() {
        return getVAT()+(getTotalPrice()*(1-discount));
    }

    public void addPayment(int i) {
        payment.add(i);

        if (getTotalRemaining() <= 0)
            this.status = SaleStatus.PAID;
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

        System.out.println("Date: \t\t" + getSaleOnString());
        System.out.println("Order by: \t" + getSaleBy());
        System.out.println("Status: \t" + getStatus());
        System.out.println("Order Id: \t" + getId());
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
    }

    public static Order find(int id) {
        return null;
    }

    public void addProducts(ArrayList<Product> products) {

        for (Product newProduct : products) {
            boolean isNewProduct = true;
            for (Product existingProducts : this.products) 
                if (existingProducts.id().equals(newProduct.id())) {
                    existingProducts.increaseQuantity(1);
                    isNewProduct = false;
                }
            
            if (isNewProduct)
                this.products.add(newProduct);
        } 
    }

    public void saleBy(String name) {
        this.saleBy = name;
    }

    public void status(SaleStatus saleStatus) {
        this.status = saleStatus;
    }

}