package Task_05;

import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {
    private static final class Item {
        double cost;
        String category;
        int quantity;

        Item(double cost, String category, int quantity) {
            this.cost = cost;
            this.category = category;
            this.quantity = quantity;
        }
    }

    private final HashMap<String, Item> groceryList = new HashMap<>();

    // Add item with cost, category, quantity
    public void addItem(String item, double cost, String category, int quantity) {
        if (item == null) {
            System.out.println("Error: Cannot add empty or null item.");
            return;
        }
        if (category == null) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }
        if (cost < 0) {
            System.out.println("Error: Cost cannot be negative.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be positive.");
            return;
        }

        if (groceryList.containsKey(item)) {
            Item old = groceryList.get(item);
            int newQty = old.quantity + quantity;
            groceryList.put(item, new Item(cost, category, newQty));
            System.out.println("\"" + item + "\" already exists. " +
                    "Updating cost from $" + String.format("%.2f", old.cost) + " to $" + String.format("%.2f", cost) +
                    ", category from \"" + old.category + "\" to \"" + category + "\"," +
                    " and quantity from " + old.quantity + " to " + newQty + ".");
        } else {
            groceryList.put(item, new Item(cost, category, quantity));
            System.out.println("\"" + item + "\" added with cost $" +
                    String.format("%.2f", cost) + ", category \"" + category +
                    "\", quantity " + quantity + ".");
        }
    }

    public void updateQuantity(String item, int newQuantity) {
        if (item == null) {
            System.out.println("Error: Item name cannot be empty.");
            return;
        }
        if (newQuantity < 0) {
            System.out.println("Error: Quantity cannot be negative.");
            return;
        }

        Item it = groceryList.get(item);
        if (it == null) {
            System.out.println("\"" + item + "\" is not in the grocery list.");
            return;
        }
        int old = it.quantity;
        it.quantity = newQuantity;
        System.out.println("\"" + item + "\" quantity updated from " + old + " to " + newQuantity + ".");
    }

    public void removeItem(String item) {
        if (item == null) {
            System.out.println("Error: Cannot remove empty or null item.");
            return;
        }
        if (groceryList.remove(item) != null) {
            System.out.println("\"" + item + "\" removed from the grocery list.");
        } else {
            System.out.println("\"" + item + "\" is not in the grocery list.");
        }
    }

    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.containsKey(item.trim());
    }

    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        int index = 1;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            String name = e.getKey();
            Item it = e.getValue();
            System.out.println(index + ". " + name +
                    " - $" + String.format("%.2f", it.cost) +
                    " [" + it.category + "], qty: " + it.quantity);
            index++;
        }
    }

    public void displayAvailableItems() {
        int index = 1;
        boolean any = false;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            Item it = e.getValue();
            if (it.quantity > 0) {
                String name = e.getKey();
                System.out.println(index + ". " + name +
                        " - $" + String.format("%.2f", it.cost) +
                        " [" + it.category + "], qty: " + it.quantity);
                index++;
                any = true;
            }
        }
        if (!any) System.out.println("No items with positive quantity.");
    }

    public void displayByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }

        int index = 1;
        boolean found = false;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            if (e.getValue().category.equalsIgnoreCase(category)) {
                String name = e.getKey();
                Item it = e.getValue();
                System.out.println(index + ". " + name +
                        " - $" + String.format("%.2f", it.cost) +
                        ", qty: " + it.quantity);
                index++;
                found = true;
            }
        }
        if (!found) System.out.println("No items found in category \"" + category + "\".");
    }

    public double calculateTotalCost() {
        double total = 0.0;
        for (Item it : groceryList.values()) total += it.cost * it.quantity;
        return total;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        System.out.println("Adding Items");
        manager.addItem("Apples", 2.50, "Fruits", 3);
        manager.addItem("Milk", 1.80, "Dairy", 2);
        manager.addItem("Bread", 2.20, "Bakery", 1);
        manager.addItem("Bananas", 1.10, "Fruits", 6);
        manager.addItem("Butter", 3.40, "Dairy", 1);

        System.out.println("\nDisplay All Items");
        manager.displayList();

        System.out.println("\nDisplay by Categories");
        manager.displayByCategory("Dairy");
        manager.displayByCategory("Fruits");

        System.out.println("\nUpdate Quantities");
        manager.updateQuantity("Milk", 5);
        manager.updateQuantity("Apples", 0);
        manager.displayAvailableItems();

        System.out.println("\nRemove Item");
        manager.removeItem("Bread");

        System.out.println("\nFinal Total");
        System.out.println("Total cost: $" + String.format("%.2f", manager.calculateTotalCost()));
    }
}

