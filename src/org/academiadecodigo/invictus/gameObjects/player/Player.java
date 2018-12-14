package org.academiadecodigo.invictus.gameObjects.player;

import org.academiadecodigo.invictus.Character;
import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.invictus.gameObjects.projectiles.Projectile;
import org.academiadecodigo.invictus.keyboard.Key;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player implements Character {


    private Rectangle representation;
    private int lives;
    private boolean dead;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private int jumpSize;
    private boolean shotFired;
    private boolean jumping;
    private boolean falling;
    private int jumpFase;
    private long lastShot;
    private List<Projectile> projectiles;

    public Player() {
        projectiles = new CopyOnWriteArrayList<>();
        representation = new Rectangle(Game.PADDING + Game.HEIGHT / 4 + WIDTH / 2, Game.HEIGHT - 150 - HEIGHT, WIDTH, HEIGHT);
        representation.setColor(Color.BLUE);
        representation.fill();
        lives = 3;
        jumpSize = 9;
        dead = false;
        lastShot = 0;
    }

    public void shoot() {
        if(System.currentTimeMillis() > lastShot + 1000L) {
            projectiles.add(new Projectile(representation.getX() + WIDTH, representation.getY() + HEIGHT/2 - 5));
            lastShot = System.currentTimeMillis();
        }
    }

    @Override
    public void die() {
        dead = true;
        representation.delete();
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void move() {

        if(falling){
            representation.translate(0, jumpSize);
        }


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

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
