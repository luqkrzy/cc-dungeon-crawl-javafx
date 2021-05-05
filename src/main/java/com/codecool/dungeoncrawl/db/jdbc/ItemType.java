package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.logic.items.*;

public interface ItemType {

    default int getItemValue(Item item) {
        if (item instanceof Armor) {
            return ((Armor) item).getDefense();
        }
        if (item instanceof HP) {
            return ((HP) item).getHealthPoints();
        }
        if (item instanceof Sword) {
            return ((Sword) item).getDamage();
        } else {
            return 0;
        }
    }

    default Item getItem(int type, int value) {
        Item item;
        switch (type) {
            case 1 -> item = new Key();
            case 2 -> item = new Sword(value);
            case 3 -> item = new HP(value);
            case 4 -> item = new Armor(value);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return item;
    }
}
