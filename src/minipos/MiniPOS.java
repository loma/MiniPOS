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
        sale.addProduct(p1);
        double totalPrice = sale.getTotalPrice(); // 10
        System.out.println(totalPrice);

        sale.addProduct(p2);
        totalPrice = sale.getTotalPrice(); // 40
        System.out.println(totalPrice);

        Product p = sale.findProduct("1");
        if (p != null) {
            System.out.print("name:" +p.name());
            System.out.print("price:" +p.price());
        }

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
