package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.map.Tiles;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui {
    private final GameMap map = MapLoader.loadMap();
    private final Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private final RightGridPane rightGridPane = new RightGridPane();


    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(rightGridPane.getUi());
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> {
                map.getPlayer().move(0, -1);
                refresh();
            }
            case S -> {
                map.getPlayer().move(0, 1);
                refresh();
            }
            case A -> {
                map.getPlayer().move(-1, 0);
                refresh();
            }
            case D -> {
                map.getPlayer().move(1, 0);
                refresh();
            }
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.isActor()) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                    if (cell.isActorAndItemSamePosition()) {
                        rightGridPane.showPickUpButton2(cell);
                    } else {
                        // rightGridPane.hidePickUpButton();
                    }

                } else if (cell.isItem()) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }  else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        rightGridPane.getHealthLabel().setText("" + map.getPlayer().getHealth());
    }
}

