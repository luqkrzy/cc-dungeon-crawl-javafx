package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;

public class Sword extends Item {
    private int damage;

    public Sword(Cell cell, String name, int damage) {
        super(cell, name);
        this.damage = damage;
        this.itemType = ItemType.SWORD;
    }

    public Sword(String name) {
        super(name);
        this.damage = randomNumberInRange(3, 5);
        this.itemType = ItemType.SWORD;
    }

    public Sword(int damage) {
        this.damage = damage;
        this.itemType = ItemType.SWORD;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
