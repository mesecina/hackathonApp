package org.academiadecodigo.invictus.gameObjects.player;

import org.academiadecodigo.invictus.Character;
import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.invictus.keyboard.Key;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player implements Character {


    private Rectangle representation;
    private int lives;
    private boolean dead;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private int jumpSize;
    private boolean shotFired;
    private boolean jumping;
    private int jumpFase;

    public Player() {
        representation = new Rectangle(Game.PADDING + Game.HEIGHT / 4 + WIDTH / 2, Game.HEIGHT - 150 - HEIGHT, WIDTH, HEIGHT);
        representation.setColor(Color.BLUE);
        representation.fill();
        lives = 3;
        jumpSize = 9;
        dead = false;
    }

    @Override
    public void die() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void move() {
        if (jumping) {
            if(jumpFase == 70){
                jumping = false;
                jumpFase = 0;
                return;
            }

            if (jumpFase < 35) {
                representation.translate(0, -jumpSize);
                jumpFase++;
                return;
            }

            representation.translate(0, jumpSize);
            jumpFase++;

        }
    }

    public void act(Key key) {
        switch (key) {
            case UP:
                up();
                break;
            case SPACE:
                shoot();
                break;
        }
    }

    public void shoot() {

    }

    public void up() {
        if (jumping) {
            return;
        }
        //representation.translate(0, -5);
        jumping = true;
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
