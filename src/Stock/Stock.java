/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;
import java.util.ArrayList;

/**
 *
 * @author Keo
 */
public class Stock {
    ArrayList<Product> productList = new ArrayList<Product>();

    public void addProduct(Product p1) {
        productList.add(p1);
    }

    public double getTotalPrice() {
        int sum = 0;
        for (Product p : productList)
            sum += p.price();

        return sum;
    }

    public Product findProduct(String string) {
        return null;
    }

    public void removeProduct(String string) {
    }
    
}
