package meejitack__burger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BurgerMain {
	private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<MyBurger> myBurgerCart = new ArrayList<>();
    private static ArrayList<MySide> mySideBasket = new ArrayList<>();
    private static Admin admin = new Admin();
    private static ArrayList<MyBurger> burgerList = new ArrayList<>();
    private static ArrayList<MySide> sideList = new ArrayList<>();
 
    
    public static void main(String[] args) {
    	
    	while (true) {
        	System.out.println("\t\t\t\t");
        	System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            System.out.println(" 1. chooseHamburger \t4. deleteSide ");
            System.out.println(" 2. deleteHamburger \t5. showProducts");
            System.out.println(" 3. addSides        \t6. emptyCart");
            System.out.println(" 7. outputReceipt   \t8. managerLogin");
            System.out.println(" 9. EXIT");
            System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            System.out.print("Pick whatever you want to eat: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    chooseHamburger();
                    break;
                case 2:
                    deleteHamburger();
                    break;
                case 3:
                    addSides();
                    break;
                case 4:
                    deleteSide();
                    break;
                case 5:
                    showProducts();
                    break;
                case 6:
                    emptyCart();
                    break;
                case 7:
                    outputReceipt();
                    break;
                case 8:
                    managerLogin();
                    break;
                case 9:
                    System.out.println("Thank you for visiting MeeJiTaek Burger. God bless you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void chooseHamburger() {
        ArrayList<Burger> burgers = readBurgersFromFile("burger.txt");
        if (burgers.isEmpty()) {
            System.out.println("No burgers available.");
            return;
        }
        System.out.println("Available burgers:");
        for (int i = 0; i < burgers.size(); i++) {
            System.out.println((i + 1) + ". " + burgers.get(i));
        }
        System.out.print("Enter the number of the burger you want to add to cart: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > burgers.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        MyBurger selectedBurger = new MyBurger(burgers.get(choice - 1).getName(),
                burgers.get(choice - 1).getPrice(), burgers.get(choice - 1).getInfo());
        myBurgerCart.add(selectedBurger);
        System.out.println("Burger added to cart: " + selectedBurger.getName());
    }

    private static ArrayList<Burger> readBurgersFromFile(String filename) {
        ArrayList<Burger> burgers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0];
                    String priceStr = parts[1];
                    String info = parts[2];
                    if (isValidPrice(priceStr)) {
                        double price = Double.parseDouble(priceStr);
                        burgers.add(new Burger(name, price, info));
                    } else {
                        System.err.println("Invalid price format in burger file: " + priceStr);
                    }
                } else {
                    throw new BurgerException("Invalid format in burger file: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading burger file: " + e.getMessage());
        } catch (BurgerException e) {
            System.err.println(e.getMessage());
        }
        return burgers;
    }

    private static boolean isValidPrice(String priceStr) {
        // Regular expression to match a valid numerical value (integer or decimal)
        return priceStr.matches("^\\d+(\\.\\d+)?$");
    }
    private static void deleteHamburger() {
        if (myBurgerCart.isEmpty()) {
            System.out.println("No burgers in cart to delete.");
            return;
        }
        System.out.println("Burgers in cart:");
        for (int i = 0; i < myBurgerCart.size(); i++) {
            System.out.println((i + 1) + ". " + myBurgerCart.get(i));
        }
        System.out.print("Enter the number of the burger you want to delete from cart: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > myBurgerCart.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        MyBurger deletedBurger = myBurgerCart.remove(choice - 1);
        System.out.println("Deleted burger from cart: " + deletedBurger.getName());
    }

    private static void addSides() {
        ArrayList<Side> sides = readSidesFromFile("side.txt");

        if (sides.isEmpty()) {
            System.out.println("No sides available.");
            return;
        }

        System.out.println("Available sides:");
        for (int i = 0; i < sides.size(); i++) {
            System.out.println((i + 1) + ". " + sides.get(i).getName());
        }

        System.out.print("Enter the number of the side you want to add: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice < 1 || choice > sides.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Side selectedSide = sides.get(choice - 1);
        System.out.println("Selected side: " + selectedSide.getName());

        System.out.println("1. Size Up (Price + $2.1)");
        System.out.println("2. Set (Price x 1.3)");
        System.out.println("3. Size Up & Set (Price x 1.3 + $2.1)");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (option) {
            case 1:
                selectedSide.sizeUp();
                System.out.println("Side sized up: " + selectedSide.getName() + " (Price: $" + selectedSide.getPrice() + ")");
                break;
            case 2:
                selectedSide.set();
                System.out.println("Side set: " + selectedSide.getName() + " (Price: $" + selectedSide.getPrice() + ")");
                break;
            case 3:
                selectedSide.sizeUpNSet();
                System.out.println("Side sized up & set: " + selectedSide.getName() + " (Price: $" + selectedSide.getPrice() + ")");
                break;
            default:
                System.out.println("Invalid option.");
        }

        // You should use ArrayList<Side> instead of ArrayList<MySide>
        mySideBasket.add(new MySide(selectedSide.getName(), selectedSide.getPrice(), selectedSide.getInfo()));
    }

    private static ArrayList<Side> readSidesFromFile(String filename) {
        ArrayList<Side> sides = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0];
                    try {
                        double price = Double.parseDouble(parts[1]);
                        String info = parts[2];
                        sides.add(new Side(name, price, info));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid price format in side file: " + parts[1]);
                    }
                } else {
                    throw new BurgerException("Invalid format in side file: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading side file: " + e.getMessage());
        } catch (BurgerException e) {
            System.err.println(e.getMessage());
        }
        return sides;
    }
    

    private static void deleteSide() {
        if (mySideBasket.isEmpty()) {
            System.out.println("No sides in basket to delete.");
            return;
        }
        System.out.println("Sides in basket:");
        for (int i = 0; i < mySideBasket.size(); i++) {
            System.out.println((i + 1) + ". " + mySideBasket.get(i));
        }
        System.out.print("Enter the number of the side you want to delete from basket: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > mySideBasket.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        MySide deletedSide = mySideBasket.remove(choice - 1);
        System.out.println("Deleted side from basket: " + deletedSide.getName());
    }

    private static void showProducts() {
        System.out.println("Products in cart:");
        System.out.println("Burgers:");
        for (int i = 0; i < myBurgerCart.size(); i++) {
            System.out.println((i + 1) + ". " + myBurgerCart.get(i));
        }
        System.out.println("Sides:");
        for (int i = 0; i < mySideBasket.size(); i++) {
            System.out.println((i + 1) + ". " + mySideBasket.get(i));
        }
    }

    private static void emptyCart() {
        myBurgerCart.clear();
        mySideBasket.clear();
        System.out.println("Shopping cart emptied.");
    }

    private static void outputReceipt() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        // Calculate total price
        double total = calculateTotal();

        // Print receipt header
        System.out.println("Receipt");
        System.out.println("Date and Time: " + formattedDateTime);
        System.out.println("-----------------------------------");

        // Print burger items
        System.out.println("Burgers:");
        for (MyBurger burger : myBurgerCart) {
            System.out.println(burger.getName() + " - $" + burger.getPrice());
        }

        // Print side items
        System.out.println("Sides:");
        for (MySide side : mySideBasket) {
            System.out.println(side.getName() + " - $" + side.getPrice());
        }

        // Print total price
        System.out.println("-----------------------------------");
        System.out.println("Total: $" + total);
    }

    private static double calculateTotal() {
        double total = 0;

        // Calculate total price of burgers
        for (MyBurger burger : myBurgerCart) {
            total += burger.getPrice();
        }

        // Calculate total price of sides
        for (MySide side : mySideBasket) {
            total += side.getPrice();
        }

        return total;
    }

    private static void managerLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.authenticate(username, password)) {
            System.out.println("Login successful. Welcome, manager.");
            // 새로운 제품 추가 등 관리자 작업 로직 구현
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
        
        
        
        
        
    }
}