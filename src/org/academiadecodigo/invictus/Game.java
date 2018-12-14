package org.academiadecodigo.invictus;

import org.academiadecodigo.invictus.factories.BlockFactory;
import org.academiadecodigo.invictus.gameObjects.blocks.BaseBlock;
import org.academiadecodigo.invictus.gameObjects.player.Player;
import org.academiadecodigo.invictus.gameObjects.projectiles.Projectile;
import org.academiadecodigo.invictus.keyboard.Key;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    public static final int PADDING = 10;
    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1000;
    public static final int INICIAL_BLOCK_SIZE = 15;
    public static final int BLOCK_SIZE = 50;

    private Rectangle background;
    private Player player;
    private Map<Key, Boolean> keys;
    private BaseBlock[] firstBlock;
    private List<BaseBlock[]> screenBlocks;
    private List<BaseBlock[]> blocks;

    private int gapSize = 250;

    public Game() {
        keys = new ConcurrentHashMap<>();
        background = new Rectangle(0, 0, WIDTH, HEIGHT);
        background.setColor(Color.BLACK);
        background.fill();
        blocks = new LinkedList<>();
        screenBlocks = new LinkedList<>();
        player = new Player();
        firstBlock = BlockFactory.getFirstBlockSet(INICIAL_BLOCK_SIZE);
        screenBlocks.add(firstBlock);

        for (int i = 0; i < 3; i++) {
            blocks.add(BlockFactory.getBlockSet(15));
        }

    }


    public void start() {


        Timer loop = new Timer();
        loop.schedule(new TimerTask() {
            @Override
            public void run() {
                inputProcessing();
                Collision.playerFall(player, screenBlocks);
                player.move();
                moveBlocks();
                //moveEnemies();
                moveProjectiles();
                //Collision.detect(enemies, player, player.getProjectiles());

                if (player.isDead()) {
                    loop.cancel();
                }
            }
        }, 0, 15);

    }

    public void keyPressed(Key key) {
        keys.put(key, true);
    }

    public void keyReleased(Key key) {
        keys.put(key, false);
    }

    private void inputProcessing() {
        for (Key key : keys.keySet()) {
            if (keys.get(key)) {
                player.act(key);
            }
        }
    }

    private void moveBlocks() {

        for (int i = 0; i < screenBlocks.size(); i++) {

            for (int j = 0; j < screenBlocks.get(i).length; j++) {
                screenBlocks.get(i)[j].move();
            }
        }

        BaseBlock[] lastBlock = screenBlocks.get(screenBlocks.size() - 1);
        BaseBlock[] firstBlock = screenBlocks.get(0);

        System.out.println(blocks.get(0));

        if (WIDTH - (lastBlock[lastBlock.length - 1].getX() + lastBlock[lastBlock.length - 1].getWidth()) > gapSize) {
            BaseBlock[] nextBlock = blocks.remove(0);
            screenBlocks.add(nextBlock);
        }

        if(firstBlock[firstBlock.length - 1].getX() + firstBlock[firstBlock.length - 1].getWidth() < 0 ){
            BaseBlock[] recycledBlock = screenBlocks.remove(0);
            blocks.add(recycledBlock);

            for (int i = 0; i < recycledBlock.length; i++) {
                recycledBlock[i].getRepresentation().translate(WIDTH + 15 * BaseBlock.WIDTH + PADDING,0);
            }

        }
    }

    private void moveProjectiles() {
        for(Projectile projectile : player.getProjectiles()) {
            projectile.move();
        }
    }

}
