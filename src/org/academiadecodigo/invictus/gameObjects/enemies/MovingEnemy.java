package org.academiadecodigo.invictus.gameObjects.enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MovingEnemy extends Enemy {

    private boolean dead;
    private boolean falling;
    private int fallSize = 15;

    private int speed;

    public MovingEnemy() {
        super();
        dead = false;

        speed = 15;
    }

    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void move() {

        if (falling) {
            representation.translate(0, fallSize);
        }

        representation.translate(-speed, 0);
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

    public Picture getRepresentation() {
        return representation;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
