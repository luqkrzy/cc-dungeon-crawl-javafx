package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.db.GameDatabaseManager;
import com.codecool.dungeoncrawl.gui.menu.GameMenu;
import com.codecool.dungeoncrawl.gui.menu.MenuItemTitle;
import com.codecool.dungeoncrawl.gui.window.*;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.engine.Engine;
import com.codecool.dungeoncrawl.logic.engine.KeyboardHandler;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.map.*;
import com.codecool.dungeoncrawl.model.ActorModel;
import com.codecool.dungeoncrawl.model.GameSaveModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private GameDatabaseManager dbm;
    private GameMap map;
    private Canvas canvas;
    private GraphicsContext context;
    private RightGridPane rightGridPane;
    private BottomGridPane bottomGridPane;
    private DisplayInventory displayInventory;
    private DisplayGameOver displayGameOver;
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
        this.displayGameOver = new DisplayGameOver(context, canvas.getWidth() / 2, canvas.getHeight() / 2);
        this.keyboardHandler = new KeyboardHandler(this);
        this.engine = new Engine(map, context, displayGameOver, rightGridPane, keyboardHandler);
    }


    private void build(GameSaveModel gameSaveModel) {
        this.dbm = new GameDatabaseManager();
        this.map = MapLoader.loadMap(gameSaveModel);
        this.canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = new RightGridPane();
        this.bottomGridPane = new BottomGridPane();
        this.displayInventory = new DisplayInventory(context, canvas.getWidth() / 2, canvas.getHeight() / 2 - 50);
        this.displayGameOver = new DisplayGameOver(context, canvas.getWidth() / 2, canvas.getHeight() / 2);
        this.keyboardHandler = new KeyboardHandler(this);
        this.engine = new Engine(map, context, displayGameOver, rightGridPane, keyboardHandler);
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        connectToDatabase();
        GameMenu mainMenu = new GameMenu(this, MenuItemTitle.DUNGEON_CRAWL);
        mainMenu.initMainMenu();
    }

    private void connectToDatabase() {
        try {
            dbm.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void loadGame(GameSaveModel gameSave) {
        build(gameSave);

        Player player = new Player(gameSave.getPlayerModel(), map);
        List<ActorModel> monstersModel = gameSave.getMonsters();
        List<Monster> monsters = new ArrayList<>();
        monstersModel.forEach(monster -> monsters.add(loadMonsters(monster, map)));
        List<ItemModel> mapItemsModel = gameSave.getMapItems();
        List<Item> mapItems = new ArrayList<>();
        mapItemsModel.forEach(itemModel -> mapItems.add(loadItems(itemModel)));
        mapItems.forEach(item -> map.getCell(item.getX(), item.getY()).setItem(item));
        map.setMonsters(monsters);
        run();
    }

    private Item loadItems(ItemModel itemModel) {
        return getItem(map, itemModel.getX(), itemModel.getY(), itemModel.getItemType(), itemModel.getValue());
    }

    private Item getItem(GameMap gameMap, int x, int y, int type, double value) {
        Item item = null;
        switch (type) {
            case 1 -> item = new Key(new Cell(gameMap, x, y, CellType.FLOOR), value);
            case 2 -> item = new Sword(new Cell(gameMap, x, y, CellType.FLOOR), (int) value);
            case 3 -> item = new HP(new Cell(gameMap, x, y, CellType.FLOOR), (int) value);
            case 4 -> item = new Armor(new Cell(gameMap, x, y, CellType.FLOOR), (int) value);
        }
        return item;
    }

    private void run() {
        BorderPane borderPane = setUpBorderPane();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        setUpGameOverMenu();
        scene.setOnKeyPressed(keyboardHandler::onKeyPressed);
        primaryStage.setTitle(MenuItemTitle.DUNGEON_CRAWL.getTitle());
        primaryStage.show();
        refresh();
    }

    private Monster loadMonsters(ActorModel monster, GameMap gameMap) {
        Monster mon = null;
        switch (monster.getType()) {
            case "Skeleton" -> mon = new Skeleton(monster, gameMap);
            case "Ghost" -> mon = new Ghost(monster, gameMap);
            case "Mage" -> mon = new Mage(monster, gameMap);
        }
        return mon;
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


    // public void cycleRefresh() {
    //     timeline.setCycleCount(Animation.INDEFINITE);
    //     timeline.play();
    // }

    // public void setMap(GameMap map) {
    //     this.map = map;
    // }

    // private Timeline initTimeline() {
    //     return new Timeline(new KeyFrame(Duration.millis(500), event -> engine.refresh()));
    // }

}

