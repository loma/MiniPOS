/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Sale.Sale;
import Stock.Product;
import Stock.Stock;

/**
 *
 * @author Keo
 */
public class MiniPOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Product p1 = new Product("1", "name1", 10);
        Product p2 = new Product("2", "name2", 30);
        Product p3 = new Product("3", "name3", 20);

        System.out.println("Sale");
        Sale sale = new Sale();
        System.out.println("new sales");
        printReceipt(sale);
        sale.addProduct(p1);
        System.out.println("Add product 1");
        printReceipt(sale);

        sale.addProduct(p2);
        System.out.println("Add product 2");
        printReceipt(sale);

        Product p = sale.findProduct("1");
        p.setPrice(9);
        System.out.println("Update product 1 price to 9");
        printReceipt(sale);

        p = sale.findProduct("2");
        p.setQuantity(3);
        System.out.println("Update product 2 qty to 3");
        printReceipt(sale);

        sale.removeProduct("1");
        System.out.println("Remove product 1");
        printReceipt(sale);
    }

    private static void printReceipt(Sale sale) {

        System.out.println("Name\t\tQty\tPrice\tTotal");
        System.out.println("-----------------------------------------------");
        for (Product p : sale.getAllProducts())
            System.out.println(
                String.format("%s\t\t%d\t%d\t%d", 
                    p.name(), p.quantity(), p.price(), p.price() * p.quantity()));
        System.out.println("-----------------------------------------------");
        System.out.println("\t\t\tTotal:\t" +sale.getTotalPrice());
        System.out.println("\t\t\tVAT:\t" +sale.getVAT());
        System.out.println("\t\t     Subtotal:\t" +sale.subTotal());
        System.out.println();
        System.out.println();
    }
    
}
