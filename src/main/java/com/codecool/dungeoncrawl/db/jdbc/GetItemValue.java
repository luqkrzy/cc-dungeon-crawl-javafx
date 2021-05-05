package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HP;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;

public interface GetItemValue {

    default int getValue(Item item) {
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
}
