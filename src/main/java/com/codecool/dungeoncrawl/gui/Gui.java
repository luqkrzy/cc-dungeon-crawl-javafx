package com.codecool.dungeoncrawl.gui;
import com.codecool.dungeoncrawl.map.Tiles;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
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

public class Gui extends Pane {
    private final GameMap map = MapLoader.loadMap();
    private final Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private final RightGridPane rightGridPane = new RightGridPane();
    private final BottomGridPane bottomGridPane = new BottomGridPane();
    private final InventoryMenu inventoryMenu = new InventoryMenu(context, canvas.getWidth()/ 2, canvas.getHeight() / 2-50);
    // private final Timeline timeline;

    public Gui() {
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

    private BorderPane setUpBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(rightGridPane.getUi());
        borderPane.setBottom(bottomGridPane.getUi());
        return borderPane;
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        lookForItem(map.getPlayer().getCell());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.isActor()) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.isItem()) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }  else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        rightGridPane.getHealthLabel().setText("" + map.getPlayer().getHealth());
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

