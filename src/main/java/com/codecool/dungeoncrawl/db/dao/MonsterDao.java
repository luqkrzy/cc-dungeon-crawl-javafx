package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.model.ActorModel;

import java.util.List;

public interface MonsterDao {
    void add(int playerId, ActorModel monster);

    void update(ActorModel monster);

    ActorModel get(int id);

    List<ActorModel> getAll();

    void addAll(int playerId, List<Monster> monsters);
}
