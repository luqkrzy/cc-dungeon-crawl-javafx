package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.map.Cell;

import java.util.List;
import java.util.Random;

public abstract class Monster extends Actor {
    private final Random random = new Random();
    protected int moveMin = -1;
    protected int moveMax = 1;

    public Monster(Cell cell) {
        super(cell);
    }

    public Monster(Cell cell, Item item) {
        super(cell);
        addItem(item);
    }

    public void initMove() {
        if (health <= 0) {
            die();
        }
        int dx = 0;
        int dy = 0;
        boolean b = random.nextBoolean();
        Player player = isPlayerNearby();
        if (player != null) {
            attack(player);
        } else {
            move(b ? random(moveMax, moveMin) : dx, b ? dy : random(moveMax, moveMin));
        }
    }

    public Player isPlayerNearby() {
        List<Cell> neighbors = cell.getNeighbors();
        for (Cell neighbor : neighbors) {
            if (neighbor.isPlayer()) {
                return (Player) neighbor.getActor();
            }
        }
        return null;
    }

    public void attack(Player player) {
        BottomGridPane.log("attack!");
        fightEngine.fight2(player, this);
    }

    protected int random(int max, int min) {
        return random.nextInt(max - min + 1) + min;
    }
}
