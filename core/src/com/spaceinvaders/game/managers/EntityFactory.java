package com.spaceinvaders.game.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.spaceinvaders.game.components.*;

public abstract class EntityFactory {

    public static final float INVADER_WIDTH  = 60;
    public static final float INVADER_HEIGHT = 60;
    public static final float PLAYER_WIDTH   = 60;
    public static final float PLAYER_HEIGHT  = 60;
    public static final float BULLET_WIDTH   = 20;
    public static final float BULLET_HEIGHT  = 30;

    public static Entity createInvader(float x, float y) {

        Entity entity = new Entity();

        ModelComponent modelComponent = new ModelComponent(new Vector2(x, y), new Vector2(INVADER_WIDTH, INVADER_HEIGHT));
        entity.add(modelComponent);

        DrawableComponent drawableComponent = new DrawableComponent("core/assets/invader.png");
        entity.add(drawableComponent);

        InvaderComponent invaderComponent = new InvaderComponent();
        entity.add(invaderComponent);

        return entity;
    }

    public static Entity createPlayer(float x, float y) {

        Entity entity = new Entity();

        ModelComponent modelComponent = new ModelComponent(new Vector2(x, y), new Vector2(PLAYER_WIDTH, PLAYER_HEIGHT));
        entity.add(modelComponent);

        DrawableComponent drawableComponent = new DrawableComponent("core/assets/ship1.png");
        entity.add(drawableComponent);

        PlayerComponent playerComponent = new PlayerComponent();
        entity.add(playerComponent);

        return entity;
    }

    public static Entity createBullet(float x, float y, boolean isEnemyBullet) {
        Entity entity = new Entity();

        ModelComponent modelComponent = new ModelComponent(new Vector2(x, y), new Vector2(BULLET_WIDTH, BULLET_HEIGHT));
        entity.add(modelComponent);

        DrawableComponent drawableComponent = new DrawableComponent("core/assets/bullet.png");
        entity.add(drawableComponent);

        BulletComponent bulletComponent = new BulletComponent(isEnemyBullet);
        entity.add(bulletComponent);

        return entity;
    }
}
