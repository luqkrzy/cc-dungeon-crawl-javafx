package com.codecool.dungeoncrawl.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class BottomGridPane extends Pane {
    private static final Label logMessage1 = new Label(" ");
    private static final Label logMessage2 = new Label(" ");
    private static final Label logMessage3 = new Label(" ");
    private static final Label logMessage4 = new Label(" ");


    public BottomGridPane() {
        build();
    }

    private void build() {
        ui.getChildren().removeAll(logMessage1, logMessage2, logMessage3);
        ui.setPrefHeight(50);
        ui.setPadding(new Insets(5));
        ui.add(logMessage3, 0, 0);
        ui.add(logMessage2, 0, 1);
        ui.add(logMessage1, 0, 2);

    }

    public static void showInventory(String message) {
        logMessage4.setText(message);

    }

    public static void log(String message){
        if (logMessage1.getText().length() == 0){
            logMessage1.setText(message);
        }
        else if (logMessage2.getText().length() == 0){
            logMessage2.setText(logMessage1.getText());
            logMessage1.setText(message);
        }
        else {
            logMessage3.setText(logMessage2.getText());
            logMessage2.setText(logMessage1.getText());
            logMessage1.setText(message);
        }
    }
}
