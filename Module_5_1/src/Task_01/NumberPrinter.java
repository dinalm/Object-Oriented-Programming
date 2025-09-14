package Task_01;

public class NumberPrinter {
    private static final Object lock = new Object();
    private static int currentNumber = 1;
    private static int maxNumber;
    private static boolean oddTurn = true;

    public static void main(String[] args) {
        // Set the range of numbers to print
        maxNumber = 20;

        // Create odd and even number printing threads
        Thread oddThread = new Thread(new OddPrinter(), "Odd Thread");
        Thread evenThread = new Thread(new EvenPrinter(), "Even Thread");

        // Start both threads
        oddThread.start();
        evenThread.start();

        // Wait for both threads to complete
        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        System.out.println("Printing complete.");
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while ((!oddTurn || currentNumber % 2 == 0) && currentNumber <= maxNumber) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    // Check if the current number exceeds the max limit
                    if (currentNumber > maxNumber) {
                        lock.notifyAll();
                        break;
                    }

                    // Print odd number
                    if (currentNumber % 2 == 1) {
                        System.out.println("Odd Thread: " + currentNumber);
                        currentNumber++;
                        oddTurn = false; // Switch to even thread's turn
                        lock.notifyAll();
                    }
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while ((oddTurn || currentNumber % 2 == 1) && currentNumber <= maxNumber) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    // Check if the current number exceeds the max limit
                    if (currentNumber > maxNumber) {
                        lock.notifyAll();
                        break;
                    }

                    // Print even number
                    if (currentNumber % 2 == 0) {
                        System.out.println("Even Thread: " + currentNumber);
                        currentNumber++;
                        oddTurn = true;
                        lock.notifyAll();
                    }
                }
            }
        }
    }
}
