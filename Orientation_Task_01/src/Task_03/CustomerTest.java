package Task_03;

public class CustomerTest {
    public static void main(String[] args) {

        // Create customers with automatic ID assignment
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        System.out.println("Customer 1 ID: " + customer1.getId());
        System.out.println("Customer 2 ID: " + customer2.getId());

        System.out.println();

        // Test with manual time values
        customer1.setStartTime(1000);
        customer1.setEndTime(5000);

        customer2.setStartTime(2000);
        customer2.setEndTime(7000);

        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println("Customer 1 time spent: " + customer1.getTimeSpent() + " ms");
        System.out.println("Customer 2 time spent: " + customer2.getTimeSpent() + " ms");
        System.out.println();

        // Test with System.currentTimeMillis()
        Customer customer3 = new Customer();
        customer3.setStartTime(System.currentTimeMillis());

        // Simulate processing time
        try {
            Thread.sleep(100); // Sleep for 100ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        customer3.setEndTime(System.currentTimeMillis());
        System.out.println(customer3);
        System.out.println("Customer 3 time spent: " + customer3.getTimeSpent() + " ms");
        System.out.println();

        // Test with System.nanoTime() for high precision
        Customer customer4 = new Customer();
        long nanoStart = System.nanoTime();
        customer4.setStartTime(nanoStart);

        // Simulate quick operation
        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }

        long nanoEnd = System.nanoTime();
        customer4.setEndTime(nanoEnd);

        System.out.println("Customer 4 ID: " + customer4.getId());
        System.out.println("Start time (nano): " + customer4.getStartTime());
        System.out.println("End time (nano): " + customer4.getEndTime());
        System.out.println("Time spent: " + customer4.getTimeSpent() + " nanoseconds");
        System.out.println("Time spent: " + (customer4.getTimeSpent() / 1_000_000.0) + " milliseconds");
        System.out.println();

        // Test constructor with parameters
        long currentTime = System.currentTimeMillis();
        Customer customer5 = new Customer(currentTime);
        Customer customer6 = new Customer(currentTime, currentTime + 2000);

        customer5.setEndTime(currentTime + 1500);

        System.out.println(customer5);
        System.out.println(customer6);
        System.out.println();

        // Edge case
        Customer customer7 = new Customer();
        customer7.setStartTime(5000);
        customer7.setEndTime(3000);

        System.out.println(customer7);
        System.out.println("Time spent (negative): " + customer7.getTimeSpent() + " ms");
        System.out.println();
    }
}
