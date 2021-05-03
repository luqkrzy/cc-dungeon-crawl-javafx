package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.model.GameStateModel;

import java.util.List;

public interface GameStateDao {
    void add(GameStateModel state);

    void update(GameStateModel state);

    GameStateModel get(int id);

    GameStateModel get(String saveName);

    List<GameStateModel> getAll();

    boolean isSaveNameExist(String saveName);

    int getPlayerIdBySaveName(String saveName);
}
