package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.Drawable;

import java.util.Random;

public abstract class Item implements Drawable {
    private Cell cell;
    private String name;
    private final Random random;

    public Item(String name) {
        this.random = new Random();
        this.name = name;
    }

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.name = name;
        this.cell.setItem(this);
        this.random = new Random();
    }

    public int randomNumberInRange(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public String getName() {
        return name;
    }


}
