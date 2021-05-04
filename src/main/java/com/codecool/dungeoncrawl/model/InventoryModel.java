package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public class InventoryModel extends BaseModel {
    private int playerId;
    private List<Item> inventory;

    public InventoryModel(PlayerModel playerModel) {
        this.playerId = playerModel.getId();
        this.inventory = playerModel.getInventory();
    }

    public int getPlayerId() {
        return playerId;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
