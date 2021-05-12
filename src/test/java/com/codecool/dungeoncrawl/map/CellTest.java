package com.codecool.dungeoncrawl.map;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.HP;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap map = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void getNeighbor() {
        Cell cell = map.getCell(1, 1);
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        Cell cell = map.getCell(1, 0);
        Cell neighbor = cell.getNeighbor(0, -1);
        assertEquals(cell, neighbor);

        cell = map.getCell(1, 2);
        neighbor = cell.getNeighbor(0, 1);
        assertEquals(cell, neighbor);
    }

    @Test
    void isItem_isItemAtSpecificCell() {
        Sword sword = new Sword(5);
        Cell cell = map.getCell(0, 1);
        Cell cell2 = map.getCell(1, 1);
        cell.setItem(sword);
        assertTrue(cell.isItem());
        assertFalse(cell2.isItem());

    }

    @Test
    void isActor_actorPlacedAtSpecificCell() {
        new Skeleton(map.getCell(0, 0));
        assertTrue(map.getCell(0, 0).isActor());
        assertFalse(map.getCell(1, 0).isActor());

    }

    @Test
    void isActorAndItemSamePosition_actorAndItemAtSameXY() {
        Cell cell = map.getCell(1, 2);
        HP hp = new HP(cell, 5);
        Player player = new Player(cell);
        cell.setItem(hp);
        assertTrue(cell.isActorAndItemSamePosition());

    }


}
