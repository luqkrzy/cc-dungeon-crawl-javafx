package com.codecool.dungeoncrawl.logic.engine;
import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.gui.DisplayGameOver;
import com.codecool.dungeoncrawl.gui.RightGridPane;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.map.MapLoader;
import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public class Engine {

    private GameMap map;
    private final GraphicsContext context;
    private final DisplayGameOver displayGameOver;
    private final RightGridPane rightGridPane;
    private final KeyboardHandler keyboardHandler;

    public Engine(GameMap map, GraphicsContext context, DisplayGameOver displayGameOver, RightGridPane rightGridPane, KeyboardHandler keyboardHandler) {
        this.map = map;
        this.context = context;
        this.displayGameOver = displayGameOver;
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
        if (player.getX() == 1 && player.getY() == 1) {
            GameMap map = MapLoader.loadMap("/map2.txt");
            this.map = map;
            keyboardHandler.setMap(map);
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
}
