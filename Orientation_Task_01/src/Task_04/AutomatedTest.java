package Task_04;

public class AutomatedTest {
    public static void runAutomatedTests() {
        CustomerQueue testQueue = new CustomerQueue();

        // Add multiple customers
        for (int i = 0; i < 3; i++) {
            Customer customer = new Customer();
            testQueue.enqueue(customer);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nCurrent queue status:");
        testQueue.showQueue();

        // Serve all customers
        System.out.println("\nServing all customers (FIFO order)");
        while (!testQueue.isEmpty()) {
            testQueue.dequeue();
            System.out.println();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Try to dequeue from empty queue
        System.out.println("Attempting to serve from empty queue");
        testQueue.dequeue();

        System.out.println("\n=== Automated tests completed ===");
    }

    public static void main(String[] args) {
        runAutomatedTests();
    }
}
