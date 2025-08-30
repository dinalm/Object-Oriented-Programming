package Task_04;

public class Customer {
    private static int nextId = 1;
    private int id;
    private long startTime;
    private long endTime;

    // Constructor
    public Customer() {
        this.id = nextId++;
        this.startTime = 0;
        this.endTime = 0;
    }

    // Constructor with start time
    public Customer(long startTime) {
        this.id = nextId++;
        this.startTime = startTime;
        this.endTime = 0;
    }

    // Constructor with start and end time
    public Customer(long startTime, long endTime) {
        this.id = nextId++;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public int getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    // Setters
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    // Calculate time spent
    public long getTimeSpent() {
        return endTime - startTime;
    }

    public String toString() {
        return String.format("Customer ID: %d", id);
    }
}
