package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.model.ItemModel;

interface ItemType {

    // default double getItemValue(Item item) {
    //     if (item instanceof Armor) {
    //         return ((Armor) item).getDefense();
    //     }
    //     if (item instanceof HP) {
    //         return ((HP) item).getHealthPoints();
    //     }
    //     if (item instanceof Sword) {
    //         return ((Sword) item).getDamage();
    //     }
    //
    //     if (item instanceof Key) {
    //         int doorX = ((Key) item).getDoorX();
    //         int doorY = ((Key) item).getDoorY();
    //         String keyCoord = doorX + "." + doorY;
    //         return Double.parseDouble(keyCoord);
    //     }
    //
    //     return 0;
    // }

    default Item initItem(int type, double value) {
        Item item;
        switch (type) {
            case 1 -> {
                item = new Key(value);
            }
            case 2 -> item = new Sword((int) value);
            case 3 -> item = new HP((int) value);
            case 4 -> item = new Armor((int) value);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return item;
    }

    default ItemModel initItemModel(int x, int y, int type, double value) {
        return new ItemModel(x, y, type, value);

    }
}
