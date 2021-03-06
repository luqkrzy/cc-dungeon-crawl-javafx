package com.codecool.dungeoncrawl.map;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;
    private String mapName;
    private Cell[][] cells;
    private Player player;
    private List<Monster> monsters;
    private List<Item> items;

    public GameMap(String mapName, int width, int height, CellType defaultCellType) {
        this.mapName = mapName;
        this.width = width;
        this.height = height;
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
        initCells(width, height, defaultCellType);
    }

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
        initCells(width, height, defaultCellType);
    }

    private void initCells(int width, int height, CellType defaultCellType) {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public void refresh(GraphicsContext context) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Cell cell = getCell(x, y);
                if (cell.isActor()) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.isItem()) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void initItems() {
        int monstersNo = monsters.size();
        int hpNo = monstersNo - 4;
        List<Item> monsterItems = new ArrayList<>();
        for (int i = 0; i < hpNo; i++) {
            monsterItems.add(new HP("Potion"));
        }
        monsterItems.add(new Sword("Saber"));
        monsterItems.add(new Armor("Metal Armor"));
        monsterItems.add(new Key("Door key", 17, 3));
        monsterItems.add(new Key("Door Key", 22, 18));

        for (int i = 0; i < monsterItems.size(); i++) {
            monsters.get(i).addItem(monsterItems.get(i));
        }
    }

    public String getMapName() {
        return mapName;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

}
