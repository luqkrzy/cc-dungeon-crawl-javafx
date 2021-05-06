package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.ItemDao;
import com.codecool.dungeoncrawl.logic.items.Item;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemDaoJdbc extends DaoJdbc implements ItemDao, ItemType {

    public ItemDaoJdbc(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addAll(int playerId, List<Item> items) {
        items.forEach(item -> add(playerId, item));

    }

    @Override
    public void add(int playerId, Item item) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "INSERT INTO items (player_id, item_type, x, y, value) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            statement.setInt(2, item.getItemType());
            statement.setInt(3, item.getX());
            statement.setInt(4, item.getY());
            statement.setDouble(5, getItemValue(item));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "DELETE FROM items WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int playerId, List<Item> items) {
        delete(playerId);
        addAll(playerId, items);
    }

    @Override
    public List<Item> getAll(int playerId) {
        return null;
    }
}


