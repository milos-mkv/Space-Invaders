package com.spaceinvaders.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.spaceinvaders.game.managers.Assets;
import com.spaceinvaders.game.managers.Config;
import com.spaceinvaders.game.screens.GameoverScreen;
import com.spaceinvaders.game.screens.LoadingScreen;

public class SpaceInvaders extends ApplicationAdapter {

	public Screen currentScreen;

	@Override
	public void create () {
		currentScreen = new LoadingScreen(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(51f/255f, 51f/255f, 86f/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Config.ELAPSED += Gdx.graphics.getDeltaTime();
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		currentScreen.dispose();
		Assets.dispose();
	}
}
