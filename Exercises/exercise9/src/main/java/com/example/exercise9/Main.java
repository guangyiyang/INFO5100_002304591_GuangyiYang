package com.example.exercise9;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class Main extends Application {

    private TextField firstInputField;
    private TextField secondInputField;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");

        firstInputField = new TextField();
        firstInputField.setPrefWidth(100);
        firstInputField.setPrefHeight(25);
        secondInputField = new TextField();
        secondInputField.setPrefWidth(100);
        secondInputField.setPrefHeight(25);
        resultLabel = new Label(" ");
        resultLabel.setPrefHeight(25);


        Button addButton = new Button("+");
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(25);
        addButton.setOnAction(e -> calculate("plus"));

        Button subtractButton = new Button("-");
        subtractButton.setPrefWidth(100);
        subtractButton.setPrefHeight(25);
        subtractButton.setOnAction(e -> calculate("minus"));

        Button multiplyButton = new Button("*");
        multiplyButton.setPrefWidth(100);
        multiplyButton.setPrefHeight(25);
        multiplyButton.setOnAction(e -> calculate("times"));

        Button divideButton = new Button("/");
        divideButton.setPrefWidth(100);
        divideButton.setPrefHeight(25);
        divideButton.setOnAction(e -> calculate("divided by"));

        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(200);
        clearButton.setPrefHeight(25);
        clearButton.setOnAction(e -> clear());

        GridPane grid = new GridPane();

        grid.add(addButton, 0, 2);
        grid.add(subtractButton, 1, 2);
        grid.add(multiplyButton, 1, 1);
        grid.add(divideButton, 0, 1);
        grid.add(new Label(" "), 0, 3);
        grid.add(firstInputField, 0, 3);
        grid.add(new Label(" "), 1, 3);
        grid.add(secondInputField, 1, 3);
        grid.add(resultLabel, 0, 5, 2, 1);
        GridPane.setHalignment(resultLabel, HPos.CENTER);
        GridPane.setValignment(resultLabel, VPos.CENTER);

        grid.add(clearButton, 0, 7, 2,1);


        Scene scene = new Scene(grid, 250, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate(String operation) {
        try {
            double num1 = Double.parseDouble(firstInputField.getText().trim());
            double num2 = Double.parseDouble(secondInputField.getText().trim());
            double result = 0;

            switch (operation) {
                case "plus":
                    result = num1 + num2;
                    break;
                case "minus":
                    result = num1 - num2;
                    break;
                case "times":
                    result = num1 * num2;
                    break;
                case "divided by":
                    if (num2 == 0) {
                        resultLabel.setText("Cannot divide by zero.");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            resultLabel.setText(num1 + " " + operation + " " + num2 + " equals " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter numbers.");
        }
    }

    private void clear() {
        firstInputField.clear();
        secondInputField.clear();
        resultLabel.setText("Result: ");
    }
}