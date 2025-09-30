package org.example.virtualpet;

import javafx.animation.AnimationTimer;

public class Controller {
    private Pet pet;
    private VirtualPetView view;
    private AnimationTimer gameTimer;
    private boolean mouseInCanvas;

    public Controller(VirtualPetView view, double canvasWidth, double canvasHeight) {
        this.view = view;
        this.pet = new Pet(canvasWidth / 2, canvasHeight / 2);
        this.mouseInCanvas = false;

        // Start animation timer for smooth movement
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
            }
        };
        gameTimer.start();
    }

    private void updateGame() {
        if (mouseInCanvas) {
            pet.updatePosition();
        } else {
            pet.stopMoving();
        }
        view.updateCanvas();
    }

    public void handleMouseMoved(double mouseX, double mouseY) {
        mouseInCanvas = true;
        pet.setTarget(mouseX, mouseY);
    }

    public void handleMouseEntered() {
        mouseInCanvas = true;
    }

    public void handleMouseExited() {
        mouseInCanvas = false;
        pet.stopMoving();
    }

    // Getters for the CurrencyConverterApp.view
    public double getPetX() {
        return pet.getX();
    }

    public double getPetY() {
        return pet.getY();
    }

    public boolean isPetMoving() {
        return pet.isMoving();
    }

    public double getTargetX() {
        return pet.getTargetX();
    }

    public double getTargetY() {
        return pet.getTargetY();
    }
}
