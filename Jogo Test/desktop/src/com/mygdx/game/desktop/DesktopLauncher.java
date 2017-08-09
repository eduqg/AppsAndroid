package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.util.Constantes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constantes.GAME_HEIGHT;
		config.width = Constantes.GAME_WHIDTH;
		config.title = Constantes.GAME_TITULO;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
