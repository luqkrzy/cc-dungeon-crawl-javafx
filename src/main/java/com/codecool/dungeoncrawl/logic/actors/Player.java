package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.map.Cell;
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

    public Player(Cell cell) {
        super(cell);
        setHealth(100);
        setDefense(5);
        setAttack(5);
    }

    public Player(ActorModel actorModel, GameMap gameMap) {
        super(actorModel, gameMap);
        gameMap.setPlayer(this);
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public void addToInventory(Item item) {
        getMap().removeItem(item);
        BottomGridPane.log("picked up: " + item.getName());
        item.modifyPlayerSkills(this);
        cell.setItem(null);
    }

}
