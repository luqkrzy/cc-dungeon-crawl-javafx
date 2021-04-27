package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

public class Ghost extends Monster {


    public Ghost(Cell cell) {
        super(cell);
        setHealth(80);
        ;
    }

    @Override
    public void initMove() {
        move(1, 1);

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
