package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import com.codecool.dungeoncrawl.map.Tiles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.List;

public class Gui extends Pane {
    private final GameMap map;
    private final Canvas canvas;
    private final GraphicsContext context;
    private final RightGridPane rightGridPane;
    private final BottomGridPane bottomGridPane;
    private final InventoryMenu inventoryMenu;
    // private final Timeline timeline;

    public Gui() {
        this.map = MapLoader.loadMap();
        this.canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = new RightGridPane();
        this.bottomGridPane = new BottomGridPane();
        this.inventoryMenu = new InventoryMenu(context, canvas.getWidth() / 2, canvas.getHeight() / 2 - 50);
        // this.timeline = initTimeline();
    }

    private Timeline initTimeline() {
        return new Timeline(new KeyFrame(Duration.millis(300),
                event -> refresh()));
    }

    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = setUpBorderPane();
        setUpStage(primaryStage, borderPane);
    }

    private BorderPane setUpBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(rightGridPane.getUi());
        borderPane.setBottom(bottomGridPane.getUi());
        return borderPane;
    }

    private void setUpStage(Stage primaryStage, BorderPane borderPane) {
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);
        refresh();
        // keepRefreshing();
        // scene.setOnMouseClicked(mouseEvent -> System.out.println(mouseEvent.getPickResult()));
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        lookForItem(map.getPlayer().getCell());
        moveMonsters();
        map.refresh(context);
        rightGridPane.getHealthLabel().setText("" + map.getPlayer().getHealth());
    }

    private void moveMonsters() {
        List<Monster> monsters = map.getMonsters();
        monsters.removeIf(monster -> monster.getCell().getActor() == null);
        monsters.removeIf(monster -> !monster.isAlive());
        monsters.forEach(Monster::initMove);
    }

    // public void keepRefreshing() {
    //     timeline.setCycleCount(Animation.INDEFINITE);
    //     timeline.play();
    // }

    private void lookForItem(Cell cell) {
        if (cell.isActorAndItemSamePosition()) {
            rightGridPane.showPickUpButton(cell);
        } else {
            rightGridPane.hidePickUpButton();
        }
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
            case I -> {
                refresh();
                inventoryMenu.show(map.getPlayer().getInventory());
            }
        }
    }
}

