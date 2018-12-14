package org.academiadecodigo.invictus.gameObjects.enemies;

import org.academiadecodigo.invictus.Character;
import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class Enemy implements Character {

    private Rectangle representation;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private int x;
    private int y;
    private boolean dead;



    public Enemy() {
        representation = new Rectangle(Game.PADDING + Game.HEIGHT / 4 + WIDTH / 2, Game.HEIGHT - 150 - HEIGHT, WIDTH, HEIGHT);
        representation.setColor(Color.GREEN);
        representation.fill();
        this.x = x;
        this.y = y;
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
        representation.translate(-10, 0);
    }

}
