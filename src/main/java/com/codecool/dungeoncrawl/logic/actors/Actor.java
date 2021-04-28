package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.fight.FightEngine;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.Drawable;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health = 10;
    private int defense;
    private int attack;
    private final List<Item> inventory;
    protected FightEngine fightEngine;

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
            boolean outOfBounds = cell.isOutOfBounds(cell.getX() + dx, cell.getY() + dy);
            boolean isActor = cell.isActor(cell.getX() + dx, cell.getY() + dy);
            if ((!outOfBounds && !isActor) && nextCell.isPassable()) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                // } else if (nextCell.isActor() &&  nextCell.isMonster()) {
                //     fightEngine.fight((Player) cell.getActor(), (Monster) nextCell.getActor());
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

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
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

    public String getInstanceName() {
        return this.getClass().getSimpleName();
    }

    public void die() {
        cell.setActor(null);
    }

}
