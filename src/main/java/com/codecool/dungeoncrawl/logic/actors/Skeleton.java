package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        setHealth(30);
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

