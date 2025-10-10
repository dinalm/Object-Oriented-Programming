package Task_01;

public class Pen {
    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;
        Color(String color) { this.color = color; };
        @Override public String toString() { return color; }
    }

    private Color currentColor;
    private Color pendingColor;
    private boolean capIsOn;

    // Default constructor - Red color with cap on
    public Pen() {
        this.currentColor = Color.RED;
        this.pendingColor = null;
        this.capIsOn = true;
    }

    // Constructor with specified color - cap on by default
    public Pen(Color color) {
        this.currentColor = color;
        this.pendingColor = null;
        this.capIsOn = true;
    }

    // Remove the cap
    public void capOff() {
        // Apply any pending color change when cap comes off
        if (pendingColor != null) {
            currentColor = pendingColor;
            pendingColor = null;
        }
        this.capIsOn = false;
    }

    // Put the cap on
    public void capOn() {
        this.capIsOn = true;
    }

    // Draw - returns drawing message if cap is off, empty string if cap is on
    public String draw() {
        if (capIsOn) {
            return "";
        }
        return "Drawing " + currentColor.toString();
    }

    // Change the color of the pen
    public void changeColor(Color newColor) {
        this.pendingColor = newColor;
    }
}