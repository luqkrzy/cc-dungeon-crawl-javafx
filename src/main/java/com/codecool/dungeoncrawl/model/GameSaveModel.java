package com.codecool.dungeoncrawl.model;

import java.sql.Date;
import java.util.List;

public class GameSaveModel {
    private final GameStateModel gameStateModel;
    private final ActorModel player;
    private final List<ActorModel> monsters;
    private final InventoryModel inventoryModel;


    public GameSaveModel(GameStateModel gameStateModel, ActorModel player, List<ActorModel> monsters, InventoryModel inventoryModel) {
        this.gameStateModel = gameStateModel;
        this.player = player;
        this.monsters = monsters;
        this.inventoryModel = inventoryModel;
    }

    public GameStateModel getGameStateModel() {
        return gameStateModel;
    }

    public String getPlayerName() {
        return player.getActorName();
    }

    public ActorModel getPlayer() {
        return player;
    }

    public String getSaveName() {
        return gameStateModel.getSaveName();
    }

    public String getMapName() {
        return gameStateModel.getCurrentMap();
    }

    public Date getTime() {
        return gameStateModel.getSavedAt();
    }


    public List<ActorModel> getMonsters() {
        return monsters;
    }

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }
}


