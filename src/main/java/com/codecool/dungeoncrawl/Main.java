package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.gui.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private final GameController gameController = new GameController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameController.start(primaryStage);

    }
}
