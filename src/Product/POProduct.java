/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

/**
 *
 * @author loma
 */
public class POProduct extends Product {

    
    public POProduct(String id, String name, double price, double poPrice) {
        super(id, name, price, poPrice);
    }

    public POProduct() {
    }

    public double price() {
        return poPrice;
    }
}
