import java.util.Scanner;

public class Task_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the two legs
        System.out.print("Enter the length of the first leg: ");
        double a = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the length of the second leg: ");
        double b = Double.parseDouble(scanner.nextLine());

        // Calculate hypotenuse
        double hypotenuse = Math.sqrt(a * a + b * b);

        // Print result with two decimals
        System.out.printf("The length of the hypotenuse is %.2f%n", hypotenuse);
    }
}

