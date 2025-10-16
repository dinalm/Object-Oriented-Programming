package Task_01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonSortingFiltering {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, "New York"));
        people.add(new Person("Bob", 25, "Los Angeles"));
        people.add(new Person("Charlie", 35, "New York"));
        people.add(new Person("Diana", 28, "Chicago"));
        people.add(new Person("Eve", 22, "New York"));
        people.add(new Person("Frank", 40, "Los Angeles"));
        people.add(new Person("Grace", 27, "New York"));
        people.add(new Person("Henry", 33, "Chicago"));

        System.out.println("Original List:");
        printList(people);

        // Sort by age in ascending order using lambda expression
        people.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        // Alternative syntax: people.sort(Comparator.comparingInt(Person::getAge));

        System.out.println("\nSorted by Age (Ascending):");
        printList(people);

        // Filter to keep only people from New York using removeIf()
        String targetCity = "New York";
        people.removeIf(person -> !person.getCity().equals(targetCity));

        System.out.println("\nFiltered (Only " + targetCity + "):");
        printList(people);

        System.out.println("\n--- Additional Examples ---");

        // Example 1: Sort by name
        List<Person> people2 = createPeopleList();
        people2.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println("\nSorted by Name:");
        printList(people2);

        // Example 2: Sort by age descending
        List<Person> people3 = createPeopleList();
        people3.sort((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()));
        System.out.println("\nSorted by Age (Descending):");
        printList(people3);

        // Example 3: Sort by city, then by age
        List<Person> people4 = createPeopleList();
        Comparator<Person> cityThenAge = (p1, p2) -> {
            int cityComparison = p1.getCity().compareTo(p2.getCity());
            if (cityComparison != 0) {
                return cityComparison;
            }
            return Integer.compare(p1.getAge(), p2.getAge());
        };
        people4.sort(cityThenAge);
        System.out.println("\nSorted by City, then Age:");
        printList(people4);

        // Example 4: Filter people older than 30
        List<Person> people5 = createPeopleList();
        people5.removeIf(person -> person.getAge() <= 30);
        System.out.println("\nFiltered (Age > 30):");
        printList(people5);
    }

    // Helper method to create a fresh list of people
    private static List<Person> createPeopleList() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, "New York"));
        people.add(new Person("Bob", 25, "Los Angeles"));
        people.add(new Person("Charlie", 35, "New York"));
        people.add(new Person("Diana", 28, "Chicago"));
        people.add(new Person("Eve", 22, "New York"));
        people.add(new Person("Frank", 40, "Los Angeles"));
        people.add(new Person("Grace", 27, "New York"));
        people.add(new Person("Henry", 33, "Chicago"));
        return people;
    }

    // Helper method to print the list
    private static void printList(List<Person> people) {
        for (Person person : people) {
            System.out.println("  " + person);
        }
    }
}