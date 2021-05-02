package com.codecool.dungeoncrawl.map;
import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import java.util.ArrayList;
import java.util.List;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private final GameMap gameMap;
    private final int x;
    private final int y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public boolean isPlayer() {
        return actor instanceof Player;

    }

    public CellType getType() {
        return type;
    }

    public boolean isMonster() {
        return actor instanceof Monster;

    }

    public Cell getNeighbor(int dx, int dy) {
        int iX = x + dx;
        int iY = y + dy;
        if (isOutOfBounds(iX, iY)) {
            return gameMap.getCell(x, y);
        } else if (isActor(iX, iY)) {
            return gameMap.getCell(x, y);
        } else return gameMap.getCell(iX, iY);
    }

    public List<Cell> getNeighbors() {
        List<Cell> neighbors = new ArrayList<>();
        int pX = x + 1;
        int pY = y + 1;
        int nX = x - 1;
        int nY = y - 1;

        if (pX <= gameMap.getWidth() - 1) {
            neighbors.add(gameMap.getCell(pX, y));
        }
        if (pY <= gameMap.getHeight() - 1) {
            neighbors.add(gameMap.getCell(x, pY));
        }
        if (nX >= 0 && nX <= gameMap.getWidth() - 1) {
            neighbors.add(gameMap.getCell(nX, y));
        }
        if (nY >= 0 && nY <= gameMap.getHeight() - 1) {
            neighbors.add(gameMap.getCell(x, nY));
        }
        return neighbors;
    }

    public boolean isActorAndItemSamePosition() {
        boolean sameXY = isItem() && getActor().getX() == getItem().getX() && getActor().getY() == getItem().getY();
        if (sameXY) {
            BottomGridPane.log("You see the " + item.getName());
        }
        return sameXY;
    }

    public boolean isActor(int iX, int iY) {
        return gameMap.getCell(iX, iY).actor != null;
    }

    public boolean isActor() {
        return actor != null;
    }

    public boolean isOutOfBounds(int iX, int iY) {
        return (iY < 0 || iY >= gameMap.getHeight() - 1) || (iX < 0 || iX >= gameMap.getWidth() - 1);
    }

    @Override
    public boolean isPassable() {
        return type.isPassable();
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isItem() {
        return item != null;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
