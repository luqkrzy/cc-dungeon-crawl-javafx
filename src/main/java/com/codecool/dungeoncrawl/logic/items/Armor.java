package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;

public class Armor extends Item {
    private final int defense;

    public Armor(Cell cell, String name, int defense) {
        super(cell, name);
        this.defense = defense;
    }

    public Armor(String name) {
        super(name);
        this.defense = randomNumberInRange(2, 5);
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
