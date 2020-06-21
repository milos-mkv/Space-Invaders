package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceinvaders.game.SpaceInvaders;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;

public class InstructionScreen implements Screen {

    private SpaceInvaders game;
    private SpriteBatch spriteBatch;

    public InstructionScreen(SpaceInvaders game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        if(Config.HARD_MODE_ON) {
            Gdx.gl.glClearColor(59f/255f, 50f/255f, 81f/255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        spriteBatch.begin();
        Assets.bitmapFontForEndScreenGameResult.setColor(1, 1, 1, 1);
        Assets.bitmapFontForEndScreenGameResult.draw(spriteBatch, "Controls", Config.SCREEN_WIDTH / 2 - 170 , Config.SCREEN_HEIGHT / 2 + 200 );
        spriteBatch.draw(Assets.keyboardTexture, Config.SCREEN_WIDTH / 2 - 340, Config.SCREEN_HEIGHT /2 -130, Assets.keyboardTexture.getWidth() / 1.2f, Assets.keyboardTexture.getHeight() / 1.2f );
        Assets.bitmapFontForEndScreenMoreInfo.draw(spriteBatch, "Press space to start game", Config.SCREEN_WIDTH / 2 - 200, Config.SCREEN_HEIGHT / 2 -150 );
        spriteBatch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.currentScreen = new GameScreen(game);
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
