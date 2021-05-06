package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.model.ActorModel;

public class Ghost extends Monster {
    public Ghost(Cell cell) {
        super(cell);
        setHealth(40);
        setAttack(10);
        setDefense(2);
    }

    public Ghost(ActorModel monster, GameMap gameMap) {
        super(monster, gameMap);
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
