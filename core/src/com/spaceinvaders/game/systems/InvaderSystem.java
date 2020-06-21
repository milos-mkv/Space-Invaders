package com.spaceinvaders.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.spaceinvaders.game.components.InvaderComponent;
import com.spaceinvaders.game.components.ModelComponent;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;
import com.spaceinvaders.game.managers.EntityFactory;
import com.spaceinvaders.game.screens.GameScreen;

public class InvaderSystem extends EntitySystem {

    public ImmutableArray<Entity> entities;

    private Engine engine;
    private GameScreen game;

    public boolean down = true;
    public boolean wallHitFlag = false;
    public int lvl = 0;
    public float shootTimer = 0;

    public InvaderSystem(GameScreen game) {
        this.game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(InvaderComponent.class).get());
        this.engine = engine;
    }

    @Override
    public void update(float delta) {

        if(entities.size() == 0) {
            Config.GAME_OVER = true;
            Config.GAME_WIN = true;
            return;
        }

        shootTimer += delta;
        if(shootTimer > Config.INVADER_SHOOT_SPEED) {
            Assets.enemyShootSound.play(0.2f);
            ModelComponent invaderModel = entities.random().getComponent(ModelComponent.class);
            engine.addEntity(EntityFactory.createBullet(invaderModel.position.x + 25, invaderModel.position.y - 5, true));
            shootTimer = 0;
        }
        float old = lvl;

        for (Entity entity : entities) {
            ModelComponent modelComponent = entity.getComponent(ModelComponent.class);
            modelComponent.position.x += Config.INVADER_SPEED * delta;

            if(!wallHitFlag) {
                if (modelComponent.position.x + modelComponent.size.x > Config.SCREEN_WIDTH || modelComponent.position.x < 0) {
                    wallHitFlag = true;
                    if (down) {
                        lvl++;
                        if (lvl == 5) down = false;
                    } else {
                        lvl--;
                        if (lvl == 0) down = true;
                    }
                }
            }
        }

        if(wallHitFlag) {
            Config.INVADER_SPEED = Config.INVADER_SPEED * -1;
            wallHitFlag = false;
        }

        if(old != lvl) {
            for (Entity entity : entities) {
                ModelComponent modelComponent = entity.getComponent(ModelComponent.class);
                modelComponent.position.x += Config.INVADER_SPEED * delta;
                if (down) {
                    modelComponent.position.y -= EntityFactory.INVADER_HEIGHT / 2;
                } else {
                    modelComponent.position.y += EntityFactory.INVADER_HEIGHT / 2;
                }
            }
        }
    }
}
