package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.map.Cell;
import com.codecool.dungeoncrawl.map.GameMap;
import com.codecool.dungeoncrawl.model.ActorModel;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        setHealth(25);
        setAttack(10);
        setDefense(0);
    }

    public Skeleton(ActorModel monster, GameMap gameMap) {
        super(monster, gameMap);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

