package org.academiadecodigo.invictus.factories;

import org.academiadecodigo.invictus.Character;
import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.invictus.gameObjects.blocks.BaseBlock;
import org.academiadecodigo.invictus.gameObjects.enemies.MovingEnemy;

import java.util.LinkedList;
import java.util.List;

public class EnemyFactory {

    public static List<? extends Character> getEnemies(){

        List<Character> enemyList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            enemyList.add(new MovingEnemy());
        }

        return enemyList;
    }

}
