package Task_07;

import java.util.*;

// EventType
enum EventType {
    ARRIVAL,
    DEPARTURE,
    SERVICE_START,
    SERVICE_END
}

// Event class to represent simulation events
class Event {
    private double eventTime;
    private EventType eventType;

    public Event(double eventTime, EventType eventType) {
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    public double getEventTime() {
        return eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return String.format("Event: %s at time %.2f", eventType, eventTime);
    }
}

// EventList class to manage simulation events
class EventList {
    private List<Event> events;

    public EventList() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public Event removeEvent() {
        if (!events.isEmpty()) {
            return events.remove(0);
        }
        return null;
    }

    public int size() {
        return events.size();
    }

    public void printEventList() {
        System.out.println("Event List Contents:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i));
        }
        System.out.println();
    }

    public void clear() {
        events.clear();
    }
}

// Customer class to represent customers in the system
class Customer {
    static int customerIdCounter = 1;
    private int customerId;
    private double arrivalTime;
    private double departureTime;

    public Customer(double arrivalTime) {
        this.customerId = customerIdCounter++;
        this.arrivalTime = arrivalTime;
        this.departureTime = -1; // Not set yet
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public double getTimeInSystem() {
        if (departureTime > 0) {
            return departureTime - arrivalTime;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (departureTime > 0) {
            return String.format("Customer %d: Arrival=%.2f, Departure=%.2f, Time in system=%.2f",
                    customerId, arrivalTime, departureTime, getTimeInSystem());
        } else {
            return String.format("Customer %d: Arrival=%.2f (still in system)", customerId, arrivalTime);
        }
    }
}

// ServicePoint class to handle customer queuing and service
class ServicePoint {
    private Queue<Customer> customerQueue;
    private String servicePointName;

    public ServicePoint(String servicePointName) {
        this.customerQueue = new LinkedList<>();
        this.servicePointName = servicePointName;
    }

    public void addCustomer(Customer customer) {
        customerQueue.offer(customer);
    }

    public Customer removeCustomer() {
        return customerQueue.poll();
    }

    public int getQueueSize() {
        return customerQueue.size();
    }

    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    public void printQueue() {
        System.out.println(servicePointName + " Queue Status:");
        System.out.println("Queue size: " + getQueueSize());
        if (!customerQueue.isEmpty()) {
            System.out.println("Customers in queue:");
            for (Customer customer : customerQueue) {
                System.out.println("  " + customer);
            }
        }
        System.out.println();
    }

    public String getServicePointName() {
        return servicePointName;
    }
}

// Random number generator
class ExponentialGenerator {
    private Random random;
    private double mean;

    public ExponentialGenerator(double mean) {
        this.random = new Random();
        this.mean = mean;
    }

    public ExponentialGenerator(double mean, long seed) {
        this.random = new Random(seed);
        this.mean = mean;
    }

    public double sample() {
        double u = random.nextDouble();
        return -mean * Math.log(1 - u);
    }

    public double getMean() {
        return mean;
    }
}

// Clock class
class Clock {
    private static Clock instance = null;
    private double currentTime;

    private Clock() {
        this.currentTime = 0.0;
    }

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public double getTime() {
        return currentTime;
    }

    public void setTime(double newTime) {
        this.currentTime = newTime;
    }

    public void advanceTime(double timeIncrement) {
        this.currentTime += timeIncrement;
    }
}

// ArrivalProcess class
class ArrivalProcess {
    private EventType eventType;
    private ExponentialGenerator generator;

    public ArrivalProcess(EventType eventType, ExponentialGenerator generator) {
        this.eventType = eventType;
        this.generator = generator;
    }

    public void generateArrival(EventList eventList) {
        Clock clock = Clock.getInstance();
        double interval = generator.sample();
        double nextEventTime = clock.getTime() + interval;
        clock.setTime(nextEventTime);
        Event newEvent = new Event(nextEventTime, eventType);
        eventList.addEvent(newEvent);
    }

    public void generateMultipleArrivals(EventList eventList, int numberOfEvents) {
        for (int i = 0; i < numberOfEvents; i++) {
            generateArrival(eventList);
        }
    }

    public EventType getEventType() {
        return eventType;
    }

    public double getGeneratorMean() {
        return generator.getMean();
    }
}

public class CompleteTest {
    public static void main(String[] args) {

        // Initialize components
        Clock clock = Clock.getInstance();
        clock.setTime(0.0);
        EventList eventList = new EventList();
        ServicePoint servicePoint = new ServicePoint("Main Service Point");

        Customer.customerIdCounter = 1;

        // Generate 10 arrival events
        System.out.println("Generating 10 arrival events");

        ExponentialGenerator arrivalGenerator = new ExponentialGenerator(3.5, 12345);
        ArrivalProcess arrivalProcess = new ArrivalProcess(EventType.ARRIVAL, arrivalGenerator);

        arrivalProcess.generateMultipleArrivals(eventList, 10);

        System.out.println("Final clock time after generating events: " + clock.getTime());
        eventList.printEventList();

        // Process all events sequentially
        System.out.println("Processing events sequentially");

        System.out.println("Clock time before processing: " + clock.getTime());

        List<Customer> allCustomers = new ArrayList<>();

        while (eventList.size() > 0) {
            Event currentEvent = eventList.removeEvent();

            // Create customer with arrival time
            Customer customer = new Customer(currentEvent.getEventTime());
            allCustomers.add(customer);

            // Add customer to service point queue
            servicePoint.addCustomer(customer);

            System.out.println("Processed: " + currentEvent + " -> Created " + customer);
        }

        System.out.println("\nClock time after processing: " + clock.getTime());
        servicePoint.printQueue();

        // Move clock forward by 5 time units
        System.out.println("Moving clock forward by 5 time units");

        System.out.println("Clock time before advance: " + clock.getTime());
        clock.advanceTime(5.0);
        System.out.println("Clock time after advance: " + clock.getTime());
        System.out.println();

        // Clear service point and calculate time in system
        System.out.println("Clearing service point and calculating times");

        List<Customer> servedCustomers = new ArrayList<>();
        int customerCount = 1;

        while (!servicePoint.isEmpty()) {
            Customer customer = servicePoint.removeCustomer();
            customer.setDepartureTime(clock.getTime()); // All depart at current time
            servedCustomers.add(customer);

            System.out.println("Served customer " + customerCount + ": " + customer);
            customerCount++;
        }

        System.out.println();
        servicePoint.printQueue();

        // Summary statistics
        System.out.println("Summary Statistics");

        double totalTimeInSystem = 0;
        double minTime = Double.MAX_VALUE;
        double maxTime = Double.MIN_VALUE;

        for (Customer customer : servedCustomers) {
            double timeInSystem = customer.getTimeInSystem();
            totalTimeInSystem += timeInSystem;

            if (timeInSystem < minTime) minTime = timeInSystem;
            if (timeInSystem > maxTime) maxTime = timeInSystem;
        }

        double averageTimeInSystem = totalTimeInSystem / servedCustomers.size();

        System.out.println("Total customers served: " + servedCustomers.size());
        System.out.printf("Average time in system: %.2f time units\n", averageTimeInSystem);
        System.out.printf("Minimum time in system: %.2f time units\n", minTime);
        System.out.printf("Maximum time in system: %.2f time units\n", maxTime);
        System.out.printf("Total simulation time: %.2f time units\n", clock.getTime());

    }
}
