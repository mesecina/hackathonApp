package org.academiadecodigo.invictus.gameObjects.projectiles;

import org.academiadecodigo.invictus.Movable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Projectile implements Movable {

    private Rectangle representation;
    private boolean dead;

    public Projectile(int x, int y) {
        dead = false;
        representation = new Rectangle(x, y, 10 ,10);
        representation.setColor(Color.YELLOW);
        representation.fill();
    }

    @Override
    public void move() {
        representation.translate(5, 0);
    }

    public void dispose() {
        dead = true;
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
