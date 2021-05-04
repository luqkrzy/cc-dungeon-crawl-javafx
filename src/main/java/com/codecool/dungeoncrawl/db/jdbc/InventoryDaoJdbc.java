package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.InventoryDao;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HP;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {
    private DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addAll(InventoryModel inventoryModel) {
        inventoryModel.getInventory().forEach(item -> add(inventoryModel.getPlayerId(), item));
    }

    @Override
    public void add(int playerId, Item item) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "INSERT INTO inventory (player_id, type, value) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            statement.setInt(2, item.getItemType());
            statement.setInt(3, getValue(item));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getValue(Item item) {
        if (item instanceof Armor) {
            return ((Armor) item).getDefense();
        }
        if (item instanceof HP) {
            return ((HP) item).getHealthPoints();
        }
        if (item instanceof Sword) {
            return ((Sword) item).getDamage();
        } else {
            return 0;
        }
    }

    public void delete(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "DELETE FROM inventory WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InventoryModel inventoryModel) {
        delete(inventoryModel.getPlayerId());
        addAll(inventoryModel);
    }


    @Override
    public InventoryModel get(int id) {
        return null;
    }

    @Override
    public List<InventoryModel> getAll() {
        return null;
    }
}
