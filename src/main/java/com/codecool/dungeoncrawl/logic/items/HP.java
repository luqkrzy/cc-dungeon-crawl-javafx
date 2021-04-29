package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;

public class HP extends Item {
    private final int healthPoints;

    public HP(Cell cell, String name) {
        super(cell, name);
        this.healthPoints = randomNumberInRange(5, 10);
    }

    public HP(String name) {
        super(name);
        this.healthPoints = randomNumberInRange(5, 10);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "hp";
    }
}
