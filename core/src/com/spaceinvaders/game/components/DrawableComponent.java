package com.spaceinvaders.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class DrawableComponent implements Component {

    private Texture texture;

    public DrawableComponent(String texturePath) {
        texture = new Texture(texturePath);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
