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
 ArrayList<Product> pro = new ArrayList<Product>();
    public void addProduct(Product p1) {
        pro.add(p1);
    }

    public double getTotalPrice() {
        int sum=0;
        for(Product p: pro){
            sum += p.price();
        }
        return sum;
    }

    public Product findProduct(String id) {
        
        for(Product p: pro){
            if(p.id().equals(id))
                return p;
        }
        return null;        
    }

    public void removeProduct(String string) {
    }
}
