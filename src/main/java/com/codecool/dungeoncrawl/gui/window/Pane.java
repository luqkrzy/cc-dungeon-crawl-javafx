package com.codecool.dungeoncrawl.gui.window;
import javafx.scene.layout.GridPane;

public abstract class Pane {
    protected final GridPane ui = new GridPane();

    public GridPane getUi() {
        return ui;
    }
}
