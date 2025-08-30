package Task_04;

import java.util.LinkedList;

public class CustomerQueue {
    private LinkedList<Customer> queue;

    public CustomerQueue() {
        this.queue = new LinkedList<>();
    }

    // Add customer to queue
    public void enqueue(Customer customer) {
        customer.setStartTime(System.nanoTime());
        queue.addFirst(customer);
        System.out.println("Customer ID " + customer.getId() + " added to queue.");
        System.out.println("Queue size: " + queue.size());
    }

    // Remove customer from queue
    public Customer dequeue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! No customers to serve.");
            return null;
        }

        Customer customer = queue.removeLast();
        customer.setEndTime(System.nanoTime());

        long timeInQueue = customer.getTimeSpent();
        double timeInQueueMs = timeInQueue / 1_000_000.0;

        System.out.println("Customer ID " + customer.getId() + " removed from queue.");
        System.out.println("Time spent in queue: " + timeInQueue + " nanoseconds");
        System.out.println("Time spent in queue: " + String.format("%.3f", timeInQueueMs) + " milliseconds");
        System.out.println("Queue size: " + queue.size());

        return customer;
    }

    // Show current queue status
    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Current queue (from newest to oldest):");
            for (int i = 0; i < queue.size(); i++) {
                Customer c = queue.get(i);
                long currentWaitTime = System.nanoTime() - c.getStartTime();
                double currentWaitTimeMs = currentWaitTime / 1_000_000.0;
                System.out.println("  Position " + (i + 1) + ": " + c +
                        " (waiting: " + String.format("%.3f", currentWaitTimeMs) + " ms)");
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
}
