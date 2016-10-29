package io.github.bobjoetom.craftingwar.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.bobjoetom.craftingwar.Game;

/**
 * Created by Brenden on 4/10/2016.
 */
public class UIState extends State {
    //TODO REPLACE WITH PROPPER BUTOONS
    private Texture background;
    private Texture playBtn;

    public UIState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("BackGround.png");
        playBtn = new Texture("Play-Button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new GameState(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, Game.WIDTH, Game.HEIGHT);
        sb.draw(playBtn,(Game.WIDTH/2)-200, Game.HEIGHT/2, 400, 200);
        sb.end();
    }

    public void dispose(){
        background.dispose();
        playBtn.dispose();
    }
}
