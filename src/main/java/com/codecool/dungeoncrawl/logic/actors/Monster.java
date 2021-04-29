package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.map.Cell;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Actor {
    private final Random random = new Random();

    public Monster(Cell cell) {
        super(cell);
    }

    public void initMove() {
        int dx = 0;
        int dy = 0;
        boolean b = random.nextBoolean();
        Player player = isPlayerNearby();
        if (player != null) {
            attack(player);
        } else {
            move(b ? random() : dx, b ? dy : random());
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

    private int random() {
        int max = 1;
        int min = -1;
        return random.nextInt(max - min + 1) + min;
    }
}
