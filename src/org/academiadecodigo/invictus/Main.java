package org.academiadecodigo.invictus;

import org.academiadecodigo.invictus.keyboard.Input;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Input input = new Input(game);
        game.start();

    }

}
