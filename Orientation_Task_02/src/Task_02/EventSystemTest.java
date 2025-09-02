package Task_02;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

// Event class that implements Comparable
class Event implements Comparable<Event> {
    private double eventTime;
    private String eventType;

    // Constructor
    public Event(double eventTime, String eventType) {
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    // Getters
    public double getEventTime() {
        return eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    // Comparable implementation
    public int compareTo(Event other) {
        return Double.compare(this.eventTime, other.eventTime);
    }

    // toString method for display
    public String toString() {
        return String.format("Event[Time=%.2f, Type=%s]", eventTime, eventType);
    }
}

// EventList class that manages events
class EventList {
    private PriorityQueue<Event> eventQueue;

    // Constructor
    public EventList() {
        this.eventQueue = new PriorityQueue<>();
    }

    // Add an event to the list
    public void addEvent(Event event) {
        eventQueue.offer(event);
    }

    // Remove and return the next event (earliest time)
    public Event removeNextEvent() {
        return eventQueue.poll();
    }

    // Check if the event list is empty
    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    // Get the number of events in the list
    public int size() {
        return eventQueue.size();
    }

    // Print all events in chronological order
    public void printEventsInOrder() {
        if (eventQueue.isEmpty()) {
            System.out.println("Event list is empty.");
            return;
        }

        // Create a sorted copy to display in order
        ArrayList<Event> sortedEvents = new ArrayList<>(eventQueue);
        Collections.sort(sortedEvents);

        System.out.println("Events in chronological order:");
        for (Event event : sortedEvents) {
            System.out.println(event);
        }
    }
}

public class EventSystemTest {

    public static void main(String[] args) {
        // a) Generate a list of events
        EventList eventList = new EventList();

        // Create events with different times (not in chronological order)
        eventList.addEvent(new Event(15.5, "Customer Departure"));
        eventList.addEvent(new Event(3.2, "Customer Arrival"));
        eventList.addEvent(new Event(8.7, "Service Start"));
        eventList.addEvent(new Event(12.1, "Customer Arrival"));
        eventList.addEvent(new Event(7.3, "Customer Departure"));
        eventList.addEvent(new Event(20.0, "System Shutdown"));
        eventList.addEvent(new Event(1.0, "System Startup"));

        System.out.println("Generated " + eventList.size() + " events\n");

        // b) Remove the first event from the list
        Event firstEvent = eventList.removeNextEvent();
        System.out.println("Removed first event: " + firstEvent);
        System.out.println("Remaining events: " + eventList.size() + "\n");

        // c) Print the contents of the EventList
        eventList.printEventsInOrder();
    }
}
