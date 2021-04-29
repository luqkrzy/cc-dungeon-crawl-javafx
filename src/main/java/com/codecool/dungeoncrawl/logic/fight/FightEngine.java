package com.codecool.dungeoncrawl.logic.fight;
import com.codecool.dungeoncrawl.gui.BottomGridPane;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import java.util.Random;

public class FightEngine {

    Random random = new Random();

    public void fight(Player player, Monster monster) {

            boolean b = random.nextBoolean();
            round(b ? player : monster, b ? monster : player);
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
            defender.die();
        }

    }


    public void fight2(Player player, Monster monster) {
        int attackerAtk = player.getAttack();
        int attackerDef = player.getDefense();
        int attackerHealth = player.getHealth();

        int defenderAtk = monster.getAttack();
        int defenderDef = monster.getDefense();
        int defenderHealth = monster.getHealth();
        int damageCaused = attackerAtk - defenderDef;
        monster.setHealth(defenderHealth - damageCaused);
        BottomGridPane.log(String.format("%s's hit caused %d damage to %s", player.getInstanceName(), damageCaused, monster.getInstanceName()));
        if (monster.isAlive()) {
            damageCaused = defenderAtk - attackerDef;
            BottomGridPane.log(String.format("%s's hit caused %d damage to %s", monster.getInstanceName(), damageCaused, player.getInstanceName()));
            player.setHealth(attackerHealth - damageCaused);
        } else {
            monster.die();
            BottomGridPane.log(String.format("%s died", monster.getInstanceName()));

        }

    }


}
