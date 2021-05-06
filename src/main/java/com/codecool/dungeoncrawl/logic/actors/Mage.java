package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.model.ActorModel;

public class Mage extends Monster {

    public Mage(Cell cell) {
        super(cell);
        setHealth(60);
        setAttack(12);
        setDefense(3);
        this.moveMin = -13;
        this.moveMax = 13;
    }

    public Mage(ActorModel monster, GameMap gameMap) {
        super(monster, gameMap);
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
