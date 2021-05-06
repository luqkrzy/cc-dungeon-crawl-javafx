package com.codecool.dungeoncrawl.model;

import java.util.List;

public class GameSaveModel {
    private final GameStateModel gameStateModel;
    private final ActorModel playerModel;
    private final List<ActorModel> monsters;
    private final InventoryModel inventoryModel;


    public GameSaveModel(GameStateModel gameStateModel, ActorModel playerModel, List<ActorModel> monsters, InventoryModel inventoryModel) {
        this.gameStateModel = gameStateModel;
        this.playerModel = playerModel;
        this.monsters = monsters;
        this.inventoryModel = inventoryModel;
    }

    public GameStateModel getGameStateModel() {
        return gameStateModel;
    }


    public ActorModel getPlayerModel() {
        return playerModel;
    }

    public String getMapString() {
        return gameStateModel.getMapString();
    }

    public int getGameId() {
        return gameStateModel.getId();
    }

    public String getPlayerName() {
        return playerModel.getActorName();
    }

    public String getSaveName() {
        return gameStateModel.getSaveName();
    }

    public String getCurrentMap() {
        return gameStateModel.getCurrentMap();
    }

    public String getSavedAt() {
        return gameStateModel.getSavedAt();
    }


    public List<ActorModel> getMonsters() {
        return monsters;
    }

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    @Override
    public String toString() {
        return "GameSaveModel{" +
                "gameStateModel=" + gameStateModel.toString() +
                ", playerModel=" + playerModel.toString() +
                ", monsters=" + monsters.toString() +
                ", inventoryModel=" + inventoryModel.toString() +
                '}';
    }
}


