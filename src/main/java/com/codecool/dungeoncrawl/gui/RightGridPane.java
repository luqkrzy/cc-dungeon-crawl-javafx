package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.map.Cell;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RightGridPane extends Pane {
    // private final GridPane ui = new GridPane();
    private final Label healthLabel = new Label();
    private final Button button = new Button("Pick Up!");

    public RightGridPane() {
        build();
    }

    private void build() {
        ui.setPrefWidth(150);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label(" "), 0, 1);
    }

    public void showPickUpButton(Cell cell) {
        ui.getChildren().remove(button);
        ui.add(button, 1, 20);
        button.setOnAction(actionEvent -> {
            ui.getChildren().remove(button);
            if (cell.isItem() && cell.isActor()) {
                cell.getActor().addItem(cell.getItem());
                BottomGridPane.log("picked up: " + cell.getItem().getName());
                cell.setItem(null);
            }
        });
    }


    public void hidePickUpButton() {
        ui.getChildren().remove(button);
    }


    public Label getHealthLabel() {
        return healthLabel;
    }


}
