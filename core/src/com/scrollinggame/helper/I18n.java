package com.scrollinggame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

public class I18n {

    public static final String TITLE = "app_name";

    private static I18NBundle strings;
    public static void init() {
    	FileHandle baseFileHandle = Gdx.files.internal("i18n/strings");
    	strings = I18NBundle.createBundle(baseFileHandle);
    }
    
    public static String t(String key) {
    	return strings.get(key);
    }
    
    public static String t(String key, Object... args) {
    	return strings.format(key, args);
    }
}
