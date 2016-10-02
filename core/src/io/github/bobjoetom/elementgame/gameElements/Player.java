package io.github.bobjoetom.elementgame.gameElements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.bobjoetom.elementgame.Game;

/**
 * Created by Brenden on 8/7/2016.
 */
public class Player {

    private boolean turn;
    private boolean taking;
    private boolean drawing;
    private boolean combining;

    Button btnEndCombine = new Button();
    int btnEndCombineWIDTH = 400;
    int btnEndCombineHEIGHT = 200;
    Vector2 btnEndCombinePosistion = new Vector2((Game.WIDTH/2)- btnEndCombineWIDTH,(Game.HEIGHT/2)- btnEndCombineHEIGHT);

    ////////////////////
    //CIRCLE////////////
    ////////////////////
    private final int RADIUS = (Game.WIDTH/2)-50;
    private final Vector2 CENTER = new Vector2();
    private float D1;
    private float D2;

    public void Player(){
        btnEndCombine.setWIDTH(btnEndCombineWIDTH);
        btnEndCombine.setHEIGHT(btnEndCombineHEIGHT);
        btnEndCombine.setRenderPosistion(btnEndCombinePosistion);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isTaking() {
        return taking;
    }

    public void setTaking(boolean taking) {
        this.taking = taking;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public boolean isCombining() {
        return combining;
    }

    public void setCombining(boolean combining) {
        this.combining = combining;
    }

    public void render(SpriteBatch sb){
        btnEndCombine.render(sb);
    }

    public void update(float delta){

    }

    public void takeTurn(){

    }
}
