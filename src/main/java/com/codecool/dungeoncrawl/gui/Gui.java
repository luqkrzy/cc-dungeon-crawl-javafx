package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.Engine;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import com.codecool.dungeoncrawl.map.Tiles;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui {
    protected final GameMap map;
    protected final Canvas canvas;
    protected final GraphicsContext context;
    protected final RightGridPane rightGridPane;
    protected final BottomGridPane bottomGridPane;
    protected final DisplayInventory displayInventory;
    protected final DisplayGameOver displayGameOver;
    protected final Engine engine;
    // private final Timeline timeline;

    public Gui() {
        this.map = loadMap("/map.txt");
        this.canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = new RightGridPane();
        this.bottomGridPane = new BottomGridPane();
        this.displayInventory = new DisplayInventory(context, canvas.getWidth() / 2, canvas.getHeight() / 2 - 50);
        this.displayGameOver = new DisplayGameOver(context, canvas.getWidth() / 2, canvas.getHeight() / 2);
        this.engine = new Engine(map, context, displayGameOver, rightGridPane, displayInventory, canvas);
        // this.timeline = initTimeline();
    }

    private GameMap loadMap(String mapName) {
        return MapLoader.loadMap(mapName);
    }

    // private Timeline initTimeline() {
    //     return new Timeline(new KeyFrame(Duration.millis(300),
    //             event -> refresh()));
    // }

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
        scene.setOnKeyPressed(engine::onKeyPressed);
        engine.refresh();
        // keepRefreshing();
        // scene.setOnMouseClicked(mouseEvent -> System.out.println(mouseEvent.getPickResult()));
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    // private void refresh() {
    //     context.setFill(Color.BLACK);
    //     context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    //     engine.gamePlay();
    // }


    // private void onKeyPressed(KeyEvent keyEvent) {
    //     switch (keyEvent.getCode()) {
    //         case W -> {
    //             map.getPlayer().move(0, -1);
    //             refresh();
    //         }
    //         case S -> {
    //             map.getPlayer().move(0, 1);
    //             refresh();
    //         }
    //         case A -> {
    //             map.getPlayer().move(-1, 0);
    //             refresh();
    //         }
    //         case D -> {
    //             map.getPlayer().move(1, 0);
    //             refresh();
    //         }
    //         case I -> {
    //             refresh();
    //             displayInventory.show(map.getPlayer().getInventory());
    //         }
    //     }
    // }
}

