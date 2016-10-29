package io.github.bobjoetom.craftingwar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.bobjoetom.craftingwar.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Game.WIDTH;
		config.height=Game.HEIGHT;
		config.title=Game.TITLE;
		new LwjglApplication(new Game(), config);
	}
}
