package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.InventoryDao;
import com.codecool.dungeoncrawl.db.dao.ItemDao;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDaoJdbc extends DaoJdbc implements ItemDao, ItemType {

    public ItemDaoJdbc(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addAll(int playerId) {

    }

    @Override
    public void update(int playerId) {

    }

    @Override
    public List<Item> getAll(int playerId) {
        return null;
    }
}


