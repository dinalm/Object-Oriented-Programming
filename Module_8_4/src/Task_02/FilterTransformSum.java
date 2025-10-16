package Task_02;

import java.util.Arrays;
import java.util.List;

public class FilterTransformSum {
    public static void main(String[] args) {
        // Given list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Original List: " + numbers);

        // Perform operations: filter odd, double them, and sum
        int result = numbers.stream()
                .filter(n -> n % 2 != 0)     // Keep only odd numbers
                .map(n -> n * 2)              // Double each number
                .mapToInt(Integer::intValue)  // Convert to IntStream
                .sum();                       // Calculate sum

        System.out.println("\nOperations:");
        System.out.println("1. Filter out even numbers (keep odd)");
        System.out.println("2. Double each remaining number");
        System.out.println("3. Sum the results");
        System.out.println("\nFinal Result: " + result);

        // Show step-by-step breakdown
        System.out.println("\n--- Step-by-Step Breakdown ---");

        List<Integer> oddNumbers = numbers.stream()
                .filter(n -> n % 2 != 0)
                .toList();
        System.out.println("After filtering (odd only): " + oddNumbers);

        List<Integer> doubled = oddNumbers.stream()
                .map(n -> n * 2)
                .toList();
        System.out.println("After doubling: " + doubled);

        int sum = doubled.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum: " + sum);

        // Additional examples with different data
        System.out.println("\n--- Additional Examples ---");

        List<Integer> numbers2 = Arrays.asList(15, 22, 31, 44, 55, 68, 71);
        int result2 = processNumbers(numbers2);
        System.out.println("\nList: " + numbers2);
        System.out.println("Result: " + result2);

        List<Integer> numbers3 = Arrays.asList(100, 101, 102, 103, 104, 105);
        int result3 = processNumbers(numbers3);
        System.out.println("\nList: " + numbers3);
        System.out.println("Result: " + result3);
    }

    // Reusable method that encapsulates the functional pipeline
    public static int processNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
