package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.map.Tiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayGameOver extends Display {
    public DisplayGameOver(GraphicsContext context, double x, double y) {
        super(context, x, y);
    }

    public void show() {
        context.setFill(Color.GRAY);
        context.fillRect(x - 150, y - 20, 400, 50);

        writeText("GAME OVER", 10, 10);
    }


    private void writeText(String str, int x, int y) {
        for (int i = 0; i < str.length(); i++) {
            Tiles.drawTile(context, String.valueOf(str.charAt(i)), x + i, y);

        }
    }

}
