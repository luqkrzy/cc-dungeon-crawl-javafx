package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.model.ItemModel;

interface ItemType {

    default Item initItem(int type, double value) {
        Item item;
        switch (type) {
            case 1 -> item = new Key(value);
            case 2 -> item = new Sword((int) value);
            case 3 -> item = new HP((int) value);
            case 4 -> item = new Armor((int) value);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return item;
    }
}
