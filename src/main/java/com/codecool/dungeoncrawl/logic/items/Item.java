package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.Drawable;

import java.lang.reflect.Field;
import java.util.Random;

public abstract class Item implements Drawable {
    private Cell cell;
    private String name;
    protected ItemType itemType;
    private Random random;

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

    public Item() {
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

    public int getItemType() {
        return itemType.getType();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException ignored) {

            }
        }
        return sb.toString();
    }
}
