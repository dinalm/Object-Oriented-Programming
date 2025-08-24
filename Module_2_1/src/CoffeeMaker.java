public class CoffeeMaker {
    private boolean on;
    private String coffeeType;
    private int coffeeAmount;

    //Constructor
    public CoffeeMaker() {
        this.on = false;
        this.coffeeType = "normal";
        this.coffeeAmount = 10;
    }

    //Turn on/off
    public void pressOnOff() {
        on = !on;
    }

    public boolean isOn() {
        return on;
    }

    //Set coffee type
    public void setCoffeeType(String type) {
        if (on) {
            if (type.equals("normal") || type.equals("espresso")) {
                this.coffeeType = type;
            }
        }
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    // Set coffee amount
    public void setCoffeeAmount(int amount) {
        if (on) {
            if (amount >= 10 && amount <= 80) {
                this.coffeeAmount = amount;
            }
        }
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }
}
