package com.codecool.dungeoncrawl.gui.window;

import com.codecool.dungeoncrawl.gui.menu.MenuItemTitle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SaveWindow {
    private final static Stage savePopup = new Stage(StageStyle.DECORATED);


    public static String popUp() {
        savePopup.setTitle(MenuItemTitle.SAVE.getTitle());
        Label saveName = new Label(MenuItemTitle.SAVE_NAME.getTitle());
        TextField saveNameField = new TextField();
        HBox nameBox = new HBox(saveName, saveNameField);
        Button saveButton = new Button(MenuItemTitle.SAVE.getTitle());
        Button cancelButton = new Button(MenuItemTitle.CANCEL.getTitle());
        saveButton.setOnAction(event -> {
            if (saveNameField.getText().length() > 0) {
                System.out.println("Save to database");
                close();
            }
        });
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(event -> close());
        HBox decisionBox = new HBox(saveButton, cancelButton);
        decisionBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(10, nameBox, decisionBox);
        Scene scene = new Scene(vBox);
        savePopup.setScene(scene);
        savePopup.showAndWait();
        return saveNameField.getText();
    }

    private static void close() {
        savePopup.hide();
        savePopup.close();
    }

}
