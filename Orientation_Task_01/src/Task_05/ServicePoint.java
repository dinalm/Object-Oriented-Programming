package Task_05;

import java.util.LinkedList;

public class ServicePoint {
    private LinkedList<Customer> queue;
    private String name;
    private int minServiceTime;
    private int maxServiceTime;

    public ServicePoint(String name, int minServiceTime, int maxServiceTime) {
        this.queue = new LinkedList<>();
        this.name = name;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
    }

    // Add customer to queue
    public void addToQueue(Customer customer) {
        customer.setStartTime(System.nanoTime());
        queue.addFirst(customer);
        System.out.println(customer + " added to " + name + " queue.");
        System.out.println("Queue size: " + queue.size());
    }

    // Remove customer from queue
    public Customer removeFromQueue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeLast();
    }

    // Serve all customers in the queue
    public void serve() {
        System.out.println("\nStarting service at " + name);
        System.out.println("Queue has " + queue.size() + " customers waiting.\n");

        int customerCount = 0;

        while (!queue.isEmpty()) {
            // Get next customer from queue
            Customer customer = removeFromQueue();
            customerCount++;

            if (customer != null) {
                // Mark service start time
                customer.setServiceStartTime(System.nanoTime());

                // Calculate random service time
                int serviceTimeMs = minServiceTime + (int)(Math.random() * (maxServiceTime - minServiceTime + 1));

                System.out.println("Now serving " + customer);
                System.out.println("Estimated service time: " + serviceTimeMs + " ms");

                // Simulate service time
                try {
                    Thread.sleep(serviceTimeMs);
                } catch (InterruptedException e) {
                    System.err.println("Service interrupted: " + e.getMessage());
                }

                // Mark service end time
                customer.setServiceEndTime(System.nanoTime());

                // Calculate and display times
                long waitingTimeNs = customer.getWaitingTime();
                long serviceTimeNs = customer.getServiceTime();
                long responseTimeNs = customer.getResponseTime();

                double waitingTimeMs = waitingTimeNs / 1_000_000.0;
                double actualServiceTimeMs = serviceTimeNs / 1_000_000.0;
                double responseTimeMs = responseTimeNs / 1_000_000.0;

                System.out.println("Service completed for " + customer);
                System.out.println("Waiting time in queue: " + String.format("%.3f", waitingTimeMs) + " ms");
                System.out.println("Actual service time: " + String.format("%.3f", actualServiceTimeMs) + " ms");
                System.out.println("Total response time: " + String.format("%.3f", responseTimeMs) + " ms");
                System.out.println("Customers remaining in queue: " + queue.size());
                System.out.println();
            }
        }

        System.out.println("Service completed at " + name);
        System.out.println("Total customers served: " + customerCount);
        System.out.println("Queue is now empty.\n");
    }

    // Get current queue status
    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println(name + " queue is empty.");
        } else {
            System.out.println(name + " queue status:");
            for (int i = 0; i < queue.size(); i++) {
                Customer c = queue.get(i);
                long currentWaitTime = System.nanoTime() - c.getStartTime();
                double currentWaitTimeMs = currentWaitTime / 1_000_000.0;
                System.out.println("  Position " + (i + 1) + ": " + c + " (waiting: " + String.format("%.3f", currentWaitTimeMs) + " ms)");
            }
            System.out.println("Queue size: " + queue.size());
        }
    }

    public int getQueueSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public String getName() {
        return name;
    }
}
