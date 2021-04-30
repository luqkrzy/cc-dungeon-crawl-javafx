package com.codecool.dungeoncrawl.map;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HP;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String name) {
        InputStream is = MapLoader.class.getResourceAsStream(name);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> cell.setType(CellType.EMPTY);
                        case '#' -> cell.setType(CellType.WALL);
                        case '.' -> cell.setType(CellType.FLOOR);
                        case 'd' -> cell.setType(CellType.DOORS);
                        case '{' -> cell.setType(CellType.STAIRS);
                        case '<' -> cell.setType(CellType.SPIKE);
                        case 's' -> {
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Skeleton(cell));
                        }
                        case '@' -> {
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                        }
                        case 'k' -> {
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "key", 17, 3);
                        }
                        case 'g' -> {
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Ghost(cell));
                        }
                        case 'm' -> {
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Mage(cell));
                        }
                        case '>' -> {
                            cell.setType(CellType.FLOOR);
                            new Sword(cell, "sword", 3);
                        }
                        case '[' -> {
                            cell.setType(CellType.FLOOR);
                            new Armor(cell, "armor", 3);
                        }
                        case 'h' -> {
                            cell.setType(CellType.FLOOR);
                            new HP(cell, "hp");
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        map.initItems();

        return map;
    }

}
