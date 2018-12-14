package org.academiadecodigo.invictus.gameObjects.projectiles;

import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.invictus.gameObjects.Movable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Projectile implements Movable {

    private Picture representation;

    public Projectile(int x, int y) {
        representation = new Picture(x, y, "guitar1.png");
        representation.draw();
    }

    @Override
    public void move() {
        representation.translate(5, 0);
    }

    public void dispose() {
        representation.translate(0, -Game.HEIGHT);
        representation.delete();
    }

    @Override
    public int getX() {
        return representation.getX();
    }

    @Override
    public int getY() {
        return representation.getY();
    }

    @Override
    public int getWidth() {
        return representation.getWidth();
    }

    @Override
    public int getHeight() {
        return representation.getHeight();
    }
}
