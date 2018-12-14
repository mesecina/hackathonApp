package org.academiadecodigo.invictus.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public enum Key {
    UP(KeyboardEvent.KEY_UP),
    DOWN(KeyboardEvent.KEY_DOWN),
    LEFT(KeyboardEvent.KEY_LEFT),
    RIGHT(KeyboardEvent.KEY_RIGHT),
    SPACE(KeyboardEvent.KEY_SPACE);

    public final int id;

    Key(int id) {
        this.id = id;
    }

    public static Key toKey(int id) {
        for (Key key : values()) {
            if (key.id == id) {
                return key;
            }
        }
        throw new IllegalArgumentException();
    }
}

