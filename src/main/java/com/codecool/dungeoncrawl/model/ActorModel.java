package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public class ActorModel extends BaseModel {
    private String currentMap;
    private String playerName;
    private String type;
    private int hp;
    private int x;
    private int y;
    private int defense;
    private int attack;
    private List<Item> inventory;


    public ActorModel(Actor actor) {
        this.currentMap = actor.getCell().getGameMap().getMapName();
        this.playerName = actor.getName();
        this.type = actor.getClassName();
        this.x = actor.getX();
        this.y = actor.getY();
        this.defense = actor.getDefense();
        this.attack = actor.getAttack();
        this.hp = actor.getHealth();
        this.inventory = actor.getInventory();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String actorName) {
        this.playerName = actorName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Item getFirstItem() {
        return inventory.get(0);
    }

    public String getType() {
        return type;
    }
}
