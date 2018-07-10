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

        Product p1 = new Product("name1", 10);
        Product p2 = new Product("name2", 30);
        Product p3 = new Product("name3", 20);

        System.out.println("Sale");
        Sale sale = new Sale();
        sale.addProduct(p1);
        double totalPrice = sale.getTotalPrice(); // 10
        System.out.println(totalPrice);

        sale.addProduct(p2);
        totalPrice = sale.getTotalPrice(); // 40
        System.out.println(totalPrice);

        System.out.println("Stock");
        Stock stock = new Stock();
        stock.addProduct(p1);
        totalPrice = stock.getTotalPrice(); // 10
        System.out.println(totalPrice);

        stock.addProduct(p2);
        totalPrice = stock.getTotalPrice(); // 40
        System.out.println(totalPrice);
        
        
    }
    
}
