package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.map.Cell;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RightGridPane {
    private final GridPane ui = new GridPane();
    private final Label healthLabel = new Label();
    private final Button button = new Button("Pick Up!");

    public RightGridPane() {
        build();

    }

    private void build() {
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

    }

    public void showPickUpButton() {
        ui.getChildren().remove(button);
        ui.add(button, 1, 20);
        button.setOnAction(actionEvent -> ui.getChildren().remove(button));

    }

    public void showPickUpButton2(Cell cell) {
        ui.getChildren().remove(button);
        ui.add(button, 1, 20);
        button.setOnAction(actionEvent -> {
            ui.getChildren().remove(button);
            cell.setItem(null);
        });


    }


    public void hidePickUpButton() {
        ui.getChildren().remove(button);
    }


    public GridPane getUi() {
        return ui;
    }

    public Label getHealthLabel() {
        return healthLabel;
    }


}
