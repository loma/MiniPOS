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
        System.out.println("After add 1: "+ totalPrice);

        sale.addProduct(p2);
        totalPrice = sale.getTotalPrice(); // 40
        System.out.println("After add 2: " + totalPrice);

        System.out.println("Find product with id 1");
        Product p = sale.findProduct("1");
        if (p != null) {
            System.out.println("name:" +p.name());
            System.out.println("price:" +p.price());
        }

        sale.removeProduct("1");
        totalPrice = sale.getTotalPrice();
        System.out.println("After remove:" + totalPrice);

        System.out.println();
        System.out.println("Stock");
        Stock stock = new Stock();
        stock.addProduct(p1);
        totalPrice = stock.getTotalPrice(); // 10
        System.out.println(totalPrice);
        System.out.println("After add 1: "+ totalPrice);

        stock.addProduct(p2);
        totalPrice = stock.getTotalPrice(); // 40
        System.out.println(totalPrice);
        System.out.println("After add 2: "+ totalPrice);
        
        p = stock.findProduct("1");
        if (p != null) {
            System.out.println("name:" +p.name());
            System.out.println("price:" +p.price());
        }

        stock.removeProduct("1");
        totalPrice = stock.getTotalPrice();
        System.out.println("After remove:" + totalPrice);
        
    }
    
}
