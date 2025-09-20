package Task_02;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class ThreadSafeList<T> {
    private final ArrayList<T> list;

    public ThreadSafeList() {
        this.list = new ArrayList<>();
    }

    public synchronized void add(T element) {
        list.add(element);
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized boolean remove(T element) {
        return list.remove(element);
    }
}

public class ThreadSafetyTester {
    public static void testThreadSafety() {
        ThreadSafeList<String> safeList = new ThreadSafeList<>();
        int numThreads = 5;
        int operationsPerThread = 100;
        CountDownLatch latch = new CountDownLatch(numThreads);

        // Create threads that add elements
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    safeList.add("Thread-" + threadId + "-Item-" + j);
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int expectedSize = numThreads * operationsPerThread;
        int actualSize = safeList.size();

        System.out.println("Expected size: " + expectedSize);
        System.out.println("Actual size: " + actualSize);
        System.out.println("Test " + (expectedSize == actualSize ? "PASSED" : "FAILED"));
    }

    public static void main(String[] args) {
        testThreadSafety();
    }
}
