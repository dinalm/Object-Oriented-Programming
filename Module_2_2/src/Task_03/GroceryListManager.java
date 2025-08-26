package Task_03;

import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {
    private static final class Item {
        double cost;
        String category;

        Item(double cost, String category) {
            this.cost = cost;
            this.category = category;
        }
    }

    private final HashMap<String, Item> groceryList = new HashMap<>();

    // Adds an item with cost and category
    public void addItem(String item, double cost, String category) {
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

        if (groceryList.containsKey(item)) {
            Item old = groceryList.get(item);
            groceryList.put(item, new Item(cost, category));
            System.out.println("\"" + item + "\" is already in the grocery list. " +
                    "Updating cost from $" + String.format("%.2f", old.cost) +
                    " to $" + String.format("%.2f", cost) +
                    " and category from \"" + old.category + "\" to \"" + category + "\".");
        } else {
            groceryList.put(item, new Item(cost, category));
            System.out.println("\"" + item + "\" added to the grocery list with cost $" +
                    String.format("%.2f", cost) + " in category \"" + category + "\".");
        }
    }

    // Removes an item
    public void removeItem(String item) {
        if (item == null || item.trim().isEmpty()) {
            System.out.println("Error: Cannot remove empty or null item.");
            return;
        }
        item = item.trim();
        if (groceryList.remove(item) != null) {
            System.out.println("\"" + item + "\" removed from the grocery list.");
        } else {
            System.out.println("\"" + item + "\" is not in the grocery list.");
        }
    }

    // Checks if an item exists
    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.containsKey(item.trim());
    }

    // Displays all items
    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        int index = 1;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            String name = e.getKey();
            Item it = e.getValue();
            System.out.println(index + ". " + name + " - $" + String.format("%.2f", it.cost)
                    + " [" + it.category + "]");
            index++;
        }
    }

    // Displays items in a given category
    public void displayByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }
        category = category.trim();

        int index = 1;
        boolean found = false;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            if (e.getValue().category.equalsIgnoreCase(category)) {
                String name = e.getKey();
                Item it = e.getValue();
                System.out.println(index + ". " + name + " - $" + String.format("%.2f", it.cost));
                index++;
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in category \"" + category + "\".");
        }
    }

    // Calculates total cost
    public double calculateTotalCost() {
        double total = 0.0;
        for (Item it : groceryList.values()) {
            total += it.cost;
        }
        return total;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        manager.addItem("Apples", 2.50, "Fruits");
        manager.addItem("Milk", 1.80, "Dairy");
        manager.addItem("Bread", 2.20, "Bakery");
        manager.addItem("Cheese", 3.40, "Dairy");
        manager.addItem("Butter", 4.40, "Dairy");

        System.out.println("\nFull list:");
        manager.displayList();

        // Check if item exists
        String checkItem = "Milk";
        System.out.println("\nIs \"" + checkItem + "\" in the grocery list? " + manager.checkItem(checkItem));

        // Remove item
        System.out.println("\nRemoving \"" + checkItem + "\"...");
        manager.removeItem(checkItem);

        // Display after removal
        System.out.println("\nUpdated list:");
        manager.displayList();

        // Show category
        System.out.println("\nDairy category:");
        manager.displayByCategory("Dairy");

        // Total cost
        System.out.println("\nTotal cost: $" + String.format("%.2f", manager.calculateTotalCost()));
    }
}


