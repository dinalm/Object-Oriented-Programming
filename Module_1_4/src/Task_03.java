import java.util.Scanner;

public class Task_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for size of array
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        // Input elements
        System.out.println("Enter the integers into the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        // New array to store unique numbers
        int[] unique = new int[size];
        int uniqueCount = 0;

        // Process each element
        for (int i = 0; i < size; i++) {
            boolean alreadyExists = false;

            // Check if array is already in unique
            for (int j = 0; j < uniqueCount; j++) {
                if (array[i] == unique[j]) {
                    alreadyExists = true;
                    break;
                }
            }

            // If not duplicate, add to unique
            if (!alreadyExists) {
                unique[uniqueCount] = array[i];
                uniqueCount++;
            }
        }

        System.out.println("\nThe array without duplicates:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(unique[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}

