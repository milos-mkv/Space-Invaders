package com.spaceinvaders.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.project.managers.GifDecoder;

public abstract class Assets  {

    public static Animation<TextureRegion> menuBackgroundAnimation;
    public static Animation<TextureRegion> lossScreenBackgroundAnimation;
    public static Animation<TextureRegion> hardAnimation;

    public static Texture menuAnimeGirl;
    public static Texture menuEvilAnimeGirl;
    public static Texture lifeTexture;
    public static Texture keyboardTexture;
    public static Texture easyBackground;
    public static Texture hardBackground;

    public static BitmapFont bitmapFontForMenuTitle;
    public static BitmapFont bitmapFontForMenuSubTitle;
    public static BitmapFont bitmapFontForEndScreenGameResult;
    public static BitmapFont bitmapFontForEndScreenMoreInfo;
    public static BitmapFont bitmapFontForSmallInfo;

    public static Sound shootSound;
    public static Sound enemyShootSound;
    public static Sound enemyHit;
    public static Music music;
    public static Music hardMusic;

    public static void loadAssets() {
        menuBackgroundAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("core/assets/source.gif").read());
        lossScreenBackgroundAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("core/assets/loss.gif").read());
        hardAnimation= GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("core/assets/hard.gif").read());
        menuAnimeGirl = new Texture("core/assets/pngbarn.png");
        lifeTexture = new Texture("core/assets/life.png");
        keyboardTexture = new Texture("core/assets/keyboard.png");
        menuEvilAnimeGirl = new Texture("core/assets/ag2.png");
        easyBackground = new Texture("core/assets/wall.png");
        hardBackground = new Texture("core/assets/evil.jpg");

        shootSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/shoot.wav"));
        enemyShootSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/enemyshoot.wav"));
        enemyHit= Gdx.audio.newSound(Gdx.files.internal("core/assets/hit.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/easy.mp3"));
        hardMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/hard1.mp3"));
        music.setLooping(true);
        hardMusic.setLooping(true);
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/MachineStd-Bold.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterForMenuTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameterForMenuTitle.size = 150;
        bitmapFontForMenuTitle = freeTypeFontGenerator.generateFont(freeTypeFontParameterForMenuTitle);
        bitmapFontForMenuTitle.setColor(1, 1, 1, 0.8f);

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterForMenuSubTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameterForMenuSubTitle.size = 40;

        bitmapFontForMenuSubTitle = freeTypeFontGenerator.generateFont(freeTypeFontParameterForMenuSubTitle);
        bitmapFontForMenuSubTitle.setColor(1, 1, 1, 0.8f);

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterForScreenGameResult = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameterForScreenGameResult.size = 100;
        bitmapFontForEndScreenGameResult = freeTypeFontGenerator.generateFont(freeTypeFontParameterForScreenGameResult);
        bitmapFontForEndScreenGameResult.setColor(1, 1, 1, 0.8f);

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterForMoreInfo = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameterForMoreInfo.size = 40;
        bitmapFontForEndScreenMoreInfo = freeTypeFontGenerator.generateFont(freeTypeFontParameterForMoreInfo);
        bitmapFontForEndScreenMoreInfo.setColor(1, 1, 1, 0.8f);

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterForSmallInfo = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameterForSmallInfo.size = 20;
        bitmapFontForSmallInfo = freeTypeFontGenerator.generateFont(freeTypeFontParameterForSmallInfo);
        bitmapFontForSmallInfo.setColor(1, 1, 1, 1f);

        freeTypeFontGenerator.dispose();
    }

    public static void dispose() {
        menuAnimeGirl.dispose();
        bitmapFontForMenuTitle.dispose();
        bitmapFontForMenuSubTitle.dispose();
        bitmapFontForEndScreenGameResult.dispose();
        bitmapFontForEndScreenMoreInfo.dispose();
    }

}
