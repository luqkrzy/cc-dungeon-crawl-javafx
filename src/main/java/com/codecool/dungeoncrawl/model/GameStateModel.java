package com.codecool.dungeoncrawl.model;

import java.sql.Timestamp;

public class GameStateModel extends BaseModel {
    private String playerName;
    private int playerId;
    private String savedAt;
    private String currentMap;
    private String saveName;
    private String mapString;

    public GameStateModel(int playerId, String currentMap, String saveName, String mapString) {
        this.playerId = playerId;
        this.currentMap = currentMap;
        this.saveName = saveName;
        this.mapString = mapString;
    }

    public GameStateModel(int playerId, Timestamp savedAt, String currentMap, String saveName, String playerName, String mapString) {
        this.playerId = playerId;
        this.currentMap = currentMap;
        this.saveName = saveName;
        this.savedAt = formatDate(savedAt);
        this.playerName = playerName;
        this.mapString = mapString;
    }

    private String formatDate(Timestamp savedAt) {
        return savedAt.toString().split("\\.")[0];
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getSavedAt() {
        return savedAt;
    }


    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public String getSaveName() {
        return saveName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMapString() {
        return mapString;
    }
}

