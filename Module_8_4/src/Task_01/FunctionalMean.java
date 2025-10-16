package Task_01;

import java.util.Arrays;

public class FunctionalMean {
    public static void main(String[] args) {
        // Test array
        int[] numbers = {10, 20, 30, 40, 50};

        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Mean: " + calculateMean(numbers));

        // Additional test cases
        System.out.println("\nAdditional Examples:");

        double[] decimals = {5.5, 10.2, 15.8, 20.1, 8.4};
        System.out.println("Array: " + Arrays.toString(decimals));
        System.out.println("Mean: " + calculateMean(decimals));

        int[] negatives = {-10, -5, 0, 5, 10};
        System.out.println("Array: " + Arrays.toString(negatives));
        System.out.println("Mean: " + calculateMean(negatives));
    }

    // Calculate mean using Stream API (functional approach)
    public static double calculateMean(int[] array) {
        return Arrays.stream(array)
                .average()
                .orElse(0.0);
    }

    // Overloaded version for double arrays
    public static double calculateMean(double[] array) {
        return Arrays.stream(array)
                .average()
                .orElse(0.0);
    }
}
