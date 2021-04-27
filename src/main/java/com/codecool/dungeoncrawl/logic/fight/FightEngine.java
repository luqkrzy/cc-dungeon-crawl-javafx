package com.codecool.dungeoncrawl.logic.fight;

import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.Random;

public class FightEngine {

    Random random = new Random();

    public void fight(Player player, Monster monster) {

        if (player.isAlive() || monster.isAlive()) {
            boolean b = random.nextBoolean();
            round(b ? player : monster, b ? monster : player);
        }
    }

    public void round(Actor attacker, Actor defender) {
        int attackerAtk = attacker.getAttack();
        int attackerDef = attacker.getDefense();
        int attackerHealth = attacker.getHealth();

        int defenderAtk = defender.getAttack();
        int defenderDef = defender.getDefense();
        int defenderHealth = defender.getHealth();
        int damageCaused = attackerAtk - defenderDef;
        defender.setHealth(defenderHealth - damageCaused);
        BottomGridPane.log(String.format("%s's hit caused %d damage to %s", attacker.getInstanceName(), damageCaused, defender.getInstanceName()));
        if (defender.isAlive()) {
            damageCaused = defenderAtk - attackerDef;
            attacker.setHealth(attackerHealth - damageCaused);
        } else {
            defender.getCell().setActor(null);
        }

    }


}
