package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;

public class Mage extends Monster {

    public Mage(Cell cell) {
        super(cell);
        setHealth(60);
        setAttack(12);
        setDefense(3);
        this.moveMin = -13;
        this.moveMax = 13;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!nextCell.getType().equals(CellType.EMPTY) && !nextCell.getType().equals(CellType.WALL)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public String getTileName() {
        return "mage";
    }
}
