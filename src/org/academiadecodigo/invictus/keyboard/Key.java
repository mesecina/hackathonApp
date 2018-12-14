package org.academiadecodigo.invictus.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public enum Key {
    UP(KeyboardEvent.KEY_UP),
    RESTART(KeyboardEvent.KEY_R),
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

