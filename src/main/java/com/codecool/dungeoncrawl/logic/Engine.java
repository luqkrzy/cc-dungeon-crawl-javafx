package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.gui.DisplayGameOver;
import com.codecool.dungeoncrawl.gui.DisplayInventory;
import com.codecool.dungeoncrawl.gui.RightGridPane;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.List;

public class Engine {

    private final GameMap map;
    private final Player player;
    private final GraphicsContext context;
    private final DisplayGameOver displayGameOver;
    private final RightGridPane rightGridPane;
    private final DisplayInventory displayInventory;
    private final Canvas canvas;

    public Engine(GameMap map, GraphicsContext context, DisplayGameOver displayGameOver, RightGridPane rightGridPane, DisplayInventory displayInventory, Canvas canvas) {
        // this.map = loadMap("/map.txt");
        this.map = map;
        this.player = map.getPlayer();
        this.context = context;
        this.displayGameOver = displayGameOver;
        this.displayInventory = displayInventory;
        this.rightGridPane = rightGridPane;
        this.canvas = canvas;
    }

    private GameMap loadMap(String mapName) {
        return MapLoader.loadMap(mapName);
    }

    public void gamePlay() {
        if (!player.isAlive()) {
            gameOver();
        } else {
            BottomGridPane.log(String.format("x: %d, y: %d", player.getCell().getX(), player.getCell().getY()));
            lookForItem(map.getPlayer().getCell());
            moveMonsters();
            refreshLabels();
            map.refresh(context);
        }
    }

    private void gameOver() {
        BottomGridPane.log("GAME OVER!");
        map.refresh(context);
        displayGameOver.show();
    }

    private void lookForItem(Cell cell) {
        if (cell.isActorAndItemSamePosition()) {
            rightGridPane.showPickUpButton(cell);
        } else {
            rightGridPane.hidePickUpButton();
        }
    }

    private void moveMonsters() {
        List<Monster> monsters = map.getMonsters();
        monsters.removeIf(monster -> monster.getCell().getActor() == null);
        monsters.removeIf(monster -> !monster.isAlive());
        monsters.forEach(Monster::initMove);
    }

    private void refreshLabels() {
        rightGridPane.getHealthLabel().setText("" + map.getPlayer().getHealth());
        rightGridPane.getAttackLabel().setText("" + map.getPlayer().getAttack());
        rightGridPane.getDefenseLabel().setText("" + map.getPlayer().getDefense());
    }


    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gamePlay();
    }


    public void onKeyPressed(KeyEvent keyEvent) {
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
                displayInventory.show(map.getPlayer().getInventory());
            }
        }
    }


}
