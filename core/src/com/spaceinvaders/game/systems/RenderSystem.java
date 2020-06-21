package com.spaceinvaders.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceinvaders.game.SpaceInvaders;
import com.spaceinvaders.game.components.DrawableComponent;
import com.spaceinvaders.game.components.ModelComponent;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;
import com.spaceinvaders.game.managers.EntityFactory;
import com.spaceinvaders.game.screens.GameScreen;
import com.project.managers.FrameRate;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private Engine engine;
    private GameScreen game;
    private SpriteBatch spriteBatch;
    public  FrameRate frameRate;

    public RenderSystem(GameScreen game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        spriteBatch = new SpriteBatch();
        frameRate = new FrameRate();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(DrawableComponent.class).get());
        this.engine = engine;
    }

    @Override
    public void update(float delta) {

        spriteBatch.begin();
        if(Config.HARD_MODE_ON) {
            spriteBatch.draw(Assets.hardAnimation.getKeyFrame(Config.ELAPSED), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        } else {
            spriteBatch.draw(Assets.easyBackground, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        }
        for(Entity entity : entities) {
            DrawableComponent drawableComponent = entity.getComponent(DrawableComponent.class);
            ModelComponent modelComponent = entity.getComponent(ModelComponent.class);
            spriteBatch.draw(drawableComponent.getTexture(), modelComponent.position.x,modelComponent.position.y, modelComponent.size.x, modelComponent.size.x);
        }
        for(int i=0; i<game.playerSystem.playerComponent.lives; i++) {
            spriteBatch.draw(Assets.lifeTexture, i*25 +20, 10, 20, 20);
        }

        spriteBatch.end();

        frameRate.update();
        frameRate.render();
    }
}
