package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

public class Ghost extends Monster {


    public Ghost(Cell cell) {
        super(cell);
        setHealth(80);
        setAttack(7);
        setDefense(3);
    }


    @Override
    public void move(int dx, int dy) {
        if (health <= 0) {
            die();
        } else {
            Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
