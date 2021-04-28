package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;

public class Armor extends Item {
    private final int defense;

    public Armor(Cell cell, String name, int defense) {
        super(cell, name);
        this.defense = defense;
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
