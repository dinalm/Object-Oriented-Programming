import java.util.Scanner;

public class Task_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user input in grams
        System.out.print("Weight (g): ");
        double grams = Double.parseDouble(scanner.nextLine());

        // Constants
        final double LUOTI_GRAMS = 13.28;
        final double NAULA_GRAMS = 32 * LUOTI_GRAMS;
        final double LEIVISKA_GRAMS = 20 * NAULA_GRAMS;

        // Calculation
        int leiviska = (int)(grams / LEIVISKA_GRAMS);
        int naula = (int)((grams - leiviska * LEIVISKA_GRAMS) / NAULA_GRAMS);
        double luoti = (grams - leiviska * LEIVISKA_GRAMS - naula * NAULA_GRAMS) / LUOTI_GRAMS;

        System.out.printf("%.0f grams is %d leiviskä, %d naula, and %.2f luoti.%n", grams, leiviska, naula, luoti);
    }
}

