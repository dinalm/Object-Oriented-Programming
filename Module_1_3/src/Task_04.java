import java.util.Scanner;

public class Task_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Keep repeating until the user gets all 10 correct
        while (true) {
            int score = 0;

            // Ask 10 random multiplication questions
            for (int i = 1; i <= 10; i++) {
                int num1 = (int) (Math.random() * 10) + 1;
                int num2 = (int) (Math.random() * 10) + 1;

                System.out.print("Question " + i + ": What is " + num1 + " x " + num2 + "? ");
                int answer = scanner.nextInt();

                if (answer == num1 * num2) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong. The correct answer is " + (num1 * num2));
                }
            }

            // Check the score after 10 questions
            if (score == 10) {
                System.out.println("\nCongratulations! You mastered the multiplication tables!");
                break;
            } else {
                System.out.println("\nYou scored " + score + " out of 10. Let's try again!\n");
            }
        }

        scanner.close();
    }
}
