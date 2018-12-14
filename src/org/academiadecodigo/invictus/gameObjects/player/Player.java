package org.academiadecodigo.invictus.gameObjects.player;

import org.academiadecodigo.invictus.gameObjects.Character;
import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.invictus.gameObjects.projectiles.Projectile;
import org.academiadecodigo.invictus.keyboard.Key;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player implements Character {

    private Picture representation;
    private boolean dead;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private int jumpSize;
    private boolean jumping;
    private boolean falling;
    private int jumpFase;
    private long lastShot;
    private List<Projectile> projectiles;

    public Player() {
        projectiles = new CopyOnWriteArrayList<>();
        representation = new Picture(Game.PADDING + Game.HEIGHT / 4 + WIDTH / 2, Game.HEIGHT - 115 - 150, "player1.png");
        representation.draw();
        jumpSize = 9;
        dead = false;
        lastShot = 0;
    }

    private void shoot() {
        if (System.currentTimeMillis() > lastShot + 1000L) {
            projectiles.add(new Projectile(representation.getX() + WIDTH, representation.getY() + HEIGHT / 2 + 10));
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
        return dead;
    }

    @Override
    public void move() {

        if (falling) {
            representation.translate(0, jumpSize);
        }

        if (jumping) {
            if (jumpFase == 70) {
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

    private void up() {

        if (jumping) {
            return;
        }

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

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public Picture getRepresentation() {
        return representation;
    }

    public boolean isJumping() {
        return jumping;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
