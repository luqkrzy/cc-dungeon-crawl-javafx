package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.engine.FightEngine;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.Drawable;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.model.ActorModel;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Drawable {
    protected String name;
    protected Cell cell;
    protected int health = 10;
    protected int defense;
    protected int attack;
    protected List<Item> inventory;
    protected FightEngine fightEngine = new FightEngine();

    public Actor(Cell cell) {
        this.cell = cell;
        this.inventory = new ArrayList<>();
        this.cell.setActor(this);
    }

    public Actor(ActorModel actorModel, GameMap gameMap) {
        this.name = actorModel.getActorName();
        this.cell = new Cell(gameMap, actorModel.getX(), actorModel.getY(), CellType.FLOOR);
        this.health = actorModel.getX();
        this.defense = actorModel.getDefense();
        this.attack = actorModel.getAttack();
        this.inventory = actorModel.getInventory();
        this.cell.setActor(this);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.getInstanceName();
    }

    public GameMap getMap() {
        return cell.getGameMap();
    }
}
