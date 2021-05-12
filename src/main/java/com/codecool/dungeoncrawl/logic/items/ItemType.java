package com.codecool.dungeoncrawl.logic.items;

enum ItemType {
    KEY(1), SWORD(2), HP(3), ARMOR(4);

    private final int type;

    ItemType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
