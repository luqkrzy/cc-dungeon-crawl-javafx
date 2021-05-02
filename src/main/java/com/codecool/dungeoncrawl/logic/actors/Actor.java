package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.engine.FightEngine;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.Drawable;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health = 10;
    protected int defense;
    protected int attack;
    private final List<Item> inventory;
    protected final FightEngine fightEngine;

    public Actor(Cell cell) {
        this.cell = cell;
        this.inventory = new ArrayList<>();
        this.cell.setActor(this);
        this.fightEngine = new FightEngine();
    }

    public void move(int dx, int dy) {
        if (health <= 0) {
            die();
        } else {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.isPassable()) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                if (nextCell.getType().equals(CellType.SPIKE)) {
                    health -= 1;
                    BottomGridPane.log("-1HP, watch out spikes!");
                }
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    public Cell getCell() {
        return cell;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }


    public String getInstanceName() {
        return this.getClass().getSimpleName();
    }

    public void die() {
        cell.setActor(null);
        dropItem();
    }

    private void dropItem() {
        if (inventory.size() > 0) {
            Item item = inventory.get(0);
            item.setCell(cell);
            cell.setItem(item);
        }
    }

    @Override
    public boolean isPassable() {
        return false;
    }

}
