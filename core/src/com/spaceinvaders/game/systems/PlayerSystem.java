package com.spaceinvaders.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.spaceinvaders.game.components.ModelComponent;
import com.spaceinvaders.game.components.PlayerComponent;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;
import com.spaceinvaders.game.managers.EntityFactory;
import com.spaceinvaders.game.screens.GameScreen;

import java.util.ArrayList;

public class PlayerSystem extends EntitySystem implements EntityListener {

    public Entity player;
    public ModelComponent modelComponent;
    public PlayerComponent playerComponent;

    private Engine engine;
    private GameScreen game;
    private float shootTimer =0;

    private ArrayList<Entity> bullets;

    public float speed =200;

    public PlayerSystem(GameScreen game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        bullets = new ArrayList<Entity>();
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(Family.all(PlayerComponent.class).get(), this);
        this.engine = engine;
    }

    @Override
    public void update(float delta) {
        shootTimer += delta;
        if(!playerComponent.alive) {
            Config.GAME_OVER = true;
            Config.GAME_WIN = false;
            return;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            modelComponent.position.x -= speed * delta;
            if(modelComponent.position.x < 0)
                modelComponent.position.x = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            modelComponent.position.x += speed * delta;
            if(modelComponent.position.x + modelComponent.size.x > Config.SCREEN_WIDTH)
                modelComponent.position.x = Config.SCREEN_WIDTH - modelComponent.size.x;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && shootTimer > 0.3f) {
            shootTimer = 0;
            Assets.shootSound.play(0.3f);
            engine.addEntity(EntityFactory.createBullet(modelComponent.position.x + 25, modelComponent.position.y + 70, false));
        }

    }

    @Override
    public void entityAdded(Entity entity) {
        player = entity;
        modelComponent = player.getComponent(ModelComponent.class);
        playerComponent = player.getComponent(PlayerComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
