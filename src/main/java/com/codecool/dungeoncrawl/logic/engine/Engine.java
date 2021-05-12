package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.gui.menu.GameMenu;
import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.gui.window.RightGridPane;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Engine {

    private GameMap map;
    private final GraphicsContext context;
    private final RightGridPane rightGridPane;
    private final KeyboardHandler keyboardHandler;
    private GameMenu gameOverMenu;

    public Engine(GameMap map, GraphicsContext context, RightGridPane rightGridPane, KeyboardHandler keyboardHandler) {
        this.map = map;
        this.context = context;
        this.rightGridPane = rightGridPane;
        this.keyboardHandler = keyboardHandler;
    }

    public void gamePlay() {
        Player player = map.getPlayer();
        if (!player.isAlive()) {
            gameOver();
        } else {
            BottomGridPane.log(String.format("x: %d, y: %d", player.getCell().getX(), player.getCell().getY()));
            lookForItem(map.getPlayer().getCell());
            moveMonsters();
            refreshLabels();
            nextMap(player);
            map.refresh(context);
        }
    }

    private void nextMap(Player player) {
        if (player.getCell().getType().equals(CellType.STAIRS)) {
            GameMap map = MapLoader.loadMap("/map_02.txt", player.getName());
            this.map = map;
            keyboardHandler.setMap(map);
        }
    }

    private void gameOver() {
        BottomGridPane.log("GAME OVER!");
        map.refresh(context);
        gameOverMenu.initMainMenu();
    }

    private void lookForItem(Cell cell) {
        if (cell.isActorAndItemSamePosition()) {
            BottomGridPane.log("You see the " + cell.getItem().getName());
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

    public void setGameOverMenu(GameMenu gameOverMenu) {
        this.gameOverMenu = gameOverMenu;

    }

}
