package io.github.bobjoetom.craftingwar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.bobjoetom.craftingwar.state.GameStateManager;
import io.github.bobjoetom.craftingwar.state.UIState;

public class Game extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static int consoleCounter;

	public static final String TITLE = "Card Game";
	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new UIState(gsm));
		Gdx.gl.glClearColor(120f,200f,245f,1f);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	public static void consolePrint(int counts, String string){
		consoleCounter++;
		if(consoleCounter%counts==0){
			System.out.println(string);
			consoleCounter=0;
		}
	}
}
