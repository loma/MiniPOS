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
public class TerminalPrinter implements IPrinter {

    @Override
    public void print(Order order) {
        System.out.println("Date: \t\t" + order.getSaleOnString());
        System.out.println("Order by: \t" + order.getSaleBy());
        System.out.println("Status: \t" + order.getStatus());
        System.out.println("Order Id: \t" + order.getId());
        System.out.println("Name\t\tQty\tPrice\t\tTotal");
        System.out.println("-------------------------------------------------------");
        for (Product p : order.getAllProducts())
            System.out.println(
                String.format("%s\t\t%d\t%.2f\t\t%.2f", 
                    p.getName(), p.getQuantity(), p.price(), p.price() * p.getQuantity()));
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t\tTotal:\t" +order.getTotalPrice());
        System.out.println("\t\t\t     Discount:\t" +order.getTotalDiscount());
        System.out.println("\t\t\t     --------------------------");
        System.out.println(String.format("\t\t\t\t  VAT:\t%.2f", order.getVAT()));
        System.out.println("\t\t\t     Subtotal:\t" +order.subTotal());
        System.out.println("\t\t\t     --------------------------");
        System.out.println("\t\t\t\t Paid:\t" +order.getTotalPayment());
        if (order.getTotalRemaining() >0)
            System.out.println("\t\t\t    Remaining:\t" +order.getTotalRemaining());

        if (order.getTotalChanges() >= 0)
            System.out.println("\t\t      Changes:\t" +order.getTotalChanges());

        System.out.println();
        System.out.println();
    }
    
}
