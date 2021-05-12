package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.InventoryModel;

import java.util.List;

public interface InventoryDao {

    void add(int playerId, Item item);

    void addAll(InventoryModel inventoryModel);

    void update(InventoryModel inventoryModel);

    InventoryModel get(int id);
}
