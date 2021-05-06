package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.List;

public interface ItemDao {

    void add(int playerId, Item item);

    void addAll(int playerId, List<Item> items);

    void update(int playerId, List<Item> items);

    List<ItemModel> getAll(int playerId);

}
