package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;

public class HP extends Item {
    private final int healthPoints;

    public HP(Cell cell, String name) {
        super(cell, name);
        this.healthPoints = randomNumberInRange(7, 13);
        this.itemType = ItemType.HP;

    }

    public HP(String name) {
        super(name);
        this.healthPoints = randomNumberInRange(7, 13);
        this.itemType = ItemType.HP;
    }

    public HP(Cell cell, int healthPoints) {
        super(cell);
        this.healthPoints = healthPoints;
    }

    public HP(int healthPoints) {
        this.healthPoints = healthPoints;
        this.itemType = ItemType.HP;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "hp";
    }

    @Override
    public double getValue() {
        return healthPoints;
    }

    @Override
    public void modifyPlayerSkills(Player player) {
        player.setHealth(player.getHealth() + healthPoints);
    }
}
