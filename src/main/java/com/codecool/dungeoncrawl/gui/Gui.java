package com.codecool.dungeoncrawl.gui;
import com.codecool.dungeoncrawl.gui.menu.MainMenu;
import com.codecool.dungeoncrawl.logic.engine.Engine;
import com.codecool.dungeoncrawl.logic.engine.KeyboardHandler;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import com.codecool.dungeoncrawl.map.Tiles;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui {
    protected GameMap map;
    protected final Canvas canvas;
    protected final GraphicsContext context;
    protected final RightGridPane rightGridPane;
    protected final BottomGridPane bottomGridPane;
    protected final DisplayInventory displayInventory;
    protected final DisplayGameOver displayGameOver;
    protected final KeyboardHandler keyboardHandler;
    protected final Engine engine;
    protected Stage primaryStage;
    // private final Timeline timeline;

    public Gui() {
        this.map = MapLoader.loadMap("/map.txt");
        this.canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = new RightGridPane();
        this.bottomGridPane = new BottomGridPane();
        this.displayInventory = new DisplayInventory(context, canvas.getWidth() / 2, canvas.getHeight() / 2 - 50);
        this.displayGameOver = new DisplayGameOver(context, canvas.getWidth() / 2, canvas.getHeight() / 2);
        this.keyboardHandler = new KeyboardHandler(this, displayInventory, map);
        this.engine = new Engine(map, context, displayGameOver, rightGridPane, keyboardHandler);
        // this.timeline = initTimeline();
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        MainMenu mainMenu = new MainMenu(primaryStage);
        mainMenu.setUpMainMenu();
    }

    protected void startNewGame(Stage primaryStage) {
        BorderPane borderPane = setUpBorderPane();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(keyboardHandler::onKeyPressed);
        refresh();
        // cycleRefresh();
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

    public GraphicsContext getContext() {
        return context;
    }

    public RightGridPane getRightGridPane() {
        return rightGridPane;
    }

    public BottomGridPane getBottomGridPane() {
        return bottomGridPane;
    }

    public DisplayInventory getDisplayInventory() {
        return displayInventory;
    }

    public DisplayGameOver getDisplayGameOver() {
        return displayGameOver;
    }

    public KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }

    public Engine getEngine() {
        return engine;
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

