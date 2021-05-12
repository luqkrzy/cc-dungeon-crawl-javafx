package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;

public class Armor extends Item {
    private int defense;

    public Armor(Cell cell, String name) {
        super(cell, name);
        this.defense = randomNumberInRange(2, 5);
        this.itemType = ItemType.ARMOR;
    }

    public Armor(String name) {
        super(name);
        this.defense = randomNumberInRange(2, 5);
        this.itemType = ItemType.ARMOR;
    }

    public Armor(int defense) {
        this.defense = defense;
        this.itemType = ItemType.ARMOR;
    }

    public Armor(Cell cell, int defense) {
        super(cell);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "armor";
    }

    @Override
    public double getValue() {
        return defense;
    }

    @Override
    public void modifyPlayerSkills(Player player) {
        player.setDefense(player.getDefense() + defense);
        player.addItem(this);
    }
}


