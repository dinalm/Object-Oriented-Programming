package Task_01;

public class SportsCar extends Car {

    public SportsCar(String typeName) {
        super(typeName);
    }

    public SportsCar(String typeName, double gasolineLevel, double speed) {
        super(typeName, gasolineLevel, speed);
    }

    public void accelerate() {
        if (gasolineLevel > 0) {
            speed += 20;
            gasolineLevel = Math.max(0, gasolineLevel - 3.0);
        } else {
            speed = 0;
        }
    }

    void decelerate(int amount) {
        if (gasolineLevel > 0) {
            if (amount > 0) {
                double enhancedAmount = amount * 1.5;
                speed = Math.max(0, speed - enhancedAmount);
            }
        } else {
            speed = 0;
        }
    }

    public void turboBoost() {
        if (gasolineLevel >= 10) {
            speed += 30;
            gasolineLevel = Math.max(0, gasolineLevel - 10.0);
        }
    }

}
