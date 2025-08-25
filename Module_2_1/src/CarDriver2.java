public class CarDriver2 {
    public static void main(String[] args) {
        Car2 myCar = new Car2("Toyota Corolla");
        myCar.fillTank();

        // Accelerate
        for (int i = 0; i < 3; i++) {
            myCar.accelerate();
        }
        System.out.println("Manual driving: speed is " + myCar.getSpeed() + " km/h");

        // Trying cruise control
        myCar.setTargetSpeed(50);
        boolean cruiseOn = myCar.turnCruiseOn();

        if (cruiseOn) {
            System.out.println("Cruise control is on, target speed = " + myCar.getTargetSpeed() + " km/h");
            myCar.adjustToCruiseSpeed();
            System.out.println("Car adjusted to " + myCar.getSpeed() + " km/h");
        } else {
            System.out.println("Could not turn on cruise control!");
        }

        // Turn off cruise
        myCar.turnCruiseOff();
        System.out.println("Cruise control is off");
    }
}
