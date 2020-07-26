package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Dj {

    private Sound[] sounds; //fon, end , coin
    private Music[] music;
    private boolean musicB;
    private boolean soundB;

    public Dj(){
        sounds=new Sound[3];
        music=new Music[2];

    }

    void load(){
        //sounds[0] = Gdx.audio.newSound(Gdx.files.internal("sound/fon.mp3"));
        sounds[1] = Gdx.audio.newSound(Gdx.files.internal("sound/end.mp3"));
        sounds[2] = Gdx.audio.newSound(Gdx.files.internal("sound/coin.mp3"));
        music[0]= Gdx.audio.newMusic(Gdx.files.internal("sound/fon.mp3"));
        music[1]= Gdx.audio.newMusic(Gdx.files.internal("sound/fonM.mp3"));
        music[1].setVolume(0.5f);
        music[0].setVolume(0.7f);
    }

    void playFonM(){
        if(musicB) {
            music[1].setLooping(true);
            music[1].play();
        }
    }
    void stopFonM(){
        music[1].stop();
    }
    void playFon(){
        if(musicB) {
            music[0].setLooping(true);
            music[0].play();
        }
    }
    void stopFon(){
        music[0].stop();
    }

    public void playCoin(){
        if(soundB) {
            sounds[2].play(0.7f);
        }
    }
    void playEnd(){
        if(soundB) {
            sounds[1].play(0.7f);
        }
    }



    void dispose() {
        //sounds[0].dispose();
        sounds[1].dispose();
        sounds[2].dispose();
        music[0].dispose();
        music[1].dispose();
    }

    void setSettings(boolean music, boolean sound) {
        musicB=music;
        soundB=sound;
    }
}
