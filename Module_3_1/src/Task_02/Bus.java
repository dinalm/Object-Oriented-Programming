package Task_02;

import Task_01.Car;

public class Bus extends Car {
    private int currentPassengers;
    private int maxCapacity;

    // Constructor
    public Bus(String typeName) {
        super(typeName);
        this.currentPassengers = 0;
        this.maxCapacity = 40;
    }

    // Constructor
    public Bus(String typeName, int maxCapacity) {
        super(typeName);
        this.currentPassengers = 0;
        this.maxCapacity = maxCapacity;
    }

    // Constructor with initial gasoline, speed, and capacity
    public Bus(String typeName, double gasolineLevel, double speed, int maxCapacity) {
        super(typeName, gasolineLevel, speed);
        this.currentPassengers = 0;
        this.maxCapacity = maxCapacity;
    }

    // Passengers to enter the bus
    public boolean passengerEnter(int numberOfPassengers) {
        if (numberOfPassengers <= 0) {
            return false;
        }
        if (currentPassengers + numberOfPassengers <= maxCapacity) {
            currentPassengers += numberOfPassengers;
            System.out.println(numberOfPassengers + " passengers entered. Total: " + currentPassengers);
            return true;
        } else {
            int availableSpace = maxCapacity - currentPassengers;
            System.out.println("Cannot accommodate " + numberOfPassengers + " passengers. Only " +
                    availableSpace + " spaces available.");
            return false;
        }
    }

    // Passengers to exit the bus
    public boolean passengerExit(int numberOfPassengers) {
        if (numberOfPassengers <= 0) {
            return false;
        }

        if (numberOfPassengers <= currentPassengers) {
            currentPassengers -= numberOfPassengers;
            System.out.println(numberOfPassengers + " passengers exited. Remaining: " + currentPassengers);
            return true;
        } else {
            System.out.println("Cannot remove " + numberOfPassengers + " passengers. Only " +
                    currentPassengers + " passengers on board.");
            return false;
        }
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getAvailableSpace() {
        return maxCapacity - currentPassengers;
    }

    // Check if bus is full
    public boolean isFull() {
        return currentPassengers >= maxCapacity;
    }

    // Check if bus is empty
    public boolean isEmpty() {
        return currentPassengers == 0;
    }

}
