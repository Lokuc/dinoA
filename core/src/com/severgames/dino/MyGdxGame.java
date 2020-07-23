package com.severgames.dino;



import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	public static MyGdxGame myGdxGame;
	static Frame frame;
	private static Menu menu;
	private static Settings settings;
	public static Dj dj;
	private static Shop shop;



	@Override
	public void dispose() {
		menu.dispose();
		frame.dispose();
		settings.dispose();
	}

	@Override
	public void create() {
		dj= new Dj();
		dj.load();
		dj.setSettings(new Data().getSettings()[1],new Data().getSettings()[0]);
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode()); TODO
		myGdxGame=this;
		setScreen(new loadScreen());
	}

	void init(){
		menu = new Menu();
		settings=new Settings();
		shop = new Shop();
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
	public void setMenu(int chose){
		dj.playFonM();
		setScreen(menu);
		frame.setChose(chose);
	}

    public void setShop() {
		setScreen(shop);
    }
}
