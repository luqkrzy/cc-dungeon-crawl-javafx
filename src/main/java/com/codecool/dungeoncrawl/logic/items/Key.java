package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;

public class Key extends Item {


    public Key(Cell cell, String name) {
        super(cell, name);
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
