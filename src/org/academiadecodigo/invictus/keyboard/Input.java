package org.academiadecodigo.invictus.keyboard;

import org.academiadecodigo.invictus.Game;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Input implements KeyboardHandler {

    private Game game;

    public Input(Game game) {
        this.game = game;
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_UP);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(jump);

        KeyboardEvent jumpReleased = new KeyboardEvent();
        jumpReleased.setKey(KeyboardEvent.KEY_UP);
        jumpReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(jumpReleased);

        KeyboardEvent shoot = new KeyboardEvent();
        shoot.setKey(KeyboardEvent.KEY_SPACE);
        shoot.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(shoot);

        KeyboardEvent shootReleased = new KeyboardEvent();
        shootReleased.setKey(KeyboardEvent.KEY_SPACE);
        shootReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(shootReleased);

        KeyboardEvent restart = new KeyboardEvent();
        restart.setKey(KeyboardEvent.KEY_R);
        restart.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(restart);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        game.keyPressed(Key.toKey(keyboardEvent.getKey()));
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        game.keyReleased(Key.toKey(keyboardEvent.getKey()));
    }
}
