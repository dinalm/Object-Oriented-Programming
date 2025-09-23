package org.example.virtualpet;

public class Pet {
    private double x;
    private double y;
    private double targetX;
    private double targetY;
    private double speed;
    private boolean isMoving;

    private static final double DEFAULT_SPEED = 2.0;
    private static final double STOP_DISTANCE = 5.0;

    public Pet(double x, double y) {
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
        this.speed = DEFAULT_SPEED;
        this.isMoving = false;
    }

    public void setTarget(double targetX, double targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void updatePosition() {
        double distance = getDistance(x, y, targetX, targetY);

        if (distance > STOP_DISTANCE) {
            isMoving = true;

            // Calculate direction using trigonometry
            double deltaX = targetX - x;
            double deltaY = targetY - y;

            // Normalize the direction vector
            double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double directionX = deltaX / length;
            double directionY = deltaY / length;

            // Move pet towards target
            x += directionX * speed;
            y += directionY * speed;
        } else {
            isMoving = false;
        }
    }

    public void stopMoving() {
        isMoving = false;
    }

    private double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }
}
