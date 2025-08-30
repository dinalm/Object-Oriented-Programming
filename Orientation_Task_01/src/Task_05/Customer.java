package Task_05;

public class Customer {
    private static int nextId = 1;
    private int id;
    private long startTime;
    private long endTime;
    private long serviceStartTime;
    private long serviceEndTime;

    public Customer() {
        this.id = nextId++;
        this.startTime = 0;
        this.endTime = 0;
        this.serviceStartTime = 0;
        this.serviceEndTime = 0;
    }

    public Customer(long startTime) {
        this.id = nextId++;
        this.startTime = startTime;
        this.endTime = 0;
        this.serviceStartTime = 0;
        this.serviceEndTime = 0;
    }

    // Getters
    public int getId() { return id; }
    public long getStartTime() { return startTime; }
    public long getEndTime() { return endTime; }
    public long getServiceStartTime() { return serviceStartTime; }
    public long getServiceEndTime() { return serviceEndTime; }

    // Setters
    public void setStartTime(long startTime) { this.startTime = startTime; }
    public void setEndTime(long endTime) { this.endTime = endTime; }
    public void setServiceStartTime(long serviceStartTime) { this.serviceStartTime = serviceStartTime; }
    public void setServiceEndTime(long serviceEndTime) { this.serviceEndTime = serviceEndTime; }

    // Calculate waiting time in queue
    public long getWaitingTime() {
        return serviceStartTime - startTime;
    }

    // Calculate service time
    public long getServiceTime() {
        return serviceEndTime - serviceStartTime;
    }

    // Calculate total response time
    public long getResponseTime() {
        return serviceEndTime - startTime;
    }

    public String toString() {
        return "Customer ID: " + id;
    }
}
