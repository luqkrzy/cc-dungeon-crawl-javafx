package com.codecool.dungeoncrawl.gui.menu;

import com.codecool.dungeoncrawl.gui.GameController;
import com.codecool.dungeoncrawl.model.GameSaveModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.List;

public class GameMenu {
    protected final MenuItemTitle menuItemTitle;
    protected final GameController gameController;
    protected Scene gameMenu;

    public GameMenu(GameController gameController, MenuItemTitle menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
        this.gameController = gameController;
        this.gameMenu = setupSceneMenu();
    }

    public void initMainMenu() {
        gameController.getPrimaryStage().setScene(gameMenu);
        gameController.getPrimaryStage().show();
    }

    public Scene setupSceneMenu() {
        VBox vbox = new VBox(10);
        Group group = setupButtons(vbox);
        setUpVbox(vbox, group);
        return new Scene(vbox, gameController.getCanvas().getWidth(), gameController.getCanvas().getHeight());
    }

    private void setUpVbox(VBox vbox, Group group) {
        vbox.getChildren().addAll(group.getChildren());
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #929295");
    }

    private Group setupButtons(VBox vbox) {
        Label label = new Label(menuItemTitle.getTitle());
        label.setTranslateY(-100);
        label.setScaleX(4);
        label.setScaleY(4);
        Button newGameBtn = new Button(MenuItemTitle.NEW_GAME.getTitle());
        Button loadGameBtn = new Button(MenuItemTitle.LOAD_GAME.getTitle());
        Button quitGameBtn = new Button(MenuItemTitle.QUIT_GAME.getTitle());
        Button cnfBtn = new Button(MenuItemTitle.CONFIRM.getTitle());
        Button cancelBtn = new Button(MenuItemTitle.CANCEL.getTitle());
        Label playerLabel = new Label("Enter Player Name:");
        TextField playerName = createNewUserName();
        HBox hBox = new HBox(10, cnfBtn, cancelBtn);
        hBox.setAlignment(Pos.CENTER);
        newGameBtn.setMaxWidth(200);
        loadGameBtn.setMaxWidth(200);
        quitGameBtn.setMaxWidth(200);
        newGameBtn.setDefaultButton(true);
        Group group = new Group(label, newGameBtn, loadGameBtn, quitGameBtn);

        newGameBtn.setOnMouseClicked(mouseEvent -> {
            vbox.getChildren().removeAll(label, newGameBtn, loadGameBtn, quitGameBtn);
            vbox.getChildren().addAll(playerLabel, playerName);
            vbox.getChildren().add(hBox);
        });

        cnfBtn.setOnMouseClicked(mouseEvent -> {
            gameController.startNewGame(playerName.getText());

        });
        cancelBtn.setOnMouseClicked(mouseEvent -> {
            vbox.getChildren().removeAll(hBox, playerLabel, playerName);
            vbox.getChildren().addAll(label, newGameBtn, loadGameBtn, quitGameBtn);
        });

        // cancelBtn.setOnMouseClicked(mouseEvent -> gameController.getPrimaryStage().setScene(mainMenu));
        quitGameBtn.setOnMouseClicked(mouseEvent -> gameController.getPrimaryStage().close());

        loadGameBtn.setOnMouseClicked(mouseEvent -> displayLoadGamesMenu());

        return group;
    }

    private void displayLoadGamesMenu() {
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
            gameController.loadGame(selectedItem);

        });

        cnfBtns.setAlignment(Pos.CENTER);
        cnfBtns.getChildren().addAll(loadButton, cancelButton);

        table.getColumns().addAll(playerNameCol, saveNameCol, mapNameCol, time);

        vBox.getChildren().addAll(label, table, cnfBtns);

        Scene scene = new Scene(vBox, gameController.getCanvas().getWidth(), gameController.getCanvas().getHeight());
        gameController.getPrimaryStage().setScene(scene);
        // gameController.getPrimaryStage().show();
    }

    private TextField createNewUserName() {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        textField.setPromptText("Player Name");
        return textField;
    }


}


