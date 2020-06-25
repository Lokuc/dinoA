package com.severgames.dino.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.severgames.dino.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled=true;
		config.title="SeverGames";
		config.width=1200;
		config.height=600;
		config.samples=16;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
