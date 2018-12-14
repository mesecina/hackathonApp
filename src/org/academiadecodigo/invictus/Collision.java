package org.academiadecodigo.invictus;

import org.academiadecodigo.invictus.gameObjects.blocks.BaseBlock;
import org.academiadecodigo.invictus.gameObjects.player.Player;
import org.academiadecodigo.invictus.gameObjects.projectiles.Projectile;

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

    public static void playerFall(Player player, List<BaseBlock[]> screenBlocks) {
        for (int i = 0; i < screenBlocks.size(); i++) {
            for (int j = 0; j < screenBlocks.get(i).length; j++) {
                if (screenBlocks.get(i)[j].getX() < player.getX() && screenBlocks.get(i)[j].getX() + screenBlocks.get(i)[j].getWidth() > player.getX()) {
                    return;
                }
            }
            if(!player.isJumping()) {
                player.setFalling(true);
            }
        }
    }
}
