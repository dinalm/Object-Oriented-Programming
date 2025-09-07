package Task_05;

class Clock {
    // Single instance of Clock
    private static Clock instance = null;

    // Current time of the clock
    private double currentTime;

    // Private constructor
    private Clock() {
        this.currentTime = 0.0;
    }

    // Get the single instance
    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    // Get current time
    public double getTime() {
        return currentTime;
    }

    // Set the time
    public void setTime(double newTime) {
        this.currentTime = newTime;
    }

    // Advance time
    public void advanceTime(double timeIncrement) {
        this.currentTime += timeIncrement;
    }

    // Reset time to zero
    public void resetTime() {
        this.currentTime = 0.0;
    }

    @Override
    public String toString() {
        return "Clock time: " + currentTime;
    }
}

public class ClockTest {
    public static void main(String[] args) {
        System.out.println("Singleton Clock Test Program\n");

        // Get instance and verify singleton behavior
        System.out.println("Singleton Behavior");
        Clock clock1 = Clock.getInstance();
        Clock clock2 = Clock.getInstance();

        System.out.println("clock1 == clock2: " + (clock1 == clock2));
        System.out.println("Both references point to same object: " +
                (clock1.hashCode() == clock2.hashCode()));
        System.out.println("Initial time: " + clock1.getTime());
        System.out.println();

        // Setting and getting time
        System.out.println("Setting and Getting Time");
        clock1.setTime(10.5);
        System.out.println("After setting time to 10.5:");
        System.out.println("clock1 time: " + clock1.getTime());
        System.out.println("clock2 time: " + clock2.getTime());
        System.out.println();

        // Advancing time
        System.out.println("Advancing Time");
        clock1.advanceTime(5.25);
        System.out.println("After advancing by 5.25 units:");
        System.out.println("Current time: " + clock2.getTime());

        clock1.advanceTime(2.75);
        System.out.println("After advancing by another 2.75 units:");
        System.out.println("Current time: " + clock1.getTime());
        System.out.println();

        // Multiple operations demonstrating shared state
        System.out.println("Shared State Demonstration");
        Clock clock3 = Clock.getInstance();

        System.out.println("clock3 initial time: " + clock3.getTime());
        clock3.setTime(100.0);
        System.out.println("After clock3.setTime(100.0):");
        System.out.println("clock1 time: " + clock1.getTime());
        System.out.println("clock2 time: " + clock2.getTime());
        System.out.println("clock3 time: " + clock3.getTime());
        System.out.println();

        // Reset functionality
        System.out.println("Reset Time");
        clock1.resetTime();
        System.out.println("After reset:");
        System.out.println("All clocks show time: " + clock2.getTime());
        System.out.println();

        // Simulation scenario
        System.out.println("Simulation Scenario");
        System.out.println("Simulating events occurring at different times:");

        clock1.setTime(0.0);
        System.out.println("Simulation start: " + clock1);

        clock1.advanceTime(2.5);
        System.out.println("Event 1 occurs: " + clock1);

        clock1.advanceTime(3.7);
        System.out.println("Event 2 occurs: " + clock1);

        clock1.advanceTime(1.8);
        System.out.println("Event 3 occurs: " + clock1);

        clock1.setTime(25.0);
        System.out.println("Jump to specific time: " + clock1);
        System.out.println();

        // Verify constructor is private
        System.out.println("Constructor Access");
        System.out.println("Cannot create Clock with 'new Clock()' - constructor is private");

    }
}
