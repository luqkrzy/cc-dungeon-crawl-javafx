package com.codecool.dungeoncrawl.model;

import java.sql.Date;
import java.sql.Timestamp;

public class GameStateModel extends BaseModel {
    private String playerName;
    private int playerId;
    private String savedAt;
    private String currentMap;
    private String saveName;

    public GameStateModel(int playerId, Timestamp savedAt, String currentMap, String saveName, String playerName) {
        this.playerId = playerId;
        this.currentMap = currentMap;
        this.saveName = saveName;
        this.savedAt = formatDate(savedAt);
        this.playerName = playerName;
    }

    private String formatDate(Timestamp savedAt) {
        return savedAt.toString().split("\\.")[0];

    }

    public GameStateModel(int playerId, String currentMap, String saveName) {
        this.playerId = playerId;
        this.currentMap = currentMap;
        this.saveName = saveName;
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
}

