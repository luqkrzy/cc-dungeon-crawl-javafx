package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.db.GameDatabaseManager;
import com.codecool.dungeoncrawl.gui.menu.GameMenu;
import com.codecool.dungeoncrawl.gui.menu.MenuItemTitle;
import com.codecool.dungeoncrawl.gui.window.*;
import com.codecool.dungeoncrawl.logic.engine.Engine;
import com.codecool.dungeoncrawl.logic.engine.KeyboardHandler;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import com.codecool.dungeoncrawl.map.Tiles;
import com.codecool.dungeoncrawl.model.GameSaveModel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class GameController {
    private GameDatabaseManager dbm;
    private GameMap map;
    private Canvas canvas;
    private GraphicsContext context;
    private RightGridPane rightGridPane;
    private BottomGridPane bottomGridPane;
    private DisplayInventory displayInventory;
    private KeyboardHandler keyboardHandler;
    private Engine engine;
    private Stage primaryStage;

    public GameController() {
        build();
    }

    private void build() {
        this.dbm = new GameDatabaseManager();
        this.map = MapLoader.loadMap("/map.txt", "anonymous");
        this.canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = new RightGridPane();
        this.bottomGridPane = new BottomGridPane();
        this.displayInventory = new DisplayInventory(context, canvas.getWidth() / 2, canvas.getHeight() / 2 - 50);
        this.keyboardHandler = new KeyboardHandler(this);
        this.engine = new Engine(map, context, rightGridPane, keyboardHandler);
    }

    void build(GameSaveModel gameSaveModel) {
        this.dbm = new GameDatabaseManager();
        this.map = MapLoader.loadMap(gameSaveModel);
        this.canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = new RightGridPane();
        this.bottomGridPane = new BottomGridPane();
        this.displayInventory = new DisplayInventory(context, canvas.getWidth() / 2, canvas.getHeight() / 2 - 50);
        this.keyboardHandler = new KeyboardHandler(this);
        this.engine = new Engine(map, context, rightGridPane, keyboardHandler);
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GameMenu mainMenu = new GameMenu(this, MenuItemTitle.DUNGEON_CRAWL);
        mainMenu.initMainMenu();
    }


    private void setUpGameOverMenu() {
        GameMenu gameOverMenu = new GameMenu(this, MenuItemTitle.GAME_OVER);
        engine.setGameOverMenu(gameOverMenu);
    }

    public void popUpSaveWindow() {
        String saveName = SaveWindow.popUp();
        if (!saveName.isEmpty()) dbm.save(map.getPlayer(), saveName);

    }

    public void startNewGame(String playerName) {
        if (playerName.length() > 0) {
            map.getPlayer().setName(playerName);
            run();
        }
    }

    void run() {
        BorderPane borderPane = setUpBorderPane();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        setUpGameOverMenu();
        scene.setOnKeyPressed(keyboardHandler::onKeyPressed);
        primaryStage.setTitle(MenuItemTitle.DUNGEON_CRAWL.getTitle());
        primaryStage.show();
        refresh();
    }

    private BorderPane setUpBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(rightGridPane.getUi());
        borderPane.setBottom(bottomGridPane.getUi());
        return borderPane;
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        engine.gamePlay();
    }

    public GameMap getMap() {
        return map;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public DisplayInventory getDisplayInventory() {
        return displayInventory;
    }

    public List<GameSaveModel> getAllSaves() {
        return dbm.getAllGameSaves();

    }
}

