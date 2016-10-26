package com.scrollinggame;

import helper.AssetLoader;
import helper.I18n;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.scrollinggame.screen.MainScreen;

public class MainGame extends Game {
	
    public enum GameState {
    	MAIN,
    	OPTION,
    	CONFIG
    }
    
	private static MainGame instance = null;
	public static MainGame getInstance() {
		if (instance == null) {
			instance = new MainGame();
		}
		return instance;
	}

    public MainGame() {
    	if (instance != null) {
    		throw new RuntimeException("Manual instantiation of MainGame");
    	}

    }
	
	@Override
    public void create() {
    	Gdx.app.setLogLevel(Application.LOG_INFO);
    	
        AssetLoader.load();
        I18n.init();
        
        updateState(GameState.MAIN);
    }

	public void updateState(GameState newState) {
		updateState(newState, null);
	}

	public void updateState(GameState newState, Object data) {
		updateState(newState, data, true);
	}
	
	public void updateState(GameState newState, Object data, boolean updateHistory) {
		
        switch(newState) {
		case MAIN:
			setScreen(new MainScreen());
			break;
		case CONFIG:
			break;
		case OPTION:
			break;
        }
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
    
	@Override
	public void pause () {
		super.pause();
	}

	@Override
	public void resume () {
		super.resume();
	}
}
