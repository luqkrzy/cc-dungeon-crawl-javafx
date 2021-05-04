package com.codecool.dungeoncrawl.db;

import com.codecool.dungeoncrawl.db.dao.GameStateDao;
import com.codecool.dungeoncrawl.db.dao.InventoryDao;
import com.codecool.dungeoncrawl.db.dao.PlayerDao;
import com.codecool.dungeoncrawl.db.jdbc.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.db.jdbc.InventoryDaoJdbc;
import com.codecool.dungeoncrawl.db.jdbc.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private InventoryDao inventoryDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
    }

    public void save(Player player, String saveName) {
        boolean ifSaveExist = isSaveExist(saveName);
        if (ifSaveExist) {
            update(player, saveName);
        } else {
            saveGame(player, saveName);
        }
    }

    private void update(Player player, String saveName) {
        PlayerModel playerModel = new PlayerModel(player);
        playerModel.setId(gameStateDao.getPlayerIdBySaveName(saveName));
        playerDao.update(playerModel);
        GameStateModel gameStateModel = new GameStateModel(playerModel, saveName);
        gameStateDao.update(gameStateModel);
        inventoryDao.update(new InventoryModel(playerModel));
    }

    private void saveGame(Player player, String saveName) {
        PlayerModel playerModel = savePlayer(player);
        saveGameState(saveName, playerModel);
        savePlayerInventory(playerModel);

    }

    private void savePlayerInventory(PlayerModel playerModel) {
        InventoryModel inventoryModel = new InventoryModel(playerModel);
        inventoryDao.addAll(inventoryModel);
    }

    private void saveGameState(String saveName, PlayerModel playerModel) {
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
