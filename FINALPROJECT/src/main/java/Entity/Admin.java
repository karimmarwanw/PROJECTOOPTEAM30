package Entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Person {
    private String role;
    private int workingHours;

    private static final String[] VALID_ROLES = {"ceo", "cfo"};

    public Admin(String username, String password, String dateOfBirth, String role, int workingHours) {
        super(username, password, dateOfBirth);
        if (isValidRole(role)) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role. Role must be 'ceo' or 'cfo' in lowercase.");
        }
        this.workingHours = workingHours;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (isValidRole(role)) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role. Role must be 'ceo' or 'cfo' in lowercase.");
        }
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public void displayRole() {
        System.out.println("I am an Admin.");
    }

    public void getTotalItemCount() {
        System.out.println("Total items: " + Database.items.size());
    }

    public void getTotalSupplierCount() {
        System.out.println("Total suppliers: " + Database.suppliers.size());
    }

    public void getTotalOrderCount() {
        System.out.println("Total orders: " + Database.orders.size());
    }

    public void getTotalCustomerCount() {
        System.out.println("Total customers: " + Database.customers.size());
    }

    public void getTotalSales() {
        double totalSales = 0;
        for (Order order : Database.orders) {
            for (String itemName : order.getItems()) {
                item item = Database.items.stream()
                        .filter(it -> it.getName().equals(itemName))
                        .findFirst()
                        .orElse(null);
                if (item != null) {
                    totalSales += item.getPrice();
                }
            }
        }
        System.out.println("Total sales: $" + totalSales);
    }

    public void viewAllItems() {
        System.out.println("Viewing Items:");
        for (item item : Database.items) {
            System.out.println(item);
        }
    }

    public void viewAllSuppliers() {
        System.out.println("Viewing Suppliers:");
        for (Supplier supplier : Database.suppliers) {
            System.out.println("Supplier ID: " + supplier.getSupplierId() + ", Name: " + supplier.getUsername() + ", Contact: " + supplier.getContact());
        }
    }

    public void viewAllOrders() {
        System.out.println("Viewing Orders:");
        for (Order order : Database.orders) {
            System.out.println("Order ID: " + order.getOrderId() + ", Customer Username: " + order.getCustomer().getUsername());
            System.out.println("Items in this order:");
            for (String itemName : order.getItems()) {
                item orderItem = Database.items.stream()
                        .filter(it -> it.getName().equals(itemName))
                        .findFirst()
                        .orElse(null);
                if (orderItem != null) {
                    System.out.println("Item Name: " + orderItem.getName() + ", Price: $" + orderItem.getPrice());
                }
            }
        }
    }

    public void viewAllCustomers() {
        System.out.println("Viewing Customers:");
        for (Customer customer : Database.customers) {
            System.out.println("Customer Username: " + customer.getUsername());
        }
    }

    public void viewAllCategories() {
        System.out.println("Viewing Categories:");
        for (Category category : Database.categories) {
            System.out.println("Category Type: " + category.getName());
        }
    }

    public void addSupplier() {
        Scanner scanner = new Scanner(System.in);
        int supplierId = 0;
        while (true) {
            try {
                System.out.println("Enter Supplier ID:");
                supplierId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Supplier ID.");
            }
        }

        System.out.println("Enter Supplier Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Supplier Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Supplier Date of Birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Enter Supplier Contact (Phone Number):");
        String contact = scanner.nextLine();
        if (!isValidPhoneNumber(contact)) {
            System.out.println("Invalid phone number. Supplier not added.");
            return;
        }

        Supplier newSupplier = new Supplier(supplierId, username, password, dateOfBirth, contact);
        Database.suppliers.add(newSupplier);
        System.out.println("Supplier added successfully: " + newSupplier.getUsername());
    }

    public void addItemToDatabase() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        double price = 0;
        while (true) {
            System.out.print("Enter item price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("Price must be a positive number. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        System.out.print("Enter item category: ");
        String category = scanner.nextLine();

        System.out.print("Enter image path: ");
        String imagePath = scanner.nextLine();

        if (!isValidImagePath(imagePath)) {
            System.out.println("Invalid image path. Please make sure the path ends with .jpg, .jpeg, or .png.");
            return;
        }

        item newItem = new item(name, price, category, imagePath);
        Database.items.add(newItem);
        System.out.println("Item added to database successfully: " + newItem.getName());
    }

    private boolean isValidImagePath(String path) {
        return path != null && path.matches(".*\\.(jpg|jpeg|png)$");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\d{10,15}$");
    }

    private boolean isValidRole(String role) {
        for (String validRole : VALID_ROLES) {
            if (validRole.equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
}