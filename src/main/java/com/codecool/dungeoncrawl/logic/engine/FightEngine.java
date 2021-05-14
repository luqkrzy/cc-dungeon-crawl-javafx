package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.gui.window.BottomGridPane;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class FightEngine {

    public void fight(Player player, Monster monster) {
        int playerAtk = player.getAttack();
        int playerDef = player.getDefense();
        int playerHealth = player.getHealth();

        int monsterAtk = monster.getAttack();
        int monsterDef = monster.getDefense();
        int monsterHealth = monster.getHealth();

        int damageCaused = playerAtk - monsterDef;
        monster.setHealth(monsterHealth - damageCaused);
        BottomGridPane.log(String.format("%s's hit caused %d damage to %s", player.getInstanceName(), damageCaused, monster.getInstanceName()));
        if (monster.isAlive()) {
            damageCaused = monsterAtk - playerDef;
            damageCaused = Math.max(damageCaused, 0);
            BottomGridPane.log(String.format("%s's hit caused %d damage to %s", monster.getInstanceName(), damageCaused, player.getInstanceName()));
            player.setHealth(playerHealth - damageCaused);
        } else {
            monster.die();
            BottomGridPane.log(String.format("%s died", monster.getInstanceName()));
        }

    }
}
