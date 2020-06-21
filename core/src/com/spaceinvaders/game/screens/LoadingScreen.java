package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.spaceinvaders.game.SpaceInvaders;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;

public class LoadingScreen implements Screen {

    private SpaceInvaders game;
    private float timer;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFontForLoadingScreen;
    boolean done =false;

    public LoadingScreen(SpaceInvaders game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        timer = 0;
        spriteBatch = new SpriteBatch();

        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/MachineStd-Bold.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterForMenuTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameterForMenuTitle.size = 100;
        bitmapFontForLoadingScreen = freeTypeFontGenerator.generateFont(freeTypeFontParameterForMenuTitle);
        bitmapFontForLoadingScreen.setColor(1, 1, 1, 0.8f);
    }

    @Override
    public void render(float delta) {
        if((timer += delta) > 1.0f) {
            Assets.loadAssets();
            done = true;
        }

        spriteBatch.begin();
        bitmapFontForLoadingScreen.draw(spriteBatch, "Loading", Config.SCREEN_WIDTH / 2 - 140, Config.SCREEN_HEIGHT / 2 + 50);
        spriteBatch.end();

        if(done) {
            game.currentScreen = new MenuScreen(game);
            dispose();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        bitmapFontForLoadingScreen.dispose();
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
    @Override
    public void show() {
        /* Not needed */
    }
}
