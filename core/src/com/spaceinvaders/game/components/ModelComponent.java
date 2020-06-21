package com.spaceinvaders.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class ModelComponent implements Component {

    public Vector2 position;
    public Vector2 size;

    public ModelComponent(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
    }

}
