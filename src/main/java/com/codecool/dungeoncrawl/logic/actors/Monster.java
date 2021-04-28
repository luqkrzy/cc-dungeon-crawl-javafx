package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.map.Cell;

import java.util.List;

public abstract class Monster extends Actor {
    public Monster(Cell cell) {
        super(cell);
    }

    public void initMove() {
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


}
