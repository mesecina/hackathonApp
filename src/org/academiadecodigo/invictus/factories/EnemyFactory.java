package org.academiadecodigo.invictus.factories;

import org.academiadecodigo.invictus.gameObjects.enemies.MovingEnemy;

import java.util.LinkedList;
import java.util.List;

public class EnemyFactory {

    public static List<MovingEnemy> getEnemies() {

        List<MovingEnemy> enemyList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            enemyList.add(new MovingEnemy());
        }

        return enemyList;
    }

}
