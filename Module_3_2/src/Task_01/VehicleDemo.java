package Task_01;

public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration");
        System.out.println();

        // Create instances of different vehicles
        Vehicle car = new Car("Petrol", "Red");
        Vehicle motorcycle = new Motorcycle("Gasoline", "Black");
        Vehicle bus = new Bus("Diesel", 40);

        // Demo Car
        car.start();
        car.stop();
        System.out.println(car.getInfo());
        System.out.println();

        // Demo Motorcycle
        motorcycle.start();
        motorcycle.stop();
        System.out.println(motorcycle.getInfo());
        System.out.println();

        // Demo Bus
        bus.start();
        bus.stop();
        System.out.println(bus.getInfo());
        System.out.println();

    }
}
