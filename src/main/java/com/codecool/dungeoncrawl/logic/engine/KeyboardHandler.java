package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.gui.DisplayInventory;
import com.codecool.dungeoncrawl.gui.Gui;
import com.codecool.dungeoncrawl.map.GameMap;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler {
    private GameMap map;
    private final DisplayInventory displayInventory;
    private final Gui gui;
    // private final Engine engine;

    public KeyboardHandler(Gui gui, DisplayInventory displayInventory, GameMap map) {
        // this.engine = engine;
        this.gui = gui;
        this.map = map;
        this.displayInventory = displayInventory;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> {
                map.getPlayer().move(0, -1);
                gui.refresh();
            }
            case S -> {
                map.getPlayer().move(0, 1);
                gui.refresh();
            }
            case A -> {
                map.getPlayer().move(-1, 0);
                gui.refresh();
            }
            case D -> {
                map.getPlayer().move(1, 0);
                gui.refresh();
            }
            case I -> {
                gui.refresh();
                displayInventory.show(map.getPlayer().getInventory());
            }
        }
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
