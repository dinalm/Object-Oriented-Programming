import java.util.Scanner;

public class Task_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user
        System.out.print("Enter temperature in Fahrenheit: ");
        double fahrenheit = Double.parseDouble(scanner.nextLine());

        // Convert to Celsius
        double celsius = (fahrenheit - 32) * 5.0 / 9.0;

        // Print with one decimal place
        System.out.printf("Temperature in Celsius: %.1f%n", celsius);
    }
}
