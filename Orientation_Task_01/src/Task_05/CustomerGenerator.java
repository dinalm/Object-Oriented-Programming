package Task_05;

public class CustomerGenerator {

    // Generate specified number of customers and add them to the service point
    public static void generateCustomers(ServicePoint servicePoint, int numberOfCustomers) {
        System.out.println("Generating " + numberOfCustomers + " customers");

        for (int i = 0; i < numberOfCustomers; i++) {
            Customer customer = new Customer();
            servicePoint.addToQueue(customer);

            try {
                Thread.sleep(10 + (int)(Math.random() * 50));
            } catch (InterruptedException e) {
                System.err.println("Customer generation interrupted: " + e.getMessage());
            }
        }

        System.out.println("All " + numberOfCustomers + " customers have been added to the queue.\n");
    }

    // Generate customers with custom arrival intervals
    public static void generateCustomersWithInterval(ServicePoint servicePoint, int numberOfCustomers, int minInterval, int maxInterval) {
        System.out.println("Generating " + numberOfCustomers + " customers with custom intervals");

        for (int i = 0; i < numberOfCustomers; i++) {
            Customer customer = new Customer();
            servicePoint.addToQueue(customer);

            if (i < numberOfCustomers - 1) {
                int interval = minInterval + (int)(Math.random() * (maxInterval - minInterval + 1));
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    System.err.println("Customer generation interrupted: " + e.getMessage());
                }
            }
        }

        System.out.println("All " + numberOfCustomers + " customers have been added to the queue.\n");
    }
}
