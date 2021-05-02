package com.codecool.dungeoncrawl.gui.menu;

import com.codecool.dungeoncrawl.gui.Gui;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GameMenu {
    private final MenuItemTitle menuItemTitle;
    private final VBox vbox;
    private final Gui gui;

    public GameMenu(Gui gui, MenuItemTitle menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
        this.vbox = new VBox(10);
        this.gui = gui;
    }

    public void setUpMenu() {
        Group group = setupButtons();
        setUpVbox(group);
        Scene scene = new Scene(vbox, gui.getCanvas().getWidth(), gui.getCanvas().getHeight());
        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().show();
    }

    private void setUpVbox(Group group) {
        vbox.getChildren().addAll(group.getChildren());
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #929295");
    }

    private Group setupButtons() {
        Label label = new Label(menuItemTitle.getTitle());
        label.setTranslateY(-100);
        label.setScaleX(4);
        label.setScaleY(4);

        Button newGameBtn = new Button(MenuItemTitle.NEW_GAME.getTitle());
        Button loadGameBtn = new Button(MenuItemTitle.LOAD_GAME.getTitle());
        Button quitGameBtn = new Button(MenuItemTitle.QUIT_GAME.getTitle());
        Button OkBtn = new Button(MenuItemTitle.CONFIRM.getTitle());
        TextField textField = createNewUserName();

        newGameBtn.setMaxWidth(200);
        loadGameBtn.setMaxWidth(200);
        quitGameBtn.setMaxWidth(200);
        newGameBtn.setDefaultButton(true);
        newGameBtn.setOnMouseClicked(actionEvent -> {
            vbox.getChildren().add(textField);
            vbox.getChildren().add(OkBtn);
        });
        OkBtn.setOnMouseClicked(actionEvent -> gui.startNewGame(textField.getText()));
        quitGameBtn.setOnMouseClicked(actionEvent -> gui.getPrimaryStage().close());

        return new Group(label, newGameBtn, loadGameBtn, quitGameBtn);
    }

    private TextField createNewUserName() {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        textField.setPromptText("Nick");
        return textField;
    }


}


