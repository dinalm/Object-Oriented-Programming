package Task_02;

import java.util.HashMap;

public class GroceryListManager {
    private HashMap<String, Double> groceryList = new HashMap<>();

    // Adds the item with cost to the grocery list
    public void addItem(String item, double cost) {
        if (item == null) {
            System.out.println("Error: Cannot add empty or null item.");
            return;
        }

        if (groceryList.containsKey(item)) {
            double oldCost = groceryList.get(item);
            groceryList.put(item, cost);
            System.out.println("\"" + item + "\" is already in the grocery list. Updating cost from $"
                    + String.format("%.2f", oldCost) + " to $" + String.format("%.2f", cost));
        } else {
            groceryList.put(item, cost);
            System.out.println("\"" + item + "\" added to the grocery list with cost $"
                    + String.format("%.2f", cost));
        }
    }

    // Removes an item from the grocery list
    public void removeItem(String item) {
        if (item == null) {
            System.out.println("Error: Cannot remove empty or null item.");
            return;
        }

        if (groceryList.containsKey(item)) {
            groceryList.remove(item);
            System.out.println("\"" + item + "\" removed from the grocery list.");
        } else {
            System.out.println("\"" + item + "\" is not in the grocery list.");
        }
    }

    // Displays all items in the grocery list with their costs
    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
        } else {
            int index = 1;
            for (String item : groceryList.keySet()) {
                System.out.println(index + ". " + item + " - $"
                        + String.format("%.2f", groceryList.get(item)));
                index++;
            }
        }
    }

    // Checks if a specific item is present in the grocery list
    public boolean checkItem(String item) {
        if (item == null) {
            return false;
        }
        return groceryList.containsKey(item);
    }

    // Calculating the total cost of all items
    public double calculateTotalCost() {
        double total = 0.0;
        for (double cost : groceryList.values()) {
            total += cost;
        }
        return total;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        // Add items
        manager.addItem("Apples", 2.50);
        manager.addItem("Milk", 1.80);
        manager.addItem("Bread", 2.20);

        System.out.println();

        // Display list
        manager.displayList();
        System.out.println();

        // Check item
        String itemToCheck = "Milk";
        System.out.println("Is \"" + itemToCheck + "\" in the grocery list? " + manager.checkItem(itemToCheck));

        System.out.println();

        // Remove item
        manager.removeItem("Milk");
        System.out.println();

        // Display updated list
        System.out.println("Updated Grocery List:");
        manager.displayList();

        // Show total cost
        System.out.println("\nTotal cost of all items: $" + String.format("%.2f", manager.calculateTotalCost()));
    }
}
