package com.spaceinvaders.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.spaceinvaders.game.components.BulletComponent;
import com.spaceinvaders.game.components.ModelComponent;
import com.spaceinvaders.game.components.PlayerComponent;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;
import com.spaceinvaders.game.screens.GameScreen;

public class BulletSystem extends EntitySystem {

    private ImmutableArray<Entity> bullets;

    private GameScreen game;
    private Engine engine;

    public BulletSystem(GameScreen game) {
        this.game = game;
    }

    @Override
    public void update(float deltaTime) {
        if(game.invaderSystem.entities.size() == 0) {
            Config.GAME_OVER = true;
            Config.GAME_WIN = true;
            return;
        }
        for(Entity bullet : bullets) {

            ModelComponent bulletModel = bullet.getComponent(ModelComponent.class);

            if(bullet.getComponent(BulletComponent.class).isEnemyBullet) {

                bulletModel.position.y -= 300 * deltaTime;

                ModelComponent playerModel = game.playerSystem.modelComponent;
                PlayerComponent playerComponent = game.playerSystem.playerComponent;

                if (bulletModel.position.x < playerModel.position.x + playerModel.size.x &&
                    bulletModel.position.x + bulletModel.size.x > playerModel.position.x &&
                    bulletModel.position.y < playerModel.position.y + playerModel.size.y &&
                    bulletModel.position.y + bulletModel.size.y > playerModel.size.y) {

                    playerComponent.lives --;
                    engine.removeEntity(bullet);
                    if(playerComponent.lives == 0) {
                        Config.GAME_OVER = true;
                        Config.GAME_WIN = false;
                    }
                }
            } else {
                bulletModel.position.y += 300 * deltaTime;

                for(Entity invader : game.invaderSystem.entities) {
                    ModelComponent invaderModel = invader.getComponent(ModelComponent.class);
                    if (bulletModel.position.x < invaderModel.position.x + invaderModel.size.x &&
                        bulletModel.position.x + bulletModel.size.x > invaderModel.position.x &&
                        bulletModel.position.y < invaderModel.position.y + invaderModel.size.y &&
                        bulletModel.position.y + bulletModel.size.y > invaderModel.position.y + 20) {
                        engine.removeEntity(bullet);
                        engine.removeEntity(invader);
                        Assets.enemyHit.play();
                    }
                }
            }
            if(bulletModel.position.y > Config.SCREEN_HEIGHT || bulletModel.position.y < -20) {
                engine.removeEntity(bullet);
            }
        }
    }

    @Override
    public void addedToEngine(Engine engine) {
        bullets = engine.getEntitiesFor(Family.all(BulletComponent.class).get());
        this.engine = engine;
    }
}
