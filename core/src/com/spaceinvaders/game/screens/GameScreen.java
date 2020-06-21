package com.spaceinvaders.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.spaceinvaders.game.SpaceInvaders;
import com.spaceinvaders.game.managers.Config;
import com.spaceinvaders.game.managers.EntityFactory;
import com.spaceinvaders.game.systems.BulletSystem;
import com.spaceinvaders.game.systems.InvaderSystem;
import com.spaceinvaders.game.systems.PlayerSystem;
import com.spaceinvaders.game.systems.RenderSystem;

public class GameScreen implements Screen {

    public SpaceInvaders game;
    public PlayerSystem playerSystem;
    public InvaderSystem invaderSystem;
    public RenderSystem renderSystem;
    public BulletSystem bulletSystem;

    private Engine engine;

    public GameScreen(SpaceInvaders game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        Config.GAME_OVER = false;
        engine = new Engine();
        engine.addSystem(playerSystem = new PlayerSystem(this));
        engine.addSystem(invaderSystem = new InvaderSystem(this));
        engine.addSystem(renderSystem = new RenderSystem(this));
        engine.addSystem(bulletSystem = new BulletSystem(this));

        // Load Player
        engine.addEntity(EntityFactory.createPlayer(425, 50));
        // Load invaders
        for(int i=0; i<4; i++) {
            for(int j=0;j<10;j++) {
                engine.addEntity(EntityFactory.createInvader(j*60 + 150, i*60 + 300));
            }
        }
    }

    @Override
    public void render(float delta) {
        if(Config.GAME_OVER) {
            game.currentScreen = new GameoverScreen(game);
        } else {
            engine.update(delta);
            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                game.currentScreen = new MenuScreen(game);
            }
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void show() { }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }

}
