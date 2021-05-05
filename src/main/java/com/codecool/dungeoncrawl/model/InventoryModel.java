package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel extends BaseModel {
    private int playerId;
    private List<Item> inventory;


    public InventoryModel(int playerId, List<Item> inventory) {
        this.playerId = playerId;
        this.inventory = inventory;
    }

    public InventoryModel(int playerId) {
        this.playerId = playerId;
        this.inventory = new ArrayList<>();
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public int getPlayerId() {
        return playerId;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
