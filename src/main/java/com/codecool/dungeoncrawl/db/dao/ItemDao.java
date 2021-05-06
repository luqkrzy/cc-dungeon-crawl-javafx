package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public interface ItemDao {

    void add(int playerId, Item item);

    void addAll(int playerId, List<Item> items);

    void update(int playerId, List<Item> items);

    List<Item> getAll(int playerId);

}
