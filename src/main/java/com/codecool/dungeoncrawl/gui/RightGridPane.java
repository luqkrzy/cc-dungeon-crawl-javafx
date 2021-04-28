package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RightGridPane extends Pane {
    // private final GridPane ui = new GridPane();
    private final Label healthLabel = new Label();
    private final Label attackLabel = new Label();
    private final Label defenseLabel = new Label();
    private final Button button = new Button("Pick Up!");

    public RightGridPane() {
        build();
    }

    private void build() {
        ui.setPrefWidth(150);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(attackLabel, 1, 1);
        ui.add(new Label("Defense: "), 0, 2);
        ui.add(defenseLabel, 1, 2);

        ui.add(new Label(" "), 0, 3);
    }

    public void showPickUpButton(Cell cell) {
        ui.getChildren().remove(button);
        ui.add(button, 1, 20);
        button.setOnAction(actionEvent -> {
            ui.getChildren().remove(button);
            Player player = (Player) cell.getActor();
            player.addToInventory(cell);
        });
    }


    public void hidePickUpButton() {
        ui.getChildren().remove(button);
    }


    public Label getHealthLabel() {
        return healthLabel;
    }

    public Label getAttackLabel() {
        return attackLabel;
    }

    public Label getDefenseLabel() {
        return defenseLabel;
    }
}
