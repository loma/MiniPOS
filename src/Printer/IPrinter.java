/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printer;

import Product.Product;
import Sale.Order;

/**
 *
 * @author loma
 */
public abstract class IPrinter {
    public void print(Order order){
        println("Date: \t\t" + order.getSaleOnString());
        println("Order by: \t" + order.getSaleBy());
        println("Status: \t" + order.getStatus());
        println("Order Id: \t" + order.getId());
        println("Name\t\tQty\tPrice\t\tTotal");
        println("-------------------------------------------------------");
        for (Product p : order.getAllProducts())
            println(
                String.format("%s\t\t%d\t%.2f\t\t%.2f", 
                    p.getName(), p.getQuantity(), p.price(), p.price() * p.getQuantity()));
        println("-------------------------------------------------------");
        println("\t\t\t\tTotal:\t" +order.getTotalPrice());
        println("\t\t\t     Discount:\t" +order.getTotalDiscount());
        println("\t\t\t     --------------------------");
        println(String.format("\t\t\t\t  VAT:\t%.2f", order.getVAT()));
        println("\t\t\t     Subtotal:\t" +order.subTotal());
        println("\t\t\t     --------------------------");
        println("\t\t\t\t Paid:\t" +order.getTotalPayment());
        if (order.getTotalRemaining() >0)
            println("\t\t\t    Remaining:\t" +order.getTotalRemaining());

        if (order.getTotalChanges() >= 0)
            println("\t\t      Changes:\t" +order.getTotalChanges());

        println();
        println();
    }; 
    abstract void println(String message);
    abstract void println();
    public abstract void close();
}
