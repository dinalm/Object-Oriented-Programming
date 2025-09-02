package Task_02;

interface Vehicle {
    void start();
    void stop();
    String getInfo();
}

abstract class AbstractVehicle implements Vehicle {
    protected String type, fuel, color;
    protected boolean isRunning = false;

    AbstractVehicle(String type, String fuel, String color) {
        this.type = type;
        this.fuel = fuel;
        this.color = color;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start() {
        System.out.println(type + " is starting...");
        isRunning = true;
    }

    public void stop() {
        System.out.println(type + " is stopping...");
        isRunning = false;
    }

    abstract String getSpecificInfo();

    public String getInfo() {
        return type + " Information:\n" + getSpecificInfo();
    }
}

class Car extends AbstractVehicle {
    Car() {
        super("Car", "Petrol", "Red");
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nFuel: " + fuel + "\nColor: " + color;
    }
}

class Motorcycle extends AbstractVehicle {
    Motorcycle() {
        super("Motorcycle", "Gasoline", "Black");
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nFuel: " + fuel + "\nColor: " + color;
    }
}

class Bus extends AbstractVehicle {
    String capacity = "40 passengers";

    Bus() {
        super("Bus", "Diesel", "Blue");
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nFuel: " + fuel + "\nCapacity: " + capacity;
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle bus = new Bus();

        car.start();
        car.stop();
        System.out.println(car.getInfo() + "\n");

        motorcycle.start();
        motorcycle.stop();
        System.out.println(motorcycle.getInfo() + "\n");

        bus.start();
        bus.stop();
        System.out.println(bus.getInfo() + "\n");

        // Check running status
        System.out.println("Car running: " + ((AbstractVehicle) car).isRunning());
        System.out.println("Bus running: " + ((AbstractVehicle) bus).isRunning());
        System.out.println("Motorcycle running: " + ((AbstractVehicle) motorcycle).isRunning());
    }
}