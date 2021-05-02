package com.codecool.dungeoncrawl.gui.window;

import com.codecool.dungeoncrawl.gui.menu.MenuItemTitle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SaveWindow {
    private final static Stage savePopup = new Stage(StageStyle.DECORATED);

    public static void popUp() {
        savePopup.setTitle(MenuItemTitle.SAVE.getTitle());
        Button saveButton = new Button(MenuItemTitle.SAVE.getTitle());
        Button cancelButton = new Button(MenuItemTitle.CANCEL.getTitle());
        saveButton.setOnAction(event -> {
            System.out.println("Save to database");
            close();
        });
        cancelButton.setOnAction(event -> close());
        HBox hBox = new HBox(saveButton, cancelButton);
        Scene scene = new Scene(hBox);
        savePopup.setScene(scene);
        savePopup.showAndWait();
    }

    private static void close() {
        savePopup.hide();
        savePopup.close();
    }

}
