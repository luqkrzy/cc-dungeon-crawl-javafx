package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.map.Cell;

import java.util.List;
import java.util.Random;

public class Skeleton extends Monster {
    Random random = new Random();

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(30);
        setAttack(3);
        setDefense(0);
    }

    @Override
    public void initMove() {
        int dx = random();
        int dy = random();
        // Cell nextCell = cell.getNeighbor(dx, dy);
        Player player = isPlayerNearby();
        if (player != null) {
            attack(player);
        } else {
            move(dx, dy);
        }
    }

    private int random() {
        int max = 1;
        int min = -1;
        return random.nextInt(max - min + 1) + min;
    }


    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

