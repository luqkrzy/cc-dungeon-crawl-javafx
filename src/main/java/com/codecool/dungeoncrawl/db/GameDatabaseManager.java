package com.codecool.dungeoncrawl.db;

import com.codecool.dungeoncrawl.db.dao.GameStateDao;
import com.codecool.dungeoncrawl.db.dao.InventoryDao;
import com.codecool.dungeoncrawl.db.dao.MonsterDao;
import com.codecool.dungeoncrawl.db.dao.PlayerDao;
import com.codecool.dungeoncrawl.db.jdbc.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.db.jdbc.InventoryDaoJdbc;
import com.codecool.dungeoncrawl.db.jdbc.MonsterDaoJdbc;
import com.codecool.dungeoncrawl.db.jdbc.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameSaveModel;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ActorModel;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private InventoryDao inventoryDao;
    private MonsterDao monsterDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
        monsterDao = new MonsterDaoJdbc(dataSource);
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
        ActorModel actorModel = new ActorModel(player);
        actorModel.setId(gameStateDao.getPlayerIdBySaveName(saveName));
        playerDao.update(actorModel);
        GameStateModel gameStateModel = new GameStateModel(actorModel.getId(), player.getCell().getGameMap().getMapName(), saveName);
        gameStateDao.update(gameStateModel);
        inventoryDao.update(new InventoryModel(actorModel.getId(), actorModel.getInventory()));
    }

    private void saveGame(Player player, String saveName) {
        ActorModel playerModel = savePlayer(player);
        saveGameState(playerModel.getId(), saveName, player.getCell().getGameMap().getMapName());
        savePlayerInventory(playerModel);
        saveMonsters(playerModel.getId(), player.getCell().getGameMap().getMonsters());

    }

    private void saveMonsters(int playerId, List<Monster> monsters) {
        monsterDao.addAll(playerId, monsters);
    }

    private void savePlayerInventory(ActorModel actorModel) {
        InventoryModel inventoryModel = new InventoryModel(actorModel.getId(), actorModel.getInventory());
        inventoryDao.addAll(inventoryModel);
    }

    private void saveGameState(int playerId, String saveName, String currentMap) {
        GameStateModel gameStateModel = new GameStateModel(playerId, currentMap, saveName);
        gameStateDao.add(gameStateModel);
        System.out.println("GameState id: " + gameStateModel.getId());
    }

    public ActorModel savePlayer(Player player) {
        ActorModel actorModel = new ActorModel(player);
        playerDao.add(actorModel);
        System.out.println("Player id: " + actorModel.getId());
        return actorModel;
    }

    private boolean isSaveExist(String saveName) {
        final boolean saveNameExist = gameStateDao.isSaveNameExist(saveName);
        System.out.println(saveNameExist ? "Save exist - overwriting" : "New Save");
        return saveNameExist;
    }


    public GameSaveModel loadGameSaveModel(GameStateModel gameStateModel) {
        ActorModel playerModel = playerDao.get(gameStateModel.getPlayerId());
        InventoryModel inventoryModel = inventoryDao.get(gameStateModel.getPlayerId());
        playerModel.setInventory(inventoryModel.getInventory());
        List<ActorModel> monsters = monsterDao.getAll(gameStateModel.getPlayerId());
        return new GameSaveModel(gameStateModel, playerModel, monsters, inventoryModel);
    }

    public List<GameSaveModel> getAllGameSaves() {
        List<GameStateModel> allStates = getAllGameStates();
        List<GameSaveModel> gameSaves = new ArrayList<>();
        allStates.forEach(state -> gameSaves.add(loadGameSaveModel(state)));
        return gameSaves;
    }


    public List<GameStateModel> getAllGameStates() {
        return gameStateDao.getAll();
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
