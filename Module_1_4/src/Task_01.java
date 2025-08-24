import java.util.Scanner;
import java.util.Random;

public class Task_01 {
    public static void main(String[] args) {
        // Hard-coded name arrays
        String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "William", "Sophia", "James", "Isabella"};

        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ask user for number of names to generate
        System.out.print("How many random names would you like to generate? ");
        int numberOfNames = scanner.nextInt();

        System.out.println("\nGenerated names:");

        // Generate random names
        for (int i = 0; i < numberOfNames; i++) {
            // Choose random index for first name
            int firstNameIndex = random.nextInt(firstNames.length);

            // Choose random index for last name
            int lastNameIndex = random.nextInt(lastNames.length);

            // Create and print full name
            String fullName = firstNames[firstNameIndex] + " " + lastNames[lastNameIndex];
            System.out.println(fullName);
        }

        scanner.close();
    }
}
