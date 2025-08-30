package Task_04;

import java.util.Scanner;

public class CustomerQueueManager {
    private static CustomerQueue customerQueue = new CustomerQueue();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    serveCustomer();
                    break;
                case 3:
                    showQueue();
                    break;
                case 4:
                    exitProgram();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Add customer to queue");
        System.out.println("2. Serve next customer (dequeue)");
        System.out.println("3. Show current queue");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    private static int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static void addCustomer() {
        Customer newCustomer = new Customer();
        customerQueue.enqueue(newCustomer);
        System.out.println();
    }

    private static void serveCustomer() {
        Customer servedCustomer = customerQueue.dequeue();
        if (servedCustomer != null) {
            System.out.println("Successfully served customer ID " + servedCustomer.getId());
        }
        System.out.println();
    }

    private static void showQueue() {
        customerQueue.showQueue();
        System.out.println();
    }

    private static void exitProgram() {
        System.out.println("\nFinal Queue Status");
        if (!customerQueue.isEmpty()) {
            System.out.println("Warning: " + customerQueue.getQueueSize() + " customers still in queue!");
            customerQueue.showQueue();
        } else {
            System.out.println("Queue is empty. All customers have been served.");
        }
        System.out.println("Thank you!");
    }
}
