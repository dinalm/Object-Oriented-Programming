public class Car2 {
    private double speed;
    private double gasolineLevel;
    private String typeName;

    // Cruise control variables
    private boolean cruiseOn;
    private double targetSpeed;
    private final double MIN_CRUISE_SPEED = 30;
    private final double MAX_CRUISE_SPEED = 120;

    public Car2(String typeName) {
        this.typeName = typeName;
        this.speed = 0;
        this.gasolineLevel = 0;
        this.cruiseOn = false;
        this.targetSpeed = 0;
    }

    // Cruise Control Methods
    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public double getTargetSpeed() {
        return targetSpeed;
    }

    public boolean turnCruiseOn() {
        if (targetSpeed < MIN_CRUISE_SPEED || targetSpeed > MAX_CRUISE_SPEED || gasolineLevel <= 0) {
            cruiseOn = false;
            return false;
        }
        cruiseOn = true;
        return true;
    }

    public void turnCruiseOff() {
        cruiseOn = false;
    }

    public void adjustToCruiseSpeed() {
        if (cruiseOn) {
            if (speed < targetSpeed) {
                while (speed < targetSpeed && gasolineLevel > 0) {
                    accelerate();
                }
            } else if (speed > targetSpeed) {
                while (speed > targetSpeed && gasolineLevel > 0) {
                    decelerate(5);
                }
            }
        }
    }

    // Old methods
    public void accelerate() {
        if (gasolineLevel > 0)
            speed += 10;
        else
            speed = 0;
    }

    public void decelerate(int amount) {
        if (gasolineLevel > 0) {
            if (amount > 0)
                speed = Math.max(0, speed - amount);
        } else
            speed = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public String getTypeName() {
        return typeName;
    }

    public void fillTank() {
        gasolineLevel = 100;
    }

    public double getGasolineLevel() {
        return gasolineLevel;
    }
}
