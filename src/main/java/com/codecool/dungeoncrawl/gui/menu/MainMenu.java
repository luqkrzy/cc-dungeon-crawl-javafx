package com.codecool.dungeoncrawl.gui.menu;

import com.codecool.dungeoncrawl.gui.Gui;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Gui {
    private Stage primaryStage;

    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setUpMainMenu() {
        Button newGameBtn = new Button("New Game");
        Button loadGameBtn = new Button("Load Game");
        Button quitGameBtn = new Button("Quit Game");
        Label label = new Label("DUNGEON CRAWL");
        label.setTranslateY(-100);
        label.setScaleX(4);
        label.setScaleY(4);
        newGameBtn.setMaxWidth(200);
        loadGameBtn.setMaxWidth(200);
        quitGameBtn.setMaxWidth(200);
        newGameBtn.setDefaultButton(true);
        newGameBtn.setOnMouseClicked(actionEvent -> startNewGame(primaryStage));
        quitGameBtn.setOnMouseClicked(actionEvent -> primaryStage.close());
        VBox vbox = new VBox(10, label, newGameBtn, loadGameBtn, quitGameBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #929295");
        Scene scene = new Scene(vbox, canvas.getWidth(), canvas.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();


    }


}


