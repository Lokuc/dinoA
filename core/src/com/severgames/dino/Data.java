package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

public class Data {

    private Preferences preferences;

    public Data() {
        if(preferences==null){
            preferences = Gdx.app.getPreferences("Preference");
        }
        saveMoney(getMoney());
    }

    public int getMoney(){
        return preferences.getInteger("money");
    }

    public void saveMoney(int i){
        preferences.putInteger("money",i);
        preferences.flush();
    }
    public void saveSettings(boolean sound, boolean music){
        preferences.putBoolean("sound",sound);
        preferences.putBoolean("music",music);
        preferences.flush();
    }
    public boolean[] getSettings(){
        return new boolean[]{preferences.getBoolean("sound"),preferences.getBoolean("music")};
    }
     public void log(String log){
        new FileHandle("log.txt").writeString(log,true);
     }

}
