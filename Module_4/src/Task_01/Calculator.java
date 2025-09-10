package Task_01;

public class Calculator {
    private int value;

    // Constructor to initialize the calculator
    public Calculator() {
        this.value = 0;
    }

    // Method to reset the calculator
    public void reset() {
        this.value = 0;
    }

    // Method to add a positive integer
    public void add(int number) throws IllegalArgumentException {
        if (number < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed.");
        }
        this.value += number;
    }

    // Method to get the current value
    public int getValue() {
        return this.value;
    }

    // Temporary main method for testing
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        try {
            calc.add(5);
            calc.add(10);
            calc.add(20);
            System.out.println("Current value: " + calc.getValue());

            calc.reset();
            System.out.println("After reset: " + calc.getValue());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

