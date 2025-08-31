package Task_03;

public class ShapeCalculator {
    public static void main(String[] args) {
        System.out.println("Shape Calculator");
        System.out.println();

        // Create an array of Shape objects
        Shape[] shapes = {
                new Circle(5.0),
                new Rectangle(4.0, 6.0),
                new Triangle(3.0, 8.0)
        };

        // Loop through the array and display area of each shape
        for (Shape shape : shapes) {
            double area = shape.calculateArea();
            System.out.println("Area of " + shape + ": " + area);
        }
    }
}
