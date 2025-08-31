package Task_03;

public class Shape {
    public double calculateArea() {
        return 0;
    }
}

// Circle
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public String toString() {
        return "Circle with radius " + radius;
    }
}

// Rectangle
class Rectangle extends Shape {
    private double width;
    private double height;

    // Constructor
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double calculateArea() {
        return width * height;
    }

    public String toString() {
        return "Rectangle with width " + width + " and height " + height;
    }

}

// Triangle
class Triangle extends Shape {
    private double base;
    private double height;

    // Constructor
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double calculateArea() {
        return 0.5 * base * height;
    }

    public String toString() {
        return "Triangle with base " + base + " and height " + height;
    }
}
