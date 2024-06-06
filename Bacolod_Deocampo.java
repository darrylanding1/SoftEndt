import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagement {
    private static Map<String, Integer> inventory = new HashMap<>();

    // Add item to the inventory
    public static void addItem(String item, int quantity) {
        String prefixedItem = "Frooty " + item;
        inventory.put(prefixedItem, quantity);
    }

    // Remove item from the inventory
    public static void removeItem(String item, int quantity) {
        String prefixedItem = "Frooty " + item;
        if (inventory.containsKey(prefixedItem)) {
            int currentQuantity = inventory.get(prefixedItem);
            if (currentQuantity <= quantity) {
                inventory.remove(prefixedItem);
            } else {
                inventory.put(prefixedItem, currentQuantity - quantity);
            }
            System.out.println("Fruit removed successfully.");
            displayInventory();
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    // Display the inventory
    public static void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Frooty Inventory is empty.");
        } else {
            System.out.println("Frooty Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(itemName + ": " + quantity + " boxes");
            }
        }
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nFrooty Inventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Display Inventory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choiceStr = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter fruit name: ");
                    String addItemName = scanner.nextLine();
                    
                    // Error handling for non-string input
                    if (!addItemName.matches("[a-zA-Z]+")) {
                        System.out.println("Invalid input. Please enter a valid fruit name.");
                        break;
                    }
                    
                    System.out.print("Enter quantity: ");
                    String addQuantityStr = scanner.nextLine();
                    int addQuantity;

                    try {
                        addQuantity = Integer.parseInt(addQuantityStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for quantity.");
                        break;
                    }

                    System.out.println("Are you sure you want to add '" + addItemName + "' with quantity " + addQuantity + " boxes? (yes/no)");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("yes")) {
                        addItem(addItemName, addQuantity);
                        System.out.println("Fruit added successfully.");
                        displayInventory();
                    } else {
                        System.out.println("Addition cancelled.");
                    }
                    break;
                case 2:
                    System.out.print("Enter fruit name: ");
                    String removeItem = scanner.nextLine();
                    
                    if (inventory.containsKey("Frooty " + removeItem)) {
                        System.out.println("Current inventory:");
                        displayInventory();
                        
                        System.out.print("Enter quantity to remove: ");
                        String removeQuantityStr = scanner.nextLine();
                        int removeQuantity;

                        try {
                            removeQuantity = Integer.parseInt(removeQuantityStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number for quantity.");
                            break;
                        }

                        removeItem(removeItem, removeQuantity);
                    } else {
                        System.out.println("Item not found in inventory.");
                    }
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
