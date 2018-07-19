/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Repository.Repository;
import Sale.Sale;
import Stock.Product;
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
        Sale sale = new Sale();

        User user = new User();
        while ( ! user.isLogin()) {
            System.out.print("username: ");
            String username = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();
            user.login(username, password);
        }
        while(true){
            System.out.println("1. Add product");
            System.out.println("2. Print receipt");
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
                case "1":
                    System.out.print("Product Id: ");
                    String id = scanner.nextLine();
                    sale.addProduct(new Product(id, "Product #"+id, Integer.parseInt(id)*10));
                    break;
                case "2":
                    sale.printReceipt();
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

                    Product p = new Product(id, name, price);
                    p.save();

                    break;

                case "5":
                    System.out.print("id: ");
                    id = scanner.nextLine();

                    p = Product.find(id);
                    p.delete();

                    break;

                case "6":
                    System.out.print("id: ");
                    id = scanner.nextLine();
                    p = Product.find(id);


                    System.out.print("name ("+p.name()+"): ");
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
                    int idInt = scanner.nextInt();
                    name = scanner.nextLine();
                    System.out.print("name: ");
                    name = scanner.nextLine();
                    System.out.print("password: ");
                    String password = scanner.nextLine();
                    System.out.print("role: ");
                    int role = scanner.nextInt();

                    User u = new User(idInt, name, password, role);
                    u.save();

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
    private static void insertProduct(Connection connection) {
        String query = "insert into products values(2,'Apple');";
        try (Statement stmt = connection.createStatement()) {

            int result = stmt.executeUpdate(query);
            System.out.println(result);
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
