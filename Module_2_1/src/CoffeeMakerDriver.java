public class CoffeeMakerDriver {
    public static void main(String[] args) {
        CoffeeMaker cfMaker = new CoffeeMaker();

        // Switch on
        cfMaker.pressOnOff();
        System.out.println("Coffee maker is on");

        // Set coffee type and amount
        cfMaker.setCoffeeType("espresso");
        System.out.println("Coffee type is " + cfMaker.getCoffeeType());

        cfMaker.setCoffeeAmount(50);
        System.out.println("Coffee amount is " + cfMaker.getCoffeeAmount() + " ml");

        // Switch off
        cfMaker.pressOnOff();
        System.out.println("Coffee maker is off");
    }
}

