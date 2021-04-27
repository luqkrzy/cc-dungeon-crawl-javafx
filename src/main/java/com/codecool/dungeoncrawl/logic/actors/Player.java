package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        setHealth(100);
        setDefense(5);
        setAttack(10);
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public String getTileName() {
        return "player";
    }
}
