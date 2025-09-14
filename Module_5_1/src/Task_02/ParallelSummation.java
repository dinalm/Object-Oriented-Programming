package Task_02;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ParallelSummation {
    private static final int ARRAY_SIZE = 100000;
    private static int[] numbers;
    private static long totalSum = 0;
    private static final Object sumLock = new Object();

    public static void main(String[] args) {
        // Generate array of random numbers
        System.out.println("Generating array of " + ARRAY_SIZE + " random numbers...");
        generateRandomNumbers();

        // Get number of available processor cores
        int numCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processor cores: " + numCores);

        // Calculate sum using single thread for comparison
        long startTime = System.nanoTime();
        long singleThreadSum = calculateSumSingleThread();
        long singleThreadTime = System.nanoTime() - startTime;

        System.out.println("\nSingle-threaded calculation:");
        System.out.println("Sum: " + singleThreadSum);
        System.out.println("Time: " + (singleThreadTime / 1_000_000.0) + " ms");

        // Calculate sum using multiple threads
        startTime = System.nanoTime();
        long multiThreadSum = calculateSumMultiThread(numCores);
        long multiThreadTime = System.nanoTime() - startTime;

        System.out.println("\nMulti-threaded calculation (" + numCores + " threads):");
        System.out.println("Sum: " + multiThreadSum);
        System.out.println("Time: " + (multiThreadTime / 1_000_000.0) + " ms");

        // Performance comparison
        double speedup = (double) singleThreadTime / multiThreadTime;
        System.out.println("\nPerformance comparison:");
        System.out.println("Speedup: " + String.format("%.2f", speedup) + "x");
        System.out.println("Results match: " + (singleThreadSum == multiThreadSum));
    }

    private static void generateRandomNumbers() {
        numbers = new int[ARRAY_SIZE];
        Random random = new Random(42);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            numbers[i] = random.nextInt(1000);
        }

        System.out.println("Array generated with numbers ranging from 0 to 999");
    }

    private static long calculateSumSingleThread() {
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static long calculateSumMultiThread(int numThreads) {
        totalSum = 0;
        CountDownLatch latch = new CountDownLatch(numThreads);

        // Calculate chunk size for each thread
        int chunkSize = ARRAY_SIZE / numThreads;
        int remainder = ARRAY_SIZE % numThreads;

        System.out.println("Dividing array into " + numThreads + " chunks:");

        // Create and start threads
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ? startIndex + chunkSize + remainder : startIndex + chunkSize;

            System.out.println("Thread " + (i + 1) + ": processing indices " + startIndex + " to " + (endIndex - 1) + " (" + (endIndex - startIndex) + " elements)");

            Thread thread = new Thread(new SumCalculator(startIndex, endIndex, latch, i + 1));
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        return totalSum;
    }

    static class SumCalculator implements Runnable {
        private final int startIndex;
        private final int endIndex;
        private final CountDownLatch latch;
        private final int threadId;

        public SumCalculator(int startIndex, int endIndex, CountDownLatch latch, int threadId) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.latch = latch;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            long localSum = 0;

            for (int i = startIndex; i < endIndex; i++) {
                localSum += numbers[i];
            }

            System.out.println("Thread " + threadId + " calculated partial sum: " + localSum);

            synchronized (sumLock) {
                totalSum += localSum;
            }

            latch.countDown();
        }
    }
}
