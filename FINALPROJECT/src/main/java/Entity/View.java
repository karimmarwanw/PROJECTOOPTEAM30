/**package Entity;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    private Person loggedInUser;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Database.create();

        while (true) {
            System.out.println("Welcome to the Shopping System\n");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        String loginUsername = scanner.next();
                        String loginPassword = scanner.next();
                        login(loginUsername, loginPassword);
                        break;
                    case 2:
                        String signUpUsername = scanner.next();
                        String signUpPassword = scanner.next();
                        String dateOfbirth = scanner.next();
                        String role = scanner.next();
                        signUp(signUpUsername, signUpPassword, dateOfbirth, role);
                        break;
                    case 3:
                        System.out.println("Exiting...\n");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.next();
            }
        }
    }

    public boolean login(String username, String password) {
        for (Admin admin : Database.admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                loggedInUser = admin;
                adminMenu();
                return true;
            }
        }

        for (Customer customer : Database.customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                loggedInUser = customer;
                customerMenu();
                return true;
            }
        }

        for (Supplier supplier : Database.suppliers) {
            if (supplier.getUsername().equals(username) && supplier.getPassword().equals(password)) {
                loggedInUser = supplier;
                supplierMenu();
                return true;
            }
        }

        return false;
    }

    public boolean signUp(String username, String password, String dateOfBirth, String role) {
        if (!dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        switch (role) {
            case "Admin":
                Admin newAdmin = new Admin(username, password, dateOfBirth, "role", 0);
                Database.admins.add(newAdmin);
                break;
            case "Customer":
                Customer newCustomer = new Customer(username, password, dateOfBirth, 10000.0, "address", Gender.MALE, new ArrayList<>(), new Cart());
                Database.customers.add(newCustomer);
                break;
            case "Supplier":
                Supplier newSupplier = new Supplier(1, username, password, dateOfBirth, "contact");
                Database.suppliers.add(newSupplier);
                break;
            default:
                return false;
        }
        return true;
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu\n");
            System.out.println("1. View Items");
            System.out.println("2. View Orders");
            System.out.println("3. View Suppliers");
            System.out.println("4. Add Supplier");
            System.out.println("5. View Total Item Count");
            System.out.println("6. View Total Supplier Count");
            System.out.println("7. View Total Order Count");
            System.out.println("8. View Total Customer Count");
            System.out.println("9. View Total Sales");
            System.out.println("10. View All Customers");
            System.out.println("11. View All Categories");
            System.out.println("12. Logout");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        ((Admin) loggedInUser).viewAllItems();
                        break;
                    case 2:
                        ((Admin) loggedInUser).viewAllOrders();
                        break;
                    case 3:
                        ((Admin) loggedInUser).viewAllSuppliers();
                        break;
                    case 4:
                        ((Admin) loggedInUser).addSupplier();
                        break;
                    case 5:
                        ((Admin) loggedInUser).getTotalItemCount();
                        break;
                    case 6:
                        ((Admin) loggedInUser).getTotalSupplierCount();
                        break;
                    case 7:
                        ((Admin) loggedInUser).getTotalOrderCount();
                        break;
                    case 8:
                        ((Admin) loggedInUser).getTotalCustomerCount();
                        break;
                    case 9:
                        ((Admin) loggedInUser).getTotalSales();
                        break;
                    case 10:
                        ((Admin) loggedInUser).viewAllCustomers();
                        break;
                    case 11:
                        ((Admin) loggedInUser).viewAllCategories();
                        break;
                    case 12:
                        System.out.println("Logging out...\n");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.next();
            }
        }
    }

    public void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Customer Menu\n");
            System.out.println("1. View Items");
            System.out.println("2. View Cart");
            System.out.println("3. Add Item to Cart");
            System.out.println("4. Remove Item from Cart");
            System.out.println("5. Choose Payment Method & pay");
            System.out.println("6. View Balance");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        viewItems();
                        break;
                    case 2:
                        ((Customer) loggedInUser).displayAllCart();
                        break;
                    case 3:
                        ((Customer) loggedInUser).getCart().addItemToCart();
                        break;
                    case 4:
                        ((Customer) loggedInUser).getCart().removeItemFromCart();
                        break;
                    case 5:
                        ((Customer) loggedInUser).getCart().choosePaymentMethodAndPay((Customer) loggedInUser);
                        break;
                    case 6:
                        System.out.println("Your balance: $" + ((Customer) loggedInUser).getBalance() + "\n");
                        break;
                    case 7:
                        System.out.println("Logging out...\n");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.next();
            }
        }
    }

    public void supplierMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Supplier Menu\n");
            System.out.println("1. View Items");
            System.out.println("2. Add Item to Database");
            System.out.println("3. Update Stock");
            System.out.println("4. Remove Item from Database");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        ((Supplier) loggedInUser).showItems();
                        break;
                    case 2:
                        ((Supplier) loggedInUser).addItemToDatabase();
                        break;
                    case 3:
                        ((Supplier) loggedInUser).updateStock();
                        break;
                    case 4:
                        ((Supplier) loggedInUser).showItems();
                        ((Supplier) loggedInUser).removeItemFromDatabase();
                        break;
                    case 5:
                        System.out.println("Logging out...\n");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.next();
            }
        }
    }

    private void viewItems() {
        System.out.println("Available Items:\n");
        for (item currentItem : Database.items) {
            System.out.println(currentItem.getName() + " - $" + currentItem.getPrice() + "\n");
        }
    }
}*/