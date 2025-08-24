import java.util.Scanner;

public class Task_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for coefficients
        System.out.print("Enter coefficient a: ");
        double a = scanner.nextDouble();

        System.out.print("Enter coefficient b: ");
        double b = scanner.nextDouble();

        System.out.print("Enter coefficient c: ");
        double c = scanner.nextDouble();

        // Calculate the discriminant
        double dst = b * b - 4 * a * c;

        // Check the discriminant to determine root type
        if (dst > 0) {
            double root1 = (-b + Math.sqrt(dst)) / (2 * a);
            double root2 = (-b - Math.sqrt(dst)) / (2 * a);
            System.out.println("The equation has two real roots: " + root1 + " and " + root2);
        } else if (dst == 0) {
            double root = -b / (2 * a);
            System.out.println("The equation has one real root: " + root);
        } else {
            System.out.println("No real roots");
        }

        scanner.close();
    }
}
