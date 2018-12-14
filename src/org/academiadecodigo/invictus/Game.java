package org.academiadecodigo.invictus;

import org.academiadecodigo.invictus.factories.BlockFactory;
import org.academiadecodigo.invictus.factories.EnemyFactory;
import org.academiadecodigo.invictus.gameObjects.blocks.BaseBlock;
import org.academiadecodigo.invictus.gameObjects.enemies.MovingEnemy;
import org.academiadecodigo.invictus.gameObjects.player.Player;
import org.academiadecodigo.invictus.gameObjects.projectiles.Projectile;
import org.academiadecodigo.invictus.keyboard.Key;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    public static final int PADDING = 10;
    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1000;
    private static final int INITIAL_BLOCK_SIZE = 15;

    private static int score;
    private Text scoreText;

    private Picture background;
    private Player player;
    private Map<Key, Boolean> keys;
    private BaseBlock[] firstBlock;

    private List<BaseBlock[]> blocks;
    private List<BaseBlock[]> screenBlocks;

    private List<MovingEnemy> enemies;
    private List<MovingEnemy> screenEnemies;

    private Picture gameOver;
    private Menu menu;

    private boolean waitingRestart;

    private boolean bg2;
    private boolean bg3;

    private int gapSize = 250;

    private Sound sound;

    public Game() {
        init();
    }

    private void init() {
        menu = new Menu();
        preGame();
        background = new Picture(0, 0, "backgroundZanzibar.png");
        background.draw();

        player = new Player();
        keys = new ConcurrentHashMap<>();

        firstBlock = BlockFactory.getFirstBlockSet(INITIAL_BLOCK_SIZE);
        blocks = new LinkedList<>();
        screenBlocks = new LinkedList<>();
        screenBlocks.add(firstBlock);

        enemies = EnemyFactory.getEnemies();
        screenEnemies = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            blocks.add(BlockFactory.getBlockSet(15));
        }

        gameOver = new Picture(0, 0, "loser.jpg");

        bg2 = false;
        bg3 = false;
        score = 0;

        sound = new Sound("/music.wav");
        start();
    }

    private void preGame() {
        menu.play();
    }

    private void start() {

        sound.play(true);
        score();

        Timer loop = new Timer();
        loop.schedule(new TimerTask() {
            @Override
            public void run() {

                inputProcessing();
                Collision.playerFall(player, screenBlocks);
                Collision.detect(screenEnemies, enemies, player, player.getProjectiles());

                scoreText.setText("SCORE: " + score);

                if (score == 30 && !bg2) {

                    background.load("background2.jpg");
                    player.getRepresentation().load("player2.png");

                    for (int i = 0; i < screenEnemies.size(); i++) {
                        screenEnemies.get(i).getRepresentation().load("enemy2.png");
                        screenEnemies.get(i).setSpeed(25);
                    }

                    for (int i = 0; i < enemies.size(); i++) {
                        enemies.get(i).getRepresentation().load("enemy2.png");
                        enemies.get(i).setSpeed(25);
                    }

                    bg2 = true;
                }

                if (score == 60 && !bg3) {
                    background.load("background3.jpg");
                    player.getRepresentation().load("player3.png");

                    for (int i = 0; i < screenEnemies.size(); i++) {
                        screenEnemies.get(i).getRepresentation().load("enemy3.png");
                        screenEnemies.get(i).setSpeed(34);
                    }

                    for (int i = 0; i < enemies.size(); i++) {
                        enemies.get(i).getRepresentation().load("enemy3.png");
                        enemies.get(i).setSpeed(34);
                    }

                    bg3 = true;
                }

                player.move();
                moveBlocks();
                moveEnemies();
                moveProjectiles();

                if (player.isDead()) {
                    gameOver();
                    loop.cancel();
                    waitingRestart = true;
                    while (waitingRestart) {
                        inputProcessing();
                    }
                }
            }
        }, 0, 15);
    }

    private void score() {

        scoreText = new Text(1500, 20, "SCORE: " + score);
        scoreText.grow(20, 10);
        scoreText.setColor(Color.ORANGE);
        scoreText.draw();
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
                if (key == Key.RESTART && waitingRestart) {
                    waitingRestart = false;
                    init();
                    return;
                }

                player.act(key);
            }
        }
    }

    private int count = 0;

    private void moveBlocks() {

        for (int i = 0; i < screenBlocks.size(); i++) {

            for (int j = 0; j < screenBlocks.get(i).length; j++) {
                screenBlocks.get(i)[j].move();

                if (i == screenBlocks.size() - 1) {
                    count++;

                    if (count > 1500) {

                        if (true) {

                            MovingEnemy enemy = enemies.remove(0);
                            screenEnemies.add(enemy);
                            count = 0;
                        }
                    }
                }
            }
        }

        BaseBlock[] lastBlock = screenBlocks.get(screenBlocks.size() - 1);
        BaseBlock[] firstBlock = screenBlocks.get(0);


        if (WIDTH - (lastBlock[lastBlock.length - 1].getX() + lastBlock[lastBlock.length - 1].getWidth()) > gapSize) {

            BaseBlock[] nextBlock = blocks.remove(0);
            screenBlocks.add(nextBlock);
        }

        if (firstBlock[firstBlock.length - 1].getX() + firstBlock[firstBlock.length - 1].getWidth() < 0) {

            BaseBlock[] recycledBlock = screenBlocks.remove(0);
            blocks.add(recycledBlock);

            for (int i = 0; i < recycledBlock.length; i++) {
                recycledBlock[i].getRepresentation().translate(WIDTH + 15 * BaseBlock.WIDTH + PADDING, 0);
            }
        }
    }

    private void moveProjectiles() {
        for (Projectile projectile : player.getProjectiles()) {
            projectile.move();
        }
    }

    private void moveEnemies() {
        for (MovingEnemy enemy : screenEnemies) {
            enemy.move();

            if (enemy.getX() + enemy.getWidth() < 0) {
                MovingEnemy recycledEnemy = screenEnemies.remove(0);
                enemies.add(recycledEnemy);
                enemy.getRepresentation().translate(WIDTH + enemy.getWidth() + PADDING, 0);
            }

        }
    }

    public static void incrementScore() {
        score += 5;
    }

    private void gameOver() {
        gameOver.draw();

        scoreText.grow(20, 10);
        scoreText.setColor(Color.ORANGE);
        scoreText.setText("SCORE: " + score);
        scoreText.draw();

        sound.stop();
    }

    public enum Status {
        MENU,
        PLAY,
        QUIT
    }

}
