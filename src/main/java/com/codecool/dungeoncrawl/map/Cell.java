package com.codecool.dungeoncrawl.map;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private GameMap gameMap;
    private int x, y;

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

    public CellType getType() {
        return type;
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

    public Cell getNeighbor(int dx, int dy) {
        int iX = x + dx;
        int iY = y + dy;
        boolean isOutOfBounds = isOutOfBounds(iX, iY);
        boolean isActor = isActor(iX, iY);
        if (isOutOfBounds || isActor) {
            return gameMap.getCell(x, y);
        } else return gameMap.getCell(iX, iY);
    }

    public boolean isActorAndItemSamePosition() {
        return isItem() && getActor().getX() == getItem().getX() && getActor().getY() == getItem().getY();
    }




    public boolean isActor(int iX, int iY) {
        return gameMap.getCell(iX, iY).actor != null;
    }

    public boolean isActor() {
        return actor != null;
    }

    private boolean isOutOfBounds(int iX, int iY) {
        return iY >= gameMap.getHeight() || iX >= gameMap.getWidth();
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
}
