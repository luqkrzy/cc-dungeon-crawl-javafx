package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.map.Cell;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        setHealth(25);
        setAttack(10);
        setDefense(0);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

