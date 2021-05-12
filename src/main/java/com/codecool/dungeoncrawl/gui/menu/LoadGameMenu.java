package com.codecool.dungeoncrawl.gui.menu;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.LoadGameController;
import com.codecool.dungeoncrawl.model.GameSaveModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.List;

public class LoadGameMenu {

    private final MenuItemTitle menuItemTitle;
    private final GameController gameController;
    private final Scene gameMenu;
    private final LoadGameController loadGameController;

    public LoadGameMenu(MenuItemTitle menuItemTitle, GameController gameController, Scene gameMenu) {
        this.menuItemTitle = menuItemTitle;
        this.gameController = gameController;
        this.gameMenu = gameMenu;
        this.loadGameController = new LoadGameController(gameController);
    }


    protected void displayLoadGamesMenu() {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);

        Label label = new Label(menuItemTitle.getTitle());
        label.setTranslateY(-50);
        label.setScaleX(4);
        label.setScaleY(4);

        TableView<GameSaveModel> table = new TableView<>();
        table.setPlaceholder(new Label("You do not have any saves yet!"));
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        List<GameSaveModel> savedGames = gameController.getAllSaves();
        ObservableList<GameSaveModel> saves = FXCollections.observableArrayList(savedGames);
        table.setItems(saves);

        TableColumn<GameSaveModel, String> playerNameCol = new TableColumn<>("Player");
        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<GameSaveModel, GameSaveModel> saveNameCol = new TableColumn<>("Save");
        saveNameCol.setCellValueFactory(new PropertyValueFactory<>("saveName"));

        TableColumn<GameSaveModel, GameSaveModel> mapNameCol = new TableColumn<>("Map");
        mapNameCol.setCellValueFactory(new PropertyValueFactory<>("currentMap"));

        TableColumn<GameSaveModel, Date> time = new TableColumn<>("Date");
        time.setCellValueFactory(new PropertyValueFactory<>("savedAt"));

        Button loadButton = new Button(MenuItemTitle.LOAD_GAME.getTitle());
        Button cancelButton = new Button(MenuItemTitle.CANCEL.getTitle());
        HBox cnfBtns = new HBox(10);
        cancelButton.setOnMouseClicked(event -> gameController.getPrimaryStage().setScene(gameMenu));
        loadButton.setOnMouseClicked(event -> {
            GameSaveModel selectedItem = table.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
            loadGameController.loadGame(selectedItem);

        });

        cnfBtns.setAlignment(Pos.CENTER);
        cnfBtns.getChildren().addAll(loadButton, cancelButton);

        table.getColumns().addAll(playerNameCol, saveNameCol, mapNameCol, time);

        vBox.getChildren().addAll(label, table, cnfBtns);

        Scene scene = new Scene(vBox, gameController.getCanvas().getWidth(), gameController.getCanvas().getHeight());
        gameController.getPrimaryStage().setScene(scene);
        // gameController.getPrimaryStage().show();
    }
}
