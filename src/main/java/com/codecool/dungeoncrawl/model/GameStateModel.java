package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameStateModel extends BaseModel {
    private int playerId;
    private Date savedAt;
    private String currentMap;
    private String saveName;

    public GameStateModel(int playerId, Date savedAt, String currentMap, String saveName) {
        this.playerId = playerId;
        this.currentMap = currentMap;
        this.saveName = saveName;
    }

    public GameStateModel(int playerId, String currentMap, String saveName) {
        this.playerId = playerId;
        this.currentMap = currentMap;
        this.saveName = saveName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
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


}
