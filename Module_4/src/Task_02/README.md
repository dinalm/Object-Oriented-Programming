# Calculator 

This project demonstrates how to use **GitHub Copilot** in writing and explaining code.  
The program implements a simple `Calculator` class that can sum positive integers.  
If a negative integer is added, the calculator throws an exception.  
The calculator can also be reset to zero.

---

## Source Code

```java
public class Calculator {
    private int value;

    // Constructor
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

            // Uncommenting this will throw an exception
            // calc.add(-3);

            calc.reset();
            System.out.println("After reset: " + calc.getValue());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

```
## Explanation of the Code

The `Calculator` class models a simple integer calculator:

- **Field**
    - `private int value;` → stores the current total of the calculator

- **Constructor**
    - Initializes the calculator to `0`

- **Methods**
    - `reset()` → sets the current value back to zero
    - `add(int number)` → adds a positive integer to the current value
        - If the input is negative, it throws an `IllegalArgumentException`
    - `getValue()` → returns the current total

- **Main Method (temporary)**
    - Demonstrates usage by adding numbers and printing the results
    - Shows how an exception is thrown if a negative number is added
    - Resets the calculator and prints the cleared value  
