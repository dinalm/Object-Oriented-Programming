package Task_02;

import java.io.*;

public class FibonacciCSVGenerator {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("fibonacci.csv");

            // Write CSV header
            writer.write("Position,Fibonacci Number\n");

            long first = 0;
            long second = 1;

            // Write first two numbers
            writer.write("1," + first + "\n");
            writer.write("2," + second + "\n");

            // Generate and write remaining 58 numbers
            for (int i = 3; i <= 60; i++) {
                long next = first + second;
                writer.write(i + "," + next + "\n");

                first = second;
                second = next;
            }

            writer.close();
            System.out.println("Fibonacci sequence of 60 numbers generated and saved to fibonacci.csv");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
