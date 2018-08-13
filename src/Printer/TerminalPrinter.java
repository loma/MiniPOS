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
public class TerminalPrinter extends IPrinter {

    @Override
    void println(String message) {
        System.out.println(message);
    }

    @Override
    void println() {
        System.out.println();
    }

    @Override
    public void close() {
    }
    
}
