package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.InventoryModel;

import java.util.List;

public interface ItemDao {

    void addAll(int playerId);

    void update(int playerId);

    List<Item> getAll(int playerId);

}
