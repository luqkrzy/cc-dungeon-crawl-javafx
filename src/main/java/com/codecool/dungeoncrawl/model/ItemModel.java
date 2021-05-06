package com.codecool.dungeoncrawl.model;

public class ItemModel extends BaseModel {
    private int playerId;
    private int itemType;
    private int x;
    private int y;
    private double value;

    public ItemModel(int x, int y, int itemType, double value) {
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

    public double getValue() {
        return value;
    }
}
