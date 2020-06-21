package com.spaceinvaders.game.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component {

    public boolean alive ;
    public int lives;

    public PlayerComponent() {
        alive = true;
        lives = 3;
    }

}
