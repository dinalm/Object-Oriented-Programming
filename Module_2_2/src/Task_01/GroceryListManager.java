package Task_01;

import java.util.ArrayList;

public class GroceryListManager {
    private ArrayList<String> groceryList = new ArrayList<>();

    //Adds an item to the grocery list
    public void addItem(String item) {
        if (item == null) {
            System.out.println("Error: Cannot add empty or null item.");
            return;
        }

        // Check for duplicates
        if (groceryList.contains(item)) {
            System.out.println("\"" + item + "\" is already in the grocery list.");
        } else {
            groceryList.add(item);
            System.out.println("\"" + item + "\" added to the grocery list.");
        }
    }

    // Removes an item from the grocery list
    public void removeItem(String item) {
        if (item == null) {
            System.out.println("Error: Cannot remove empty or null item.");
            return;
        }

        if (groceryList.remove(item)) {
            System.out.println("\"" + item + "\" removed from the grocery list.");
        } else {
            System.out.println("\"" + item + "\" is not in the grocery list.");
        }
    }

    // Displays all items in the grocery list
    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
        } else {
            for (int i = 0; i < groceryList.size(); i++) {
                System.out.println((i + 1) + ". " + groceryList.get(i));
            }
        }
    }

    // Checks if a specific item is present in the grocery list
    public boolean checkItem(String item) {
        if (item == null) {
            return false;
        }
        return groceryList.contains(item);
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        // Add items to the grocery list
        manager.addItem("Apples");
        manager.addItem("Milk");
        manager.addItem("Bread");

        // Display the grocery list
        manager.displayList();
        System.out.println();

        // Check if specific items are present
        String itemToCheck = "Milk";
        boolean isPresent = manager.checkItem(itemToCheck);
        System.out.println("Is \"" + itemToCheck + "\" in the grocery list? " + isPresent);

        // Remove an item from the grocery list
        System.out.println("Removing \"Milk\" from the list...");
        manager.removeItem("Milk");
        System.out.println();

        // Display the updated grocery list
        System.out.println("Updated Grocery List:");
        manager.displayList();
    }
}
