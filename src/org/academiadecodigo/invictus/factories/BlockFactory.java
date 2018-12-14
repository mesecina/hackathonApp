package org.academiadecodigo.invictus.factories;

import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.invictus.gameObjects.blocks.BaseBlock;

public class BlockFactory {

    public static BaseBlock[] getBlockSet(int width) {

        BaseBlock[] blockSet = new BaseBlock[width];

        for (int i = 0; i < width; i++) {
            blockSet[i] = new BaseBlock(Game.WIDTH + i * BaseBlock.WIDTH, Game.HEIGHT - BaseBlock.HEIGHT / 2);
        }

        return blockSet;

    }

    public static BaseBlock[] getFirstBlockSet(int width) {

        BaseBlock[] blockSet = new BaseBlock[width];

        for (int i = 0; i < width; i++) {
            blockSet[i] = new BaseBlock(i * BaseBlock.WIDTH, Game.HEIGHT - BaseBlock.HEIGHT / 2);
        }

        return blockSet;

    }

}
