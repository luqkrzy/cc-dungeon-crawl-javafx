package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.map.Tiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class DisplayInventory extends Display {

    public DisplayInventory(GraphicsContext context, double width, double height) {
        super(context, width, height);
    }

    public void show(List<Item> inventory) {
        context.setFill(Color.BLUE);
        context.fillRect(x - 2, y - 2, 204, 204);
        context.setFill(Color.GRAY);
        context.fillRect(x, y, 200, 200);
        context.strokeText("Inventory", 460, 282);
        int y = 300;
        for (int i = 0; i < inventory.size(); i++, y += 15) {
            Item item = inventory.get(i);
            StringBuilder stringBuilder = new StringBuilder();
            if (item instanceof Sword) {
                stringBuilder.append(item.getName()).append(" ATK+").append(((Sword) item).getDamage());
            } else if (item instanceof Armor) {
                stringBuilder.append(item.getName()).append(" DEF+").append(((Armor) item).getDefense());
            } else {
                stringBuilder.append(item.getName());
            }
            context.strokeText(stringBuilder.toString(), 410, y);
        }

    }

    private void writeText(String inv, int x, int y) {
        for (int i = 0; i < inv.length(); i++) {
            Tiles.drawTile(context, String.valueOf(inv.charAt(i)), x+i , y);

        }
    }


}
