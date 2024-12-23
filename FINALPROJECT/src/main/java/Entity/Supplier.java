package Entity;

import java.util.Scanner;

public class Supplier extends Person {
    private int supplierId;
    private String contact;

    public Supplier(int supplierId, String username, String password, String dateOfBirth, String contact) {
        super(username, password, dateOfBirth);
        this.supplierId = supplierId;
        setContact(contact);
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (isValidPhoneNumber(contact)) {
            this.contact = contact;
        } else {
            throw new IllegalArgumentException("Invalid phone number. It must contain only digits and have a length of 10–15.");
        }
    }

    @Override
    public void displayRole() {
        System.out.println("I am a Supplier.");
    }


    public void removeItemFromDatabase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Item Name:");
        String name = scanner.nextLine();
        for (int i = 0; i < Database.items.size(); i++) {
            item currentItem = Database.items.get(i);
            if (currentItem.getName().equals(name)) {
                Database.items.remove(i);
                System.out.println("Item removed successfully: " + currentItem.getName());
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void showItems() {
        for (int i = 0; i < Database.items.size(); i++) {
            item currentItem = Database.items.get(i);
            System.out.println(currentItem.toString());
        }
    }

    public void showCategories() {
        for (int i = 0; i < Database.items.size(); i++) {
            item currentItem = Database.items.get(i);
            System.out.println(currentItem.getCategory());
        }
    }

    public void showSupplierDetails() {
        System.out.println("Supplier ID: " + supplierId);
        System.out.println("Username: " + username);
        System.out.println("Contact: " + contact);
        System.out.println("Date of Birth: " + dateOfBirth);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\d{10,15}$");
    }

    public void addItemToDatabase() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter item name: ");
        String name = scanner.nextLine();

        double price = 0.0;
        boolean validPrice = false;
        while (!validPrice) {
            System.out.println("Enter item price (numbers only): ");
            String priceInput = scanner.nextLine();

            try {
                price = Double.parseDouble(priceInput);
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                } else {
                    validPrice = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
            }
        }

        System.out.println("Enter item category: ");
        String category = scanner.nextLine();
    }
}

      /***  item newItem = new item("samsung", 500, "Mobiles", c2, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (3).jpeg");
        Database.items.add(newItem);
        System.out.println("Item added to database: " + newItem.getName());
    }**/

    /**public void updateStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Item Name:");
        String name = scanner.nextLine();
        for (item currentItem : Database.items) {
            if (currentItem.getName().equals(name)) {
                System.out.println("Enter new stock quantity:");
                int newStock = scanner.nextInt();
                currentItem.setStock(newStock);
                System.out.println("Stock updated successfully for item: " + currentItem.getName());
                return;
            }
        }
        System.out.println("Item not found.");
    }
}**/