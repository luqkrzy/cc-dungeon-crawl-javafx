package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.GameMap;

public class Key extends Item {
    private int doorX;
    private int doorY;


    public Key(Cell cell, String name, int x, int y) {
        super(cell, name);
        this.doorX = x;
        this.doorY = y;
        this.itemType = ItemType.KEY;

    }

    public Key(Cell cell, double doorXY) {
        super(cell);
        setDoorXY(doorXY);
        this.itemType = ItemType.KEY;

    }

    public Key(String name, int x, int y) {
        super(name);
        this.doorX = x;
        this.doorY = y;
        this.itemType = ItemType.KEY;

    }

    public Key(double doorXY) {
        setDoorXY(doorXY);
        this.itemType = ItemType.KEY;
    }


    private void setDoorXY(double doorXY) {
        String doubleAsString = String.valueOf(doorXY);
        String[] split = doubleAsString.split("\\.");
        doorX = Integer.parseInt(split[0]);
        doorY = Integer.parseInt(split[1]);
    }


    public int getDoorX() {
        return doorX;
    }

    public int getDoorY() {
        return doorY;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public String getTileName() {
        return "key";
    }

    @Override
    public double getValue() {
        String keyCoord = doorX + "." + doorY;
        return Double.parseDouble(keyCoord);
    }

    @Override
    public void modifyPlayerSkills(Player player) {
        GameMap map = player.getMap();
        map.getCell(doorX, doorY).setType(CellType.OPENDOORS);
        player.addItem(this);
    }
}
