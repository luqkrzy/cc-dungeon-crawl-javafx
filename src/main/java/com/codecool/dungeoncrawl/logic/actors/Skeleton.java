package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.map.Cell;

import java.util.Random;

public class Skeleton extends Monster {
    Random random = new Random();

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(30);
        setAttack(10);
        setDefense(0);
    }

    public Skeleton(Cell cell, Item item) {
        super(cell, item);
        setHealth(30);
        setAttack(10);
        setDefense(0);
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }
}

