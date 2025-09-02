package Task_03;

interface Vehicle {
    void start();
    void stop();
    String getInfo();
}

interface ElectricVehicle {
    void charge();
    int getBatteryLevel();
    void setBatteryLevel(int level);
}

abstract class AbstractVehicle implements Vehicle, ElectricVehicle {
    protected String type, fuel, color;
    protected boolean isRunning = false;
    protected int batteryLevel = 0;

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

    public void charge() {
        System.out.println("Cannot charge " + type + " - not an electric vehicle.");
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int level) {
        this.batteryLevel = level;
    }

    abstract String getSpecificInfo();

    public String getInfo() {
        return type + " Information:\n" + getSpecificInfo();
    }
}

class Car extends AbstractVehicle {
    Car() {
        super("Car", "Gasoline", "Blue");
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nFuel: " + fuel + "\nColor: " + color;
    }
}

class Motorcycle extends AbstractVehicle {
    Motorcycle() {
        super("Motorcycle", "Petrol", "Red");
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nFuel: " + fuel + "\nColor: " + color;
    }
}

class Bus extends AbstractVehicle {
    String capacity = "50 passengers";

    Bus() {
        super("Bus", "Diesel", "Yellow");
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nFuel: " + fuel + "\nCapacity: " + capacity;
    }
}

class ElectricCar extends AbstractVehicle {
    ElectricCar() {
        super("Electric Car", "Battery", "Green");
        this.batteryLevel = 60;
    }

    public void charge() {
        if (batteryLevel < 100) {
            batteryLevel = Math.min(100, batteryLevel + 20);
            System.out.println(type + " charging complete. Battery: " + batteryLevel + "%");
        } else {
            System.out.println(type + " battery is already full!");
        }
    }

    public void start() {
        if (batteryLevel > 10) {
            System.out.println(type + " starting quietly...");
            isRunning = true;
        } else {
            System.out.println(type + " battery too low to start. Please charge.");
        }
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nPower Source: " + fuel + "\nColor: " + color +
                "\nBattery: " + batteryLevel + "%";
    }
}

class ElectricMotorcycle extends AbstractVehicle {
    ElectricMotorcycle() {
        super("Electric Motorcycle", "Battery", "Black");
        this.batteryLevel = 80;
    }

    public void charge() {
        if (batteryLevel < 100) {
            batteryLevel = Math.min(100, batteryLevel + 15);
            System.out.println(type + " charged. Current battery: " + batteryLevel + "%");
        } else {
            System.out.println(type + " is fully charged.");
        }
    }

    public void start() {
        if (batteryLevel > 5) {
            System.out.println(type + " starting with electric motor...");
            isRunning = true;
        } else {
            System.out.println(type + " needs charging before starting.");
        }
    }

    String getSpecificInfo() {
        return "Type: " + type + "\nPower Source: " + fuel + "\nColor: " + color +
                "\nBattery: " + batteryLevel + "%";
    }
}

public class VehicleTestDemo {
    public static void main(String[] args) {

        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle bus = new Bus();

        Vehicle electricCar = new ElectricCar();
        Vehicle electricMotorcycle = new ElectricMotorcycle();

        car.start();
        car.stop();
        System.out.println(car.getInfo());
        System.out.println();

        motorcycle.start();
        motorcycle.stop();
        System.out.println(motorcycle.getInfo());
        System.out.println();

        bus.start();
        bus.stop();
        System.out.println(bus.getInfo());
        System.out.println();

        electricCar.start();
        electricCar.stop();
        System.out.println(electricCar.getInfo());
        System.out.println();

        electricMotorcycle.start();
        electricMotorcycle.stop();
        System.out.println(electricMotorcycle.getInfo());
        System.out.println();


        System.out.println("Testing Charge Functionality:");
        ((AbstractVehicle) car).charge();
        ((AbstractVehicle) motorcycle).charge();
        ((AbstractVehicle) bus).charge();
        ((AbstractVehicle) electricCar).charge();
        ((AbstractVehicle) electricMotorcycle).charge();

        System.out.println("Electric Car battery: " + ((AbstractVehicle) electricCar).getBatteryLevel() + "%");
        System.out.println("Electric Motorcycle battery: " + ((AbstractVehicle) electricMotorcycle).getBatteryLevel() + "%");
    }
}
