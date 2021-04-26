package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private final Gui gui = new Gui();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui.start(primaryStage);

    }
}
