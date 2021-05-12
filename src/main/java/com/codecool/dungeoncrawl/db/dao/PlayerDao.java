package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.model.ActorModel;

import java.util.List;

public interface PlayerDao {
    void add(ActorModel player);

    void update(ActorModel player);

    ActorModel get(int id);
}
