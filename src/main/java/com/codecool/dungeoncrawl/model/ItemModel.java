package com.codecool.dungeoncrawl.model;

public class ItemModel extends BaseModel {
    private int playerId;
    private int itemType;
    private int x;
    private int y;
    private int value;

    public ItemModel(int playerId, int itemType, int x, int y, int value) {
        this.playerId = playerId;
        this.itemType = itemType;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getItemType() {
        return itemType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
