package org.academiadecodigo.invictus.gameObjects.blocks;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BaseBlock extends Block {

    private Picture representation;
    public static final int WIDTH = 133;
    public static final int HEIGHT = 300;
    private int speed;


    public BaseBlock(int x, int y) {
        representation = new Picture(x, y, "platform.png");
        representation.draw();
        speed = 10;
    }

    @Override
    public void move() {
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

}
