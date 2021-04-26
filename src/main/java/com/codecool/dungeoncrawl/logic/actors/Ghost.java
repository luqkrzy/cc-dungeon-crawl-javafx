package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

public class Ghost extends Actor {


    public Ghost(Cell cell) {
        super(cell);
        setHealth(80);;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
