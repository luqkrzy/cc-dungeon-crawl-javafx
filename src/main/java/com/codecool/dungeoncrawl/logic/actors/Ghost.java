package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

public class Ghost extends Monster {
    public Ghost(Cell cell) {
        super(cell);
        setHealth(40);
        setAttack(10);
        setDefense(2);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
