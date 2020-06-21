package com.spaceinvaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceinvaders.game.SpaceInvaders;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;

public class MenuScreen implements Screen {

    private SpaceInvaders game;
    private SpriteBatch spriteBatch;

    public MenuScreen(SpaceInvaders game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        spriteBatch = new SpriteBatch();
        if(!Assets.music.isPlaying() && !Assets.hardMusic.isPlaying() && !Config.MUTE) {
            Assets.music.play();
        }
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.setColor(1, 1, 1, 1f);
        if(Config.HARD_MODE_ON) {
            spriteBatch.draw(Assets.lossScreenBackgroundAnimation.getKeyFrame(Config.ELAPSED), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        } else {
            spriteBatch.draw(Assets.menuBackgroundAnimation.getKeyFrame(Config.ELAPSED), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        }
        Assets.bitmapFontForMenuTitle.draw(spriteBatch, "Space", Config.SCREEN_WIDTH / 2 - 150,  Config.SCREEN_HEIGHT / 2 + 120);
        Assets.bitmapFontForMenuTitle.draw(spriteBatch, "Invaders", Config.SCREEN_WIDTH / 2 - 240,  Config.SCREEN_HEIGHT / 2);
        spriteBatch.setColor(1, 1, 1, 0.5f);
        if(Config.HARD_MODE_ON) {
            spriteBatch.draw(Assets.menuEvilAnimeGirl, 200, 0, 500, 500);
        } else {
            spriteBatch.draw(Assets.menuAnimeGirl, 200, 0, 500, 500);
        }
        Assets.bitmapFontForMenuSubTitle.draw(spriteBatch, "Press space to start game", Config.SCREEN_WIDTH / 2 - 200,  Config.SCREEN_HEIGHT / 2 - 140);
        Assets.bitmapFontForSmallInfo.draw(spriteBatch, "Press H for Hard Mode", Config.SCREEN_WIDTH/2 - 100, 30);
        Assets.bitmapFontForSmallInfo.draw(spriteBatch, "Press M to Mute Music", Config.SCREEN_WIDTH/2 - 100, 50);

        spriteBatch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.currentScreen = new InstructionScreen(game);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            Config.HARD_MODE_ON = !Config.HARD_MODE_ON;
            if(Config.HARD_MODE_ON) {
                if(!Config.MUTE) {
                    Assets.music.stop();
                    Assets.hardMusic.play();
                }
                Config.INVADER_SPEED = 400;
                Config.INVADER_SHOOT_SPEED = 0.1f;
            } else {
                if(!Config.MUTE) {
                    Assets.hardMusic.stop();
                    Assets.music.play();
                }
                Config.INVADER_SPEED = 200;
                Config.INVADER_SHOOT_SPEED = 0.3f;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            Config.MUTE = !Config.MUTE;
            if(Config.MUTE) {
                Assets.hardMusic.stop();
                Assets.music.stop();
            } else {
                if(Config.HARD_MODE_ON) {
                    Assets.hardMusic.play();
                } else {
                    Assets.music.play();
                }
            }
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        /* Not needed */
    }

    @Override
    public void show() {
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
