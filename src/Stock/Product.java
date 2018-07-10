/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

/**
 *
 * @author Trivico
 */
public class Product {
    String id;
    String name;
    int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int price() {
        return price;
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }
}
