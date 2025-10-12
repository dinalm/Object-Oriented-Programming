package Task_02;

public class ShoppingCart {

    private static class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private java.util.List<Item> items;

    public ShoppingCart() {
        this.items = new java.util.ArrayList<>();
    }

    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    public void removeItem(String name) {
        items.removeIf(item -> item.name.equals(name));
    }

    public int getItemCount() {
        return items.size();
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(item -> item.price)
                .sum();
    }
}
