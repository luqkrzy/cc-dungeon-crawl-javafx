package com.codecool.dungeoncrawl.gui.menu;

public enum MenuItemTitle {
    DUNGEON_CRAWL("DUNGEON CRAWL"), GAME_OVER("GAME OVER!"), NEW_GAME("New Game"), LOAD_GAME("Load Game"), QUIT_GAME("Quit Game"),
    CONFIRM("Confirm"), SAVE("Save"), CANCEL("Cancel"), SAVE_NAME("Save name: ");

    private final String title;

    MenuItemTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
