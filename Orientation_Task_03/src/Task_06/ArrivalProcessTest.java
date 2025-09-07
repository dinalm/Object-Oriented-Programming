package Task_06;

import java.util.*;

// EventType
enum EventType {
    ARRIVAL,
    DEPARTURE,
    SERVICE_START,
    SERVICE_END
}

// Event class
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

// Manage simulation events
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

    // Generate random number using inverse transform method
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

    // Constructor
    public ArrivalProcess(EventType eventType, ExponentialGenerator generator) {
        this.eventType = eventType;
        this.generator = generator;
    }

    // Generate next arrival event and add to event list
    public void generateArrival(EventList eventList) {
        Clock clock = Clock.getInstance();

        // Generate time interval until next arrival
        double interval = generator.sample();

        // Calculate next event time
        double nextEventTime = clock.getTime() + interval;

        // Move clock to the new event time
        clock.setTime(nextEventTime);

        // Create new event and add to event list
        Event newEvent = new Event(nextEventTime, eventType);
        eventList.addEvent(newEvent);
    }

    // Generate multiple arrivals
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

public class ArrivalProcessTest {
    public static void main(String[] args) {

        // Initialize clock
        Clock clock = Clock.getInstance();
        clock.setTime(0.0);
        System.out.println("Initial clock time: " + clock.getTime());

        // Create event list
        EventList eventList = new EventList();

        // Create ArrivalProcess with exponential distribution
        System.out.println("\nCreating ArrivalProcess");
        ExponentialGenerator expGenerator = new ExponentialGenerator(5.0);
        ArrivalProcess arrivalProcess = new ArrivalProcess(EventType.ARRIVAL, expGenerator);

        System.out.println("ArrivalProcess created:");
        System.out.println("Event Type: " + arrivalProcess.getEventType());
        System.out.println("Generator Mean: " + arrivalProcess.getGeneratorMean());

        // Generate single arrival
        System.out.println("\nGenerating single arrival");
        arrivalProcess.generateArrival(eventList);
        System.out.println("Clock time after first event: " + clock.getTime());
        eventList.printEventList();

        // Generate multiple arrivals
        System.out.println("Generating 9 more arrivals (10 total)");
        arrivalProcess.generateMultipleArrivals(eventList, 9);
        System.out.println("Final clock time: " + clock.getTime());
        eventList.printEventList();

        // Statistics about generated events
        System.out.println("Event Statistics");
        List<Event> events = eventList.getEvents();
        System.out.println("Total events generated: " + events.size());

        if (events.size() > 1) {
            double totalIntervals = 0;
            for (int i = 1; i < events.size(); i++) {
                double interval = events.get(i).getEventTime() - events.get(i-1).getEventTime();
                totalIntervals += interval;
            }
            double avgInterval = totalIntervals / (events.size() - 1);
            System.out.printf("Average interval between events: %.2f (Expected: %.2f)\n",
                    avgInterval, expGenerator.getMean());
        }

        // Multiple ArrivalProcess instances
        System.out.println("\nMultiple ArrivalProcess with different parameters");

        // Reset for clean test
        eventList.clear();
        clock.setTime(0.0);

        // Create different arrival processes
        ExponentialGenerator fastGenerator = new ExponentialGenerator(2.0, 123);
        ArrivalProcess fastArrivals = new ArrivalProcess(EventType.SERVICE_START, fastGenerator);

        ExponentialGenerator slowGenerator = new ExponentialGenerator(8.0, 456);
        ArrivalProcess slowArrivals = new ArrivalProcess(EventType.DEPARTURE, slowGenerator);

        System.out.println("Fast arrivals (mean=2.0): SERVICE_START events");
        fastArrivals.generateMultipleArrivals(eventList, 3);

        System.out.println("Slow arrivals (mean=8.0): DEPARTURE events");
        slowArrivals.generateMultipleArrivals(eventList, 3);

        eventList.printEventList();

    }
}
