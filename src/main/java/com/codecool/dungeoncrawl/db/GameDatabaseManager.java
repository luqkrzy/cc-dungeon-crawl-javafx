package com.codecool.dungeoncrawl.db;

import com.codecool.dungeoncrawl.db.dao.GameStateDao;
import com.codecool.dungeoncrawl.db.dao.PlayerDao;
import com.codecool.dungeoncrawl.db.jdbc.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.db.jdbc.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void save(Player player, String saveName) {
        boolean ifSaveExist = isSaveExist(saveName);
        if (ifSaveExist) {
            update(player, saveName);
        } else {
            saveGameState(player, saveName);
        }
    }

    private void update(Player player, String saveName) {
        PlayerModel playerModel = new PlayerModel(player);
        playerModel.setId(gameStateDao.getPlayerIdBySaveName(saveName));
        playerDao.update(playerModel);
        GameStateModel gameStateModel = new GameStateModel(playerModel, saveName);
        gameStateDao.update(gameStateModel);


    }

    private void saveGameState(Player player, String saveName) {
        PlayerModel playerModel = savePlayer(player);
        saveState(saveName, playerModel);
    }

    private void saveState(String saveName, PlayerModel playerModel) {
        GameStateModel gameStateModel = new GameStateModel(playerModel, saveName);
        gameStateDao.add(gameStateModel);
        System.out.println("GameState id: " + gameStateModel.getId());
    }

    public PlayerModel savePlayer(Player player) {
        PlayerModel playerModel = new PlayerModel(player);
        playerDao.add(playerModel);
        System.out.println("Player id: " + playerModel.getId());
        return playerModel;
    }

    private boolean isSaveExist(String saveName) {
        final boolean saveNameExist = gameStateDao.isSaveNameExist(saveName);
        System.out.println(saveNameExist ? "Save exist - overwriting" : "New Save");
        return saveNameExist;
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USER");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
