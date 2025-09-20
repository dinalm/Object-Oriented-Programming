package Task_01;

import java.util.Random;

class TheaterReservationSystem {
    private int availableSeats;
    private final int totalSeats;

    public TheaterReservationSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public synchronized boolean reserveSeats(int customerId, int requestedSeats) {
        try {
            // Check if enough seats are available
            if (availableSeats >= requestedSeats) {

                Thread.sleep(10);

                // Reserve the seats
                availableSeats -= requestedSeats;
                System.out.println("Customer " + customerId + " reserved " + requestedSeats + " tickets.");
                return true;
            } else {
                System.out.println("Customer " + customerId + " couldn't reserve " + requestedSeats + " tickets.");
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public synchronized int getAvailableSeats() {
        return availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}

class Customer extends Thread {
    private final int customerId;
    private final int requestedSeats;
    private final TheaterReservationSystem reservationSystem;

    public Customer(int customerId, int requestedSeats, TheaterReservationSystem reservationSystem) {
        this.customerId = customerId;
        this.requestedSeats = requestedSeats;
        this.reservationSystem = reservationSystem;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        reservationSystem.reserveSeats(customerId, requestedSeats);
    }
}

public class TicketReservationDemo {
    public static void main(String[] args) {
        // Create a theater with 10 seats
        TheaterReservationSystem theater = new TheaterReservationSystem(10);

        // Create multiple customers with different seat requirements
        Customer[] customers = {
                new Customer(1, 2, theater),
                new Customer(2, 1, theater),
                new Customer(3, 3, theater),
                new Customer(4, 1, theater),
                new Customer(5, 2, theater),
                new Customer(6, 2, theater),
                new Customer(7, 1, theater),
                new Customer(8, 1, theater),
                new Customer(9, 3, theater),
                new Customer(10, 3, theater),
                new Customer(11, 2, theater),
                new Customer(12, 4, theater),
                new Customer(13, 1, theater),
                new Customer(14, 4, theater),
                new Customer(15, 3, theater)
        };

        // Start all customer threads
        for (Customer customer : customers) {
            customer.start();
        }

        // Wait for all customers to finish their reservation attempts
        for (Customer customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("\nReservation process completed.");
        System.out.println("Seats remaining: " + theater.getAvailableSeats() + "/" + theater.getTotalSeats());
    }
}
