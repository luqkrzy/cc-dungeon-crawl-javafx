package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameStateModel extends BaseModel {
    private Date savedAt;
    private String currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private ActorModel actorModel;
    private String saveName;

    public GameStateModel(ActorModel actorModel, String saveName) {
        this.currentMap = actorModel.getCurrentMap();
        this.actorModel = actorModel;
        this.saveName = saveName;

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

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public ActorModel getPlayerModel() {
        return actorModel;
    }

    public void setPlayerModel(ActorModel actorModel) {
        this.actorModel = actorModel;
    }

    public String getSaveName() {
        return saveName;
    }


}
