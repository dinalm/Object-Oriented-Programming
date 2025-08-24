import java.util.Scanner;

public class Task_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user
        System.out.print("Enter a binary number: ");
        String binary = scanner.nextLine();

        int decimal = 0;
        int power = 0;

        // Process from right to left
        for (int i = binary.length() - 1; i >= 0; i--) {
            char bit = binary.charAt(i);

            if (bit == '1') {
                decimal += (int) Math.pow(2, power);
            }
            power++;
        }

        // Display result
        System.out.printf("Binary %s = Decimal %d%n", binary, decimal);

        scanner.close();
    }
}

