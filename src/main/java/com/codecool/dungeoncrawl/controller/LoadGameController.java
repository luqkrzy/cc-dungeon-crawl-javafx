package com.codecool.dungeoncrawl.controller;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.model.ActorModel;
import com.codecool.dungeoncrawl.model.GameSaveModel;
import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class LoadGameController {

    private final GameController gameController;

    public LoadGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void loadGame(GameSaveModel gameSave) {
        gameController.build(gameSave);
        Player player = new Player(gameSave.getPlayerModel(), gameController.getMap());
        List<ActorModel> monstersModel = gameSave.getMonsters();
        List<Monster> monsters = new ArrayList<>();
        monstersModel.forEach(monster -> monsters.add(loadMonsters(monster, gameController.getMap())));
        List<ItemModel> mapItemsModel = gameSave.getMapItems();
        List<Item> mapItems = new ArrayList<>();
        mapItemsModel.forEach(itemModel -> mapItems.add(loadItems(gameController.getMap(), itemModel)));
        mapItems.forEach(item -> gameController.getMap().getCell(item.getX(), item.getY()).setItem(item));
        gameController.getMap().setMonsters(monsters);
        gameController.run();
    }

    private Item loadItems(GameMap map, ItemModel itemModel) {
        return initItem(map, itemModel.getX(), itemModel.getY(), itemModel.getItemType(), itemModel.getValue());
    }

    private Item initItem(GameMap gameMap, int x, int y, int type, double value) {
        Item item = null;
        switch (type) {
            case 1 -> item = new Key(new Cell(gameMap, x, y, CellType.FLOOR), value);
            case 2 -> item = new Sword(new Cell(gameMap, x, y, CellType.FLOOR), (int) value);
            case 3 -> item = new HP(new Cell(gameMap, x, y, CellType.FLOOR), (int) value);
            case 4 -> item = new Armor(new Cell(gameMap, x, y, CellType.FLOOR), (int) value);
        }
        return item;
    }

    private Monster loadMonsters(ActorModel monster, GameMap gameMap) {
        Monster mon = null;
        switch (monster.getType()) {
            case "Skeleton" -> mon = new Skeleton(monster, gameMap);
            case "Ghost" -> mon = new Ghost(monster, gameMap);
            case "Mage" -> mon = new Mage(monster, gameMap);
        }
        return mon;
    }

}
