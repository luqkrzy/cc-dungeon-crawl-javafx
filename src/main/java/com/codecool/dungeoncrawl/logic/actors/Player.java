package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.engine.FightEngine;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.CellType;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.model.ActorModel;

public class Player extends Actor {

    public Player(Cell cell, String name) {
        super(cell);
        this.name = name;
        setHealth(100);
        setDefense(5);
        setAttack(5);
    }

    public Player(ActorModel actorModel, GameMap gameMap) {
        this.name = actorModel.getActorName();
        this.cell = new Cell(gameMap, actorModel.getX(), actorModel.getY(), CellType.FLOOR);
        this.health = actorModel.getX();
        this.defense = actorModel.getDefense();
        this.attack = actorModel.getAttack();
        this.inventory = actorModel.getInventory();
        this.cell.setActor(this);
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public void addToInventory(Cell cell) {
        Item item = cell.getItem();
        BottomGridPane.log("picked up: " + item.getName());
        if (item instanceof Sword) {
            setAttack(getAttack() + ((Sword) item).getDamage());
        }
        if (item instanceof Armor) {
            setDefense(getDefense() + ((Armor) item).getDefense());
        }
        if (item instanceof Key) {
            GameMap map = cell.getGameMap();
            map.getCell(((Key) item).getDoorX(), ((Key) item).getDoorY()).setType(CellType.OPENDOORS);
        }
        if (item instanceof HP) {
            setHealth(getHealth() + ((HP) item).getHealthPoints());
        } else {
            addItem(item);
        }
        cell.setItem(null);
    }

}
