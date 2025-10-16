package Task_02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class CollectionLambdaOperations {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(8);
        numbers.add(20);
        numbers.add(15);
        numbers.add(3);
        numbers.add(12);

        System.out.println("Original List: " + numbers);

        // ========== FILTER EVEN NUMBERS ==========
        List<Integer> numbers1 = new ArrayList<>(numbers);

        // Using removeIf with lambda to remove even numbers (keep odd)
        numbers1.removeIf(n -> n % 2 == 0);
        System.out.println("\nAfter filtering out even numbers (odd only): " + numbers1);

        // Alternative: Keep even numbers only
        List<Integer> evenNumbers = new ArrayList<>(numbers);
        evenNumbers.removeIf(n -> n % 2 != 0);
        System.out.println("Even numbers only: " + evenNumbers);

        // ========== DOUBLE THE ODD NUMBERS ==========
        List<Integer> numbers2 = new ArrayList<>(numbers);

        // Using replaceAll with lambda to double odd numbers
        numbers2.replaceAll(n -> (n % 2 != 0) ? n * 2 : n);
        System.out.println("\nAfter doubling odd numbers: " + numbers2);

        // ========== SUM THE NUMBERS ==========
        List<Integer> numbers3 = new ArrayList<>(numbers);

        // Using forEach with lambda to calculate sum
        int[] sum = {0}; // Using array to allow modification in lambda
        numbers3.forEach(n -> sum[0] += n);
        System.out.println("\nSum of all numbers: " + sum[0]);

        // Alternative using a custom method with lambda
        int total = calculateSum(numbers3, n -> n);
        System.out.println("Sum (using custom method): " + total);

        System.out.println("\n========== ADDITIONAL EXAMPLES ==========");

        // Example 1: Square all numbers
        List<Integer> numbers4 = new ArrayList<>(numbers);
        numbers4.replaceAll(n -> n * n);
        System.out.println("\nSquared numbers: " + numbers4);

        // Example 2: Filter numbers greater than 10
        List<Integer> numbers5 = new ArrayList<>(numbers);
        numbers5.removeIf(n -> n <= 10);
        System.out.println("Numbers greater than 10: " + numbers5);

        // Example 3: Add 5 to each number
        List<Integer> numbers6 = new ArrayList<>(numbers);
        numbers6.replaceAll(n -> n + 5);
        System.out.println("Each number + 5: " + numbers6);

        // Example 4: Find maximum using forEach
        List<Integer> numbers7 = new ArrayList<>(numbers);
        int[] max = {Integer.MIN_VALUE};
        numbers7.forEach(n -> {
            if (n > max[0]) {
                max[0] = n;
            }
        });
        System.out.println("Maximum number: " + max[0]);

        // Example 5: Count even numbers
        List<Integer> numbers8 = new ArrayList<>(numbers);
        int[] evenCount = {0};
        numbers8.forEach(n -> {
            if (n % 2 == 0) {
                evenCount[0]++;
            }
        });
        System.out.println("Count of even numbers: " + evenCount[0]);

        // Example 6: Combined operations
        System.out.println("\n========== COMBINED OPERATIONS ==========");
        List<Integer> combined = new ArrayList<>(numbers);
        System.out.println("Original: " + combined);

        // Step 1: Filter out numbers less than 10
        combined.removeIf(n -> n < 10);
        System.out.println("After filtering (>= 10): " + combined);

        // Step 2: Triple each remaining number
        combined.replaceAll(n -> n * 3);
        System.out.println("After tripling: " + combined);

        // Step 3: Calculate sum
        int[] combinedSum = {0};
        combined.forEach(n -> combinedSum[0] += n);
        System.out.println("Sum of processed numbers: " + combinedSum[0]);

        // Example 7: Using Predicate, UnaryOperator, and Consumer explicitly
        System.out.println("\n========== USING FUNCTIONAL INTERFACES ==========");
        List<Integer> numbers9 = new ArrayList<>(numbers);
        System.out.println("Original: " + numbers9);

        // Define lambda expressions with explicit types
        Predicate<Integer> isEven = n -> n % 2 == 0;
        UnaryOperator<Integer> doubleValue = n -> n * 2;
        Consumer<Integer> printer = n -> System.out.print(n + " ");

        // Apply operations
        numbers9.removeIf(isEven.negate()); // Keep only even
        System.out.print("Even numbers: ");
        numbers9.forEach(printer);
        System.out.println();

        numbers9.replaceAll(doubleValue);
        System.out.print("After doubling: ");
        numbers9.forEach(printer);
        System.out.println();
    }

    // Helper method to calculate sum with a transformation function
    private static int calculateSum(List<Integer> list, UnaryOperator<Integer> transformer) {
        int sum = 0;
        for (Integer num : list) {
            sum += transformer.apply(num);
        }
        return sum;
    }
}