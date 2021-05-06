package com.codecool.dungeoncrawl.db;

import com.codecool.dungeoncrawl.db.dao.*;
import com.codecool.dungeoncrawl.db.jdbc.*;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.*;
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
    private ItemDao itemDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
        monsterDao = new MonsterDaoJdbc(dataSource);
        itemDao = new ItemDaoJdbc(dataSource);
    }

    public void save(Player player, String saveName) {
        boolean ifSaveExist = doesSaveExist(saveName);
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
        GameStateModel gameStateModel = new GameStateModel(actorModel.getId(), player.getMap().getMapName(), saveName, player.getMap().layoutToString());
        gameStateDao.update(gameStateModel);
        inventoryDao.update(new InventoryModel(actorModel.getId(), actorModel.getInventory()));
        itemDao.update(actorModel.getId(), player.getMap().getItems());
    }

    private void saveGame(Player player, String saveName) {
        ActorModel playerModel = savePlayer(player);
        saveGameState(playerModel.getId(), saveName, player);
        savePlayerInventory(playerModel);
        saveMonsters(playerModel.getId(), player.getMap().getMonsters());
        saveMapItems(playerModel.getId(), player.getMap().getItems());
    }

    private void saveMapItems(int playerId, List<Item> items) {
        itemDao.addAll(playerId, items);
    }

    private void saveMonsters(int playerId, List<Monster> monsters) {
        monsterDao.addAll(playerId, monsters);
    }

    private void savePlayerInventory(ActorModel actorModel) {
        InventoryModel inventoryModel = new InventoryModel(actorModel.getId(), actorModel.getInventory());
        inventoryDao.addAll(inventoryModel);
    }

    private void saveGameState(int playerId, String saveName, Player player) {
        System.out.println(player.getMap().layoutToString());
        GameStateModel gameStateModel = new GameStateModel(playerId, player.getMap().getMapName(), saveName, player.getMap().layoutToString());
        gameStateDao.add(gameStateModel);
        System.out.println("GameState id: " + gameStateModel.getId());
    }

    public ActorModel savePlayer(Player player) {
        ActorModel actorModel = new ActorModel(player);
        playerDao.add(actorModel);
        System.out.println("Player id: " + actorModel.getId());
        return actorModel;
    }

    private boolean doesSaveExist(String saveName) {
        boolean saveNameExist = gameStateDao.isSaveNameExist(saveName);
        System.out.println(saveNameExist ? "Save exist - overwriting" : "New Save");
        return saveNameExist;
    }


    public GameSaveModel loadGameSaveModel(GameStateModel gameStateModel) {
        ActorModel playerModel = playerDao.get(gameStateModel.getPlayerId());
        InventoryModel inventoryModel = inventoryDao.get(gameStateModel.getPlayerId());
        playerModel.setInventory(inventoryModel.getInventory());
        List<ActorModel> monsters = monsterDao.getAll(gameStateModel.getPlayerId());
        List<ItemModel> mapItems = itemDao.getAll(playerModel.getId());
        return new GameSaveModel(gameStateModel, playerModel, monsters, inventoryModel, mapItems);
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
