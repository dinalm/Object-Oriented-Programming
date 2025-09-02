package Task_03;

public class EventSystemTest {

    public static void main(String[] args) {

        // a) Generate a list of events
        EventList eventList = new EventList();

        // Create events with different times and enum event types
        eventList.addEvent(new Event(15.5, EventType.EXIT));
        eventList.addEvent(new Event(3.2, EventType.ARRIVAL));
        eventList.addEvent(new Event(8.7, EventType.ARRIVAL));
        eventList.addEvent(new Event(12.1, EventType.ARRIVAL));
        eventList.addEvent(new Event(7.3, EventType.EXIT));
        eventList.addEvent(new Event(20.0, EventType.EXIT));
        eventList.addEvent(new Event(1.0, EventType.ARRIVAL));

        System.out.println("Generated " + eventList.size() + " events\n");

        // b) Remove the first (next to be processed) event from the list
        Event firstEvent = eventList.removeNextEvent();
        System.out.println("Removed first event: " + firstEvent);
        System.out.println("Remaining events: " + eventList.size() + "\n");

        // c) Print the contents of the EventList, ordered by event time
        eventList.printEventsInOrder();
    }
}
