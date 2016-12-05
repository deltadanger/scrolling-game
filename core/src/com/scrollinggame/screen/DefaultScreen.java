package com.scrollinggame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.scrollinggame.helper.AssetLoader;

public abstract class DefaultScreen implements Screen, InputProcessor {

	public static final Color BACKGROUND_COLOR = Color.valueOf("000000ff");
    
    protected SpriteBatch batcher;
    private OrthographicCamera cam;
    private GlyphLayout layout = new GlyphLayout();
    
    protected Rectangle view = new Rectangle();
    
    public DefaultScreen() {
    	
		Gdx.input.setInputProcessor(this);
		
		batcher = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        cam = new OrthographicCamera(2f * aspectRatio, 2f);
        cam.setToOrtho(true);
    }
    
    protected void initBatcher() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cam.update();
		batcher.setProjectionMatrix(cam.combined);
        batcher.begin();
        batcher.disableBlending();
        batcher.setColor(BACKGROUND_COLOR);
        batcher.draw(AssetLoader.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batcher.enableBlending();

    }

    protected void draw(TextureRegion texture, Rectangle bounds) {
		batcher.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
	}

    protected void drawLine(Vector2 p1, Vector2 p2, float thickness, Color colour) {
        Sprite sprite = new Sprite(AssetLoader.pixel);
        sprite.setOrigin(0, 0);
        sprite.setPosition(p1.x, p1.y);
        sprite.setScale(p1.dst(p2), thickness);
        sprite.rotate(p2.cpy().sub(p1).angle());
        sprite.setColor(colour);
        sprite.draw(batcher);
    }

    /**
     * Draw text with given coordinates as center
     */
    protected void drawText(BitmapFont font, String text, float x, float y) {
    	drawText(font, text, (int)x, (int)y);
    }

    /**
     * Draw text with given coordinates as center
     */
    protected void drawText(BitmapFont font, String text, int x, int y) {
        layout.setText(font, text);
        x = (int) (x - layout.width / 2);
        y = (int) (y - layout.height / 2);
        font.draw(batcher, text, x, y);
    }

	@Override
	public void show() {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
	@Override
	public boolean keyDown(int keycode) {return false;}
	@Override
	public boolean keyUp(int keycode) {return false;}
	@Override
	public boolean keyTyped(char character) {return false;}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {return false;}
	@Override
	public boolean scrolled(int amount) {return false;}
}
