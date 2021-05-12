package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.gui.window.DisplayInventory;
import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.map.GameMap;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler {
    private GameMap map;
    private final DisplayInventory displayInventory;
    private final GameController gameController;
    // private final Engine engine;

    public KeyboardHandler(GameController gameController) {
        // this.engine = engine;
        this.gameController = gameController;
        this.map = gameController.getMap();
        this.displayInventory = gameController.getDisplayInventory();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> {
                map.getPlayer().move(0, -1);
                gameController.refresh();
            }
            case S -> {
                map.getPlayer().move(0, 1);
                gameController.refresh();
            }
            case A -> {
                map.getPlayer().move(-1, 0);
                gameController.refresh();
            }
            case D -> {
                map.getPlayer().move(1, 0);
                gameController.refresh();
            }
            case I -> {
                gameController.refresh();
                displayInventory.show(map.getPlayer().getInventory());
            }

            case F -> {
                if (keyEvent.isControlDown()) {
                    gameController.popUpSaveWindow();
                }
            }
        }


    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
