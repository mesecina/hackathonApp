package org.academiadecodigo.invictus.gameObjects.enemies;

import org.academiadecodigo.invictus.Character;

public abstract class Enemy implements Character {

    @Override
    public void die() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void move() {

    }

}
