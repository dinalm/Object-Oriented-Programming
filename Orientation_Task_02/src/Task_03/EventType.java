package Task_03;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

// Event type enumeration
public enum EventType {
    ARRIVAL,
    EXIT
}

// Event class that implements Comparable
class Event implements Comparable<Event> {
    private double eventTime;
    private EventType eventType;

    // Constructor
    public Event(double eventTime, EventType eventType) {
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    // Getters
    public double getEventTime() {
        return eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    // Comparable implementation -
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

    // Remove and return the next event
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

    // Print all events
    public void printEventsInOrder() {
        if (eventQueue.isEmpty()) {
            System.out.println("Event list is empty.");
            return;
        }

        // Create a sorted copy to display
        ArrayList<Event> sortedEvents = new ArrayList<>(eventQueue);
        Collections.sort(sortedEvents);

        System.out.println("Events in chronological order:");
        for (Event event : sortedEvents) {
            System.out.println(event);
        }
    }
}
