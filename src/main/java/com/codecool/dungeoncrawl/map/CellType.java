package com.codecool.dungeoncrawl.map;

public enum CellType {
    EMPTY("empty", false),
    FLOOR("floor", true),
    WALL("wall", false);

    private final String tileName;
    private final boolean isPassable;

    CellType(String tileName, boolean isPassable) {
        this.tileName = tileName;
        this.isPassable = isPassable;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean isPassable() {
        return isPassable;
    }
}
