/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import User.User;
import Repository.Repository;
import Sale.SaleStatus;
import Product.Product;
import Product.ProductType;
import Sale.Order;
import Sale.OrderSQLGenerator;
import Sale.OrderType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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


        Scanner scanner = new Scanner(System. in); 

        User user = new User();
        while ( ! user.isLogin()) {
            System.out.print("username: ");
            String username = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();
            user.login(username, password);
        }

        Order sale = new Order();
        sale.setType(OrderType.SALE);

        Order po = new Order();
        po.setType(OrderType.PO);

        sale.saleBy(user.getName());
        sale.status(SaleStatus.OPEN);

        while(true){
            System.out.println("===== SALE =====");
            System.out.println("01. Add product to sale");
            System.out.println("02. Print receipt");
            System.out.println("03. Remove product from sale");
            System.out.println("04. Save sale");
            System.out.println("05. Add payment");
            System.out.println("06. Add discount");
            System.out.println("07. Set VAT");
            System.out.println("08. Load sale");
            System.out.println("09. New sale");
            System.out.println("===== STOCK =====");
            System.out.println("11. Add product to PO");
            System.out.println("12. Print PO receipt");
            System.out.println("13. Remove product from PO");
            System.out.println("14. Save PO");
            System.out.println("===== PRODUCT =====");
            System.out.println("3. Show all products");
            System.out.println("4. Create new product");
            System.out.println("5. Remove product");
            System.out.println("6. Update product");
            System.out.println("===== USER =====");
            System.out.println("7. Show all users");
            System.out.println("8. Create new user");
            System.out.println("9. Remove user");
            System.out.println("10. Update user");
            System.out.println("0. Exit");

            System.out.print("Option: ");
            String option = scanner.nextLine();

            switch(option){
                case "01":
                    System.out.print("Product Id: ");
                    String id = scanner.nextLine();
                    Product saleProduct = Repository.findProduct(id, ProductType.SALE);
                    sale.addProduct(saleProduct);
                    sale.printReceipt();
                    break;
                case "11":
                    System.out.print("Product Id: ");
                    id = scanner.nextLine();

                    Product newPoProduct = Repository.findProduct(id, ProductType.PO);
                    po.addProduct(newPoProduct);
                    po.printReceipt();

                    break;
                case "02":
                    sale.printReceipt();
                    break;
                case "12":
                    po.printReceipt();
                    break;
                case "03":
                    System.out.print("Product Id: ");
                    id = scanner.nextLine();

                    saleProduct = Repository.findProduct(id, ProductType.SALE);
                    sale.removeProduct(saleProduct);
                    sale.printReceipt();
                    break;
                case "13":
                    System.out.print("Product Id: ");
                    id = scanner.nextLine();

                    newPoProduct = (new Product(ProductType.PO)).find(id);
                    po.removeProduct(newPoProduct);
                    po.printReceipt();
                    break;
                case "04":
                    sale.save();
                    break;
                case "14":
                    po.save();
                    break;
                case "05":
                    System.out.print("Payment amount: ");
                    int paymentAmount = scanner.nextInt();
                    scanner.nextLine();
                    sale.addPayment(paymentAmount);
                    break;
                case "06":
                    System.out.print("Discount: ");
                    double discountAmount = scanner.nextDouble();
                    sale.setDiscount(discountAmount);
                    break;
                case "07":
                    System.out.print("VAT(%): ");
                    double vatAmount = scanner.nextDouble();
                    sale.setVAT(vatAmount);
                    break;
                case "08":
                    System.out.print("Sale Id: ");
                    int saleId = scanner.nextInt();
                    scanner.nextLine();

                    sale = Repository.findOrder(saleId); 
                    break;
                case "09":
                    sale = new Order();
                    sale.setType(OrderType.SALE);

                    sale.saleBy(user.getName());
                    sale.status(SaleStatus.OPEN);
                    break;

                case "3":
                    showAllproducts();
                    break;
                case "4":
                    System.out.print("id: ");
                    id = scanner.nextLine();

                    System.out.print("name: ");
                    String name = scanner.nextLine();

                    System.out.print("price: ");
                    double price = scanner.nextDouble();

                    System.out.print("purchased price: ");
                    double poPrice = scanner.nextDouble();

                    Product p = new Product(id, name, price, poPrice, ProductType.PRODUCT);
                    p.save(0);

                    break;

                case "5":
                    System.out.print("id: ");
                    id = scanner.nextLine();

                    p = Repository.findProduct(id, ProductType.PRODUCT);
                    p.delete();

                    break;

                case "6":
                    System.out.print("id: ");
                    id = scanner.nextLine();
                    p = Repository.findProduct(id, ProductType.PRODUCT);


                    System.out.print("name ("+p.getName()+"): ");
                    name = scanner.nextLine();
                    if (!name.equals("")) p.setName(name);

                    System.out.print("price ("+p.price()+"): ");
                    price = scanner.nextDouble();
                    if (price != 0) p.setPrice(price);

                    p.update();

                    break;

                case "7":
                    List<User> users = User.all();
                    showAllUsers(users);
                    break;
                case "8":
                    System.out.print("id: ");
                    int userId = scanner.nextInt(); 
                    scanner.nextLine();

                    System.out.print("name: ");
                    name = scanner.nextLine();

                    System.out.print("password: ");
                    String password = scanner.nextLine();

                    System.out.print("role: ");
                    int role = scanner.nextInt();

                    User u = new User(userId, name, password, role);
                    u.save();

                    break;

                case "9":
                    System.out.print("id: ");
                    userId = scanner.nextInt(); 
                    scanner.nextLine();
                    user = User.find(userId);
                    user.delete();
                    break;
                case "10":
                    System.out.print("id: ");
                    userId = scanner.nextInt(); 
                    scanner.nextLine();
                    user = User.find(userId);

                    System.out.print("username: ");
                    String username = scanner.nextLine(); 
                    System.out.print("password: ");
                    password = scanner.nextLine(); 
                    System.out.print("role: ");
                    role = scanner.nextInt(); 
                    scanner.nextLine(); 

                    user.setUsername(username);
                    user.setPassword(password);
                    user.setRole(role);
                    user.update();
                    break;

                case "0":
                    return;
            }
            System.out.println();
        }
    }



    private static void showAllproducts() {
        Connection connection = new Repository().getConnection();
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

    private static void showAllUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("Empty users.");
            return;
        }
        System.out.println("id\tname\t\trole");
        System.out.println("---------------------------------");
        for (User u : users){
            System.out.println(String.format("%d\t%s\t\t%s", u.getId(), u.getName(), u.getRole()));
        }
    }
    
}
