/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Config.MiniPOSConfig;
import Sale.Sale;
import Stock.Product;
import Stock.Stock;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Keo
 */
public class MiniPOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Connection connection = tryGetConnection();

        Scanner scanner = new Scanner(System. in); 
        Sale sale = new Sale();
        while(true){
            System.out.println("1. Add product");
            System.out.println("2. Print receipt");
            System.out.println("3. Show all products");
            System.out.println("4. Create new product");
            System.out.println("5. Remove product");
            System.out.println("6. Update product");
            System.out.println("0. Exit");

            System.out.print("Option: ");
            String option = scanner.nextLine();

            switch(option){
                case "1":
                    System.out.print("Product Id: ");
                    String id = scanner.nextLine();
                    sale.addProduct(new Product(id, "Product #"+id, Integer.parseInt(id)*10));
                    break;
                case "2":
                    printReceipt(sale);
                    break;
                case "3":
                    showAllproducts(connection);
                    break;
                case "4":
                    System.out.print("id: ");
                    id = scanner.nextLine();

                    System.out.print("name: ");
                    String name = scanner.nextLine();

                    System.out.print("price: ");
                    double price = scanner.nextDouble();

                    Product p = new Product(id, name, price);
                    p.save(connection);

                    break;

                case "5":
                    System.out.print("id: ");
                    id = scanner.nextLine();

                    p = Product.find(connection, id);
                    p.delete(connection);

                    break;

                case "6":
                    System.out.print("id: ");
                    id = scanner.nextLine();
                    p = Product.find(connection, id);


                    System.out.print("name ("+p.name()+"): ");
                    name = scanner.nextLine();
                    if (!name.equals("")) p.setName(name);

                    System.out.print("price ("+p.price()+"): ");
                    price = scanner.nextDouble();
                    if (price != 0) p.setPrice(price);

                    p.update(connection);

                    break;

                case "0":
                    return;
            }
            System.out.println();
        }
    }

    private static Connection tryGetConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            String driverName = "com.mysql.jdbc.Driver";
            // Create a connection to the database
            String serverName = "192.168.0.99";
            String schema = MiniPOSConfig.DB_NAME +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String url = "jdbc:mysql://" + serverName +  "/" + schema;
            String username = "mini";
            String password = "mini";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully Connected to the database!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return null;
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
        System.out.println("\t\t     Discount:\t" +sale.getTotalDiscount());
        System.out.println("\t\t     --------------------------");
        System.out.println(String.format("\t\t\t  VAT:\t%.2f", sale.getVAT()));
        System.out.println("\t\t     Subtotal:\t" +sale.subTotal());
        System.out.println("\t\t     --------------------------");
        System.out.println("\t\t\t Paid:\t" +sale.getTotalPayment());
        if (sale.getTotalRemaining() >0)
            System.out.println("\t\t    Remaining:\t" +sale.getTotalRemaining());

        if (sale.getTotalChanges() >= 0)
            System.out.println("\t\t      Changes:\t" +sale.getTotalChanges());

        System.out.println();
        System.out.println();
    }

    private static void showAllproducts(Connection connection) {
        String query = "select * from products;";
        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id\tname\t\tprice");
            System.out.println("---------------------------------");
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                double price = rs.getDouble("price");
                System.out.println(String.format("%s\t%s\t\t%.2f", id, name, price));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void insertProduct(Connection connection) {
        String query = "insert into products values(2,'Apple');";
        try (Statement stmt = connection.createStatement()) {

            int result = stmt.executeUpdate(query);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
