package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceinvaders.game.SpaceInvaders;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;

public class GameoverScreen implements Screen {

    private SpaceInvaders game;
    private SpriteBatch spriteBatch;
    private float timer = 0;

    public GameoverScreen(SpaceInvaders game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        if(Config.GAME_WIN) {
            Assets.bitmapFontForEndScreenGameResult.setColor(0, 1, 0, 0.8f);
            spriteBatch.draw(Assets.menuBackgroundAnimation.getKeyFrame(Config.ELAPSED), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        } else {
            Assets.bitmapFontForEndScreenGameResult.setColor(1, 0, 0, 0.8f);
            spriteBatch.draw(Assets.lossScreenBackgroundAnimation.getKeyFrame(Config.ELAPSED), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        }
        Assets.bitmapFontForEndScreenGameResult.draw(spriteBatch, "You " + (Config.GAME_WIN ? "WON" : "LOST"), Config.SCREEN_WIDTH / 2 - 160, Config.SCREEN_HEIGHT / 2 + 50);
        Assets.bitmapFontForEndScreenMoreInfo.draw(spriteBatch, "Press space to return to main menu", Config.SCREEN_WIDTH / 2 - 270, Config.SCREEN_HEIGHT / 2 - 50);
        spriteBatch.end();

        if((timer += delta) > 1) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                dispose();
                game.currentScreen = new MenuScreen(game);
            }
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void show() {
        /* Not needed */
    }

    @Override
    public void resize(int width, int height) {
        /* Not needed */
    }

    @Override
    public void pause() {
        /* Not needed */
    }

    @Override
    public void resume() {
        /* Not needed */
    }

    @Override
    public void hide() {
        /* Not needed */
    }
}
