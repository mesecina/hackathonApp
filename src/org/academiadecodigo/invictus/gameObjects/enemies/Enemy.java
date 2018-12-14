package org.academiadecodigo.invictus.gameObjects.enemies;

import org.academiadecodigo.invictus.gameObjects.Character;
import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Enemy implements Character {

    protected Picture representation;
    private boolean dead;

    public Enemy() {
        representation = new Picture(Game.WIDTH, Game.HEIGHT - 150 - 100, "enemy.png");
        representation.draw();
    }


    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean isDead() {
        return dead;
    }


}
