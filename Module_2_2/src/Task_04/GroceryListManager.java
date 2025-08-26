package Task_04;

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


    // Add item with quantity
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
            System.out.println(
                    "\"" + item + "\" already exists. " +
                            "Updating cost from $" + String.format("%.2f", old.cost) + " to $" + String.format("%.2f", cost) +
                            ", category from \"" + old.category + "\" to \"" + category + "\"," +
                            " and quantity from " + old.quantity + " to " + newQty + "."
            );
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


    // Display everything
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

    // Display available items
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
        if (!any) {
            System.out.println("No items");
        }
    }

    // calculate cost
    public double calculateTotalCost() {
        double total = 0.0;
        for (Item it : groceryList.values()) {
            total += it.cost * it.quantity;
        }
        return total;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        manager.addItem("Apples", 2.50, "Fruits", 3);
        manager.addItem("Milk", 1.80, "Dairy", 2);
        manager.addItem("Bread", 2.20, "Bakery", 1);

        System.out.println("\nAll items:");
        manager.displayList();

        System.out.println("\nAvailable items:");
        manager.displayAvailableItems();

        System.out.println("\nIs \"Milk\" present? " + manager.checkItem("Milk"));

        System.out.println("\nUpdate quantities:");
        manager.updateQuantity("Apples", 0);
        manager.updateQuantity("Milk", 5);
        manager.updateQuantity("Bread", 2);

        System.out.println("\nAvailable items after updates:");
        manager.displayAvailableItems();

        System.out.println("\nTotal cost: $" + String.format("%.2f", manager.calculateTotalCost()));

        System.out.println("\nRemoving \"Bread\"...");
        manager.removeItem("Bread");

        System.out.println("\nFinal list:");
        manager.displayList();
    }
}

