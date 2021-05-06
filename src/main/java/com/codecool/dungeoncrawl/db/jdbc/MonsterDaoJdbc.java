package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.MonsterDao;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.ActorModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonsterDaoJdbc extends DaoJdbc implements MonsterDao, ItemType {

    public MonsterDaoJdbc(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void add(int playerId, ActorModel monster) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "INSERT INTO monster (type, player_id, hp, x, y, defense, attack, item_type, item_value) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, monster.getType());
            statement.setInt(2, playerId);
            statement.setInt(3, monster.getHp());
            statement.setInt(4, monster.getX());
            statement.setInt(5, monster.getY());
            statement.setInt(6, monster.getDefense());
            statement.setInt(7, monster.getAttack());
            statement.setInt(8, monster.getFirstItem().getItemType());
            statement.setDouble(9, getItemValue(monster.getFirstItem()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAll(int playerId, List<Monster> monsters) {
        monsters.forEach(monster -> add(playerId, new ActorModel(monster)));
    }

    @Override
    public void update(ActorModel monster) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "UPDATE player SET player_name=?, hp=?, x=?, y=?, defense=?, attack=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, monster.getActorName());
            statement.setInt(2, monster.getHp());
            statement.setInt(3, monster.getX());
            statement.setInt(4, monster.getY());
            statement.setInt(5, monster.getDefense());
            statement.setInt(6, monster.getAttack());
            statement.setInt(7, monster.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ActorModel get(int id) {
        return null;
    }

    @Override
    public List<ActorModel> getAll(int playerId) {
        List<ActorModel> monsters = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            final String sql = "SELECT * FROM monster WHERE player_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ActorModel monsterModel = getMonsterModel(rs);
                monsters.add(monsterModel);
            }
            return monsters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ActorModel getMonsterModel(ResultSet rs) {
        ActorModel monsterModel = null;
        try {
            monsterModel = new ActorModel(
                    null,
                    rs.getString("type"),
                    rs.getInt("x"),
                    rs.getInt("y"),
                    rs.getInt("defense"),
                    rs.getInt("attack"),
                    rs.getInt("hp"));
            int itemType = rs.getInt("item_type");
            double itemValue = rs.getDouble("item_value");
            Item item = getItem(itemType, itemValue);
            monsterModel.addToInventory(item);
            monsterModel.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monsterModel;
    }

}
