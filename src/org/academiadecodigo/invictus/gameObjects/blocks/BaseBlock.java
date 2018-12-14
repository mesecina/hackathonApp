package org.academiadecodigo.invictus.gameObjects.blocks;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class BaseBlock extends Block {

    private Rectangle representation;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 300;
    private int x;
    private int y;



    public BaseBlock(int x, int y) {
        representation = new Rectangle(x, y, WIDTH, HEIGHT);
        representation.setColor(Color.RED);
        representation.fill();
        this.x = x;
        this.y = y;
    }

    @Override
    public void move() {
        representation.translate(-10, 0);
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

    public Rectangle getRepresentation() {
        return representation;
    }
}
