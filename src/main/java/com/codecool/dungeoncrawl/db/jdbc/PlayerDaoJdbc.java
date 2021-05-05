package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.PlayerDao;
import com.codecool.dungeoncrawl.model.ActorModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ActorModel playerModel) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "INSERT INTO player (player_name, hp, x, y, defense, attack) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, playerModel.getActorName());
            statement.setInt(2, playerModel.getHp());
            statement.setInt(3, playerModel.getX());
            statement.setInt(4, playerModel.getY());
            statement.setInt(5, playerModel.getDefense());
            statement.setInt(6, playerModel.getAttack());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            playerModel.setId(id);
            // return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ActorModel playerModel) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "UPDATE player SET player_name=?, hp=?, x=?, y=?, defense=?, attack=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, playerModel.getActorName());
            statement.setInt(2, playerModel.getHp());
            statement.setInt(3, playerModel.getX());
            statement.setInt(4, playerModel.getY());
            statement.setInt(5, playerModel.getDefense());
            statement.setInt(6, playerModel.getAttack());
            statement.setInt(7, playerModel.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ActorModel get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            final String sql = "SELECT * FROM player WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return null;
            ActorModel playerModel = new ActorModel(
                    rs.getString("player_name"),
                    "Player",
                    rs.getInt("x"),
                    rs.getInt("y"),
                    rs.getInt("defense"),
                    rs.getInt("attack"),
                    rs.getInt("hp"));
            playerModel.setId(rs.getInt(1));
            System.out.println(playerModel.toString());
            return playerModel;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ActorModel> getAll() {
        return null;
    }
}
