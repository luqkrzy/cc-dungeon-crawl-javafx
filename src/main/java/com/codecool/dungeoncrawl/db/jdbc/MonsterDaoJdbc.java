package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.MonsterDao;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.model.ActorModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MonsterDaoJdbc implements MonsterDao, GetItemValue {
    private DataSource dataSource;

    public MonsterDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
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
            statement.setInt(9, getValue(monster.getFirstItem()));
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
        return null;
    }


}
