package com.scrollinggame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
public class AssetLoader {

    private static Texture mainTexture;
    
    public static TextureRegion background;
    public static TextureRegion pixel;
    
    public static FreeTypeFontGenerator generator;
    public static BitmapFont mainFont;
    
    public static void load() {
    	loadImages();
    	loadFonts();
    }
    
    private static void loadImages() {
        
    	mainTexture = new Texture(Gdx.files.internal("data/textures.png"));
    	mainTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        background = new TextureRegion(mainTexture, 0, 0, 1, 1);
        pixel = new TextureRegion(mainTexture, 0, 0, 1, 1);
    }
    
    private static void loadFonts() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("data/MachineScript.ttf"));
        
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 50;
        parameter.flip = true;
        mainFont = generator.generateFont(parameter);
    }
    
    public static void dispose() {
        mainTexture.dispose();
        generator.dispose();
        mainFont.dispose();
    }
}
