package com.codecool.dungeoncrawl.db.jdbc;

import com.codecool.dungeoncrawl.db.dao.GameStateDao;
import com.codecool.dungeoncrawl.model.GameStateModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(GameStateModel state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, player_id, save_name) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setInt(2, state.getPlayerId());
            statement.setString(3, state.getSaveName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameStateModel state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET current_map=? WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            statement.setInt(2, state.getPlayerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameStateModel get(String saveName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT FROM game_state WHERE save_name=?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, saveName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public boolean isSaveNameExist(String saveName) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "SELECT * FROM game_state WHERE save_name=?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, saveName);
            ResultSet resultSet = statement.executeQuery();
            String save_name = "";
            while (resultSet.next()) {
                save_name = resultSet.getString("save_name");
            }
            return save_name.equals(saveName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getPlayerIdBySaveName(String saveName) {
        try (Connection conn = dataSource.getConnection()) {
            final String sql = "SELECT player_id FROM game_state WHERE save_name=?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, saveName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("player_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public GameStateModel get(int id) {
        return null;

    }

    @Override
    public List<GameStateModel> getAll() {
        return null;
    }
}
