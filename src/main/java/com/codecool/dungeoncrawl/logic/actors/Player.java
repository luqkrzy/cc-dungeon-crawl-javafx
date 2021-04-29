package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HP;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.map.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        setHealth(100);
        setDefense(5);
        setAttack(5);
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public void addToInventory(Cell cell) {
        Item item = cell.getItem();
        BottomGridPane.log("picked up: " + item.getName());
        if (item instanceof Sword) {
            setAttack(getAttack() + ((Sword) item).getDamage());
        }
        if (item instanceof Armor) {
            setDefense(getDefense() + ((Armor) item).getDefense());
        }
        if (item instanceof HP) {
            setHealth(getHealth() + ((HP) item).getHealthPoints());
        } else {
            addItem(item);
        }
        cell.setItem(null);
    }

}
