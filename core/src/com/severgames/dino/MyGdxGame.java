package com.severgames.dino;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends Game {

	public static MyGdxGame myGdxGame;
	static Frame frame;
	private static Menu menu;
	private static Settings settings;



	@Override
	public void dispose() {

	}

	@Override
	public void create() {
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode()); TODO
		myGdxGame=this;
		menu = new Menu();
		settings=new Settings();
		setScreen(new loadScreen());
	}


	public void setFrame() {
		setScreen(frame);
	}

	public void setSetting(){
		setScreen(settings);
	}

	public void setMenu(){
		setScreen(menu);
	}
}
