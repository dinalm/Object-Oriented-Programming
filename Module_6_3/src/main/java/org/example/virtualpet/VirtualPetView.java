package org.example.virtualpet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VirtualPetView extends Application {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private static final int PET_SIZE = 60;

    private Canvas canvas;
    private GraphicsContext gc;
    private Controller controller;
    private Image petImage;
    private Label statusLabel;
    private boolean mouseInCanvas = false;
    private double mouseX, mouseY;

    @Override
    public void init() {
        try {
            petImage = new Image(getClass().getResourceAsStream("/cat.jpg"), PET_SIZE, PET_SIZE, true, true);
        } catch (Exception e) {
            System.out.println("Could not load the image, using default drawing");
            petImage = null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Create canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create CurrencyConverterApp.controller
        controller = new Controller(this, CANVAS_WIDTH, CANVAS_HEIGHT);

        // Create status label
        statusLabel = new Label("Pet is waiting... Move your mouse over the canvas!");
        statusLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        // Set up mouse event handlers
        canvas.setOnMouseMoved(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
            mouseInCanvas = true;
            controller.handleMouseMoved(mouseX, mouseY);
            updateStatus();
        });

        canvas.setOnMouseEntered(event -> {
            mouseInCanvas = true;
            controller.handleMouseEntered();
        });

        canvas.setOnMouseExited(event -> {
            mouseInCanvas = false;
            controller.handleMouseExited();
            updateStatus();
        });

        // Create layout
        VBox root = new VBox();
        root.getChildren().addAll(canvas, statusLabel);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea, #764ba2); -fx-alignment: center;");

        // Create scene and show stage
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + 50);
        primaryStage.setTitle("Virtual Pet Walker - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Initial canvas update
        updateCanvas();
    }

    public void updateCanvas() {
        // Clear canvas with gradient background
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // Draw background gradient
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // Add some grass dots for environment
        gc.setFill(Color.color(0.13, 0.55, 0.13, 0.3));
        for (int i = 0; i < 50; i++) {
            double x = (i * 137.5) % CANVAS_WIDTH;
            double y = (i * 73.7) % CANVAS_HEIGHT;
            gc.fillOval(x, y, 4, 4);
        }

        // Draw pet
        drawPet(controller.getPetX(), controller.getPetY());

        // Draw mouse cursor if in canvas
        if (mouseInCanvas) {
            gc.setFill(Color.color(1, 1, 1, 0.7));
            gc.setStroke(Color.color(0, 0, 0, 0.5));
            gc.setLineWidth(2);
            gc.fillOval(mouseX - 8, mouseY - 8, 16, 16);
            gc.strokeOval(mouseX - 8, mouseY - 8, 16, 16);

            // Draw line showing pet's target if moving
            if (controller.isPetMoving()) {
                gc.setStroke(Color.color(1, 1, 1, 0.4));
                gc.setLineWidth(2);
                gc.setLineDashes(5);
                gc.strokeLine(controller.getPetX(), controller.getPetY(), mouseX, mouseY);
                gc.setLineDashes(null);
            }
        }
    }

    private void drawPet(double x, double y) {
        gc.save();
        gc.translate(x, y);

        // Calculate angle for pet rotation
        if (controller.isPetMoving()) {
            double deltaX = controller.getTargetX() - x;
            double deltaY = controller.getTargetY() - y;
            double angle = Math.atan2(deltaY, deltaX);
            gc.rotate(Math.toDegrees(angle));
        }

        // Use Image if loaded, otherwise fallback to drawn pet
        if (petImage != null) {
            gc.drawImage(petImage, -PET_SIZE/2, -PET_SIZE/2);
        } else {
            // Fallback: Draw pet using graphics (cute dog)
            gc.setFill(Color.CHOCOLATE);
            gc.fillOval(-PET_SIZE/2, -PET_SIZE/2, PET_SIZE, PET_SIZE);

            // Draw pet face
            gc.setFill(Color.SADDLEBROWN);
            gc.fillOval(-15, -20, 18, 18);

            // Draw eyes
            gc.setFill(Color.BLACK);
            gc.fillOval(-18, -22, 8, 8);
            gc.fillOval(-8, -22, 8, 8);

            // Draw nose
            gc.fillOval(-13, -12, 6, 6);

            // Draw ears
            gc.setFill(Color.SADDLEBROWN);
            gc.fillOval(-28, -35, 16, 24);
            gc.fillOval(4, -35, 16, 24);

            // Draw wagging tail with animation
            long time = System.currentTimeMillis();
            double tailWag = Math.sin(time * 0.01) * 10;
            gc.setStroke(Color.SADDLEBROWN);
            gc.setLineWidth(6);
            gc.strokeLine(20, 8, 35 + tailWag, -5 + tailWag/2);

            // Draw legs
            gc.setLineWidth(8);
            // Front legs
            gc.strokeLine(-12, 18, -12, 30);
            gc.strokeLine(-2, 18, -2, 30);
            // Back legs
            gc.strokeLine(8, 18, 8, 30);
            gc.strokeLine(18, 18, 18, 30);
        }

        gc.restore();
    }

    private void updateStatus() {
        if (!mouseInCanvas) {
            statusLabel.setText("Pet stopped - mouse left the canvas");
        } else if (controller.isPetMoving()) {
            statusLabel.setText("Pet is following you! 🐕💨");
        } else {
            statusLabel.setText("Pet reached you! 🎾");
        }
    }
}
