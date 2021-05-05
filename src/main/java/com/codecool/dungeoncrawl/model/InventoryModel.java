package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public class InventoryModel extends BaseModel {
    private int playerId;
    private List<Item> inventory;

    public InventoryModel(ActorModel actorModel) {
        this.playerId = actorModel.getId();
        this.inventory = actorModel.getInventory();
    }

    public int getPlayerId() {
        return playerId;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
