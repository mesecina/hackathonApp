package org.academiadecodigo.invictus;

import java.util.List;

public class Collision {
    public static void detect(List<? extends Character> characters, Player player, List<Projectile> projectiles) {
        for (Character enemy : characters) {
            if (enemy.getX() < player.getX() + player.getWidth() &&
                    enemy.getX() + enemy.getWidth() > player.getX() &&
                    enemy.getY() < player.getY() + player.getHeight() &&
                    enemy.getY() + enemy.getHeight() > player.getY()
            ) {
                enemy.die();
                player.die();
            }
            for (Projectile projectile : projectiles) {
                if (enemy.getX() < projectile.getX() + projectile.getWidth() &&
                        enemy.getX() + enemy.getWidth() > projectile.getX() &&
                        enemy.getY() < projectile.getY() + projectile.getHeight() &&
                        enemy.getY() + enemy.getHeight() > projectile.getY()
                ) {
                    projectile.dispose();
                    enemy.die();
                }
            }
        }
    }
}
