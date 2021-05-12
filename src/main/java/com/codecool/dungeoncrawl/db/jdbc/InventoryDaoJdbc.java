package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.InventoryDao;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class InventoryDaoJdbc extends DaoJdbc implements InventoryDao, ItemType {

    public InventoryDaoJdbc(DataSource dataSource) {
        super(dataSource);
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
            statement.setDouble(3, item.getValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(int playerId) {
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
        try (Connection connection = dataSource.getConnection()) {
            final String sql = "SELECT * FROM inventory WHERE player_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            InventoryModel inventoryModel = new InventoryModel(id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int type = rs.getInt("type");
                double value = rs.getDouble("value");
                Item item = initItem(type, value);
                inventoryModel.addToInventory(item);
            }
            return inventoryModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
