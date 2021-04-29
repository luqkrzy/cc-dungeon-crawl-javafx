package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;

public class Key extends Item {
    private final int doorX;
    private final int doorY;


    public Key(Cell cell, String name, int x, int y) {
        super(cell, name);
        this.doorX = x;
        this.doorY = y;
    }

    public int getDoorX() {
        return doorX;
    }

    public int getDoorY() {
        return doorY;
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
