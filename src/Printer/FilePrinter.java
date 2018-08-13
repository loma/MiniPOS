/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printer;

import Sale.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loma
 */
public class FilePrinter extends IPrinter {

    PrintWriter printWriter;
    public FilePrinter(){
        File file = new File("printer.txt");
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilePrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    void println(String message) {
        printWriter.println(message);
    }

    @Override
    void println() {
        printWriter.println();
    }

    @Override
    public void close() {
        printWriter.close();
    }
}
