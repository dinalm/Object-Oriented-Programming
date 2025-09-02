package Task_01;

public interface Vehicle {
    void start();
    void stop();
    String getInfo();
}

// Car
class Car implements Vehicle {
    private String fuel;
    private String color;

    // Constructor
    public Car(String fuel, String color) {
        this.fuel = fuel;
        this.color = color;
    }

    public void start() {
        System.out.println("Car is starting...");
    }

    public void stop() {
        System.out.println("Car is stopping...");
    }

    public String getInfo() {
        return "Car Information:\n" +
                "Type: Car\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color;
    }
}

// Motorcycle
class Motorcycle implements Vehicle {
    private String fuel;
    private String color;

    // Constructor
    public Motorcycle(String fuel, String color) {
        this.fuel = fuel;
        this.color = color;
    }

    public void start() {
        System.out.println("Motorcycle is starting...");
    }

    public void stop() {
        System.out.println("Motorcycle is stopping...");
    }

    public String getInfo() {
        return "Motorcycle Information:\n" +
                "Type: Motorcycle\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color;
    }
}

// Bus
class Bus implements Vehicle {
    private String fuel;
    private int capacity;

    // Constructor
    public Bus(String fuel, int capacity) {
        this.fuel = fuel;
        this.capacity = capacity;
    }

    public void start() {
        System.out.println("Bus is starting...");
    }

    public void stop() {
        System.out.println("Bus is stopping...");
    }

    public String getInfo() {
        return "Bus Information:\n" +
                "Type: Bus\n" +
                "Fuel: " + fuel + "\n" +
                "Capacity: " + capacity + " passengers";
    }
}
