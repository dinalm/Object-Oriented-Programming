package Task_05;

import java.util.Scanner;

public class ServicePointTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ServicePoint servicePoint = new ServicePoint("Customer Service Desk", 100, 500);

        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Generate customers automatically");
            System.out.println("2. Add single customer manually");
            System.out.println("3. Show current queue");
            System.out.println("4. Start serving all customers");
            System.out.println("5. Run automated demo");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        generateCustomersInteractive(scanner, servicePoint);
                        break;
                    case 2:
                        addSingleCustomer(servicePoint);
                        break;
                    case 3:
                        servicePoint.showQueue();
                        System.out.println();
                        break;
                    case 4:
                        servicePoint.serve();
                        break;
                    case 5:
                        runAutomatedDemo();
                        break;
                    case 6:
                        System.out.println("Exiting program...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void generateCustomersInteractive(Scanner scanner, ServicePoint servicePoint) {
        System.out.print("Enter number of customers to generate: ");
        try {
            int count = scanner.nextInt();
            scanner.nextLine();
            if (count > 0) {
                CustomerGenerator.generateCustomers(servicePoint, count);
            } else {
                System.out.println("Please enter a positive number.\n");
            }
        } catch (Exception e) {
            System.out.println("Invalid number. Please try again.\n");
            scanner.nextLine();
        }
    }

    private static void addSingleCustomer(ServicePoint servicePoint) {
        Customer customer = new Customer();
        servicePoint.addToQueue(customer);
        System.out.println();
    }

    private static void runAutomatedDemo() {

        // Create a new service point
        ServicePoint demoServicePoint = new ServicePoint("Demo Service Point", 200, 800);

        // Generate 5 customers
        CustomerGenerator.generateCustomersWithInterval(demoServicePoint, 5, 50, 150);

        // Show queue before service
        demoServicePoint.showQueue();
        System.out.println();

        // Serve all customers
        demoServicePoint.serve();
    }
}
