package io.github.bobjoetom.elementgame.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import io.github.bobjoetom.elementgame.Game;
import io.github.bobjoetom.elementgame.gameElements.Button;
import io.github.bobjoetom.elementgame.gameElements.DrawPile;
import io.github.bobjoetom.elementgame.gameElements.Player;

/**
 * Created by Brenden on 8/7/2016.
 */
public class GameState extends State{

    Player currentPlayer;//Object Pointer of cutternt players turn
    Player player1 = new Player();
    Player player2 = new Player();


    DrawPile drawPile = new DrawPile();


    public GameState(GameStateManager gsm) {
        super(gsm);
        currentPlayer = player1;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        if(currentPlayer.isTurn()==true){
            currentPlayer.takeTurn();
        }else{nextTurn();}
    }

    public void nextTurn(){
        if(player1.isTurn()==false){
            currentPlayer=player2;
        }else currentPlayer=player1;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        drawPile.render(sb);
        player1.render(sb);
        player2.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
