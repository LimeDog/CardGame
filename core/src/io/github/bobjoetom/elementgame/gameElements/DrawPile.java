package io.github.bobjoetom.elementgame.gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import io.github.bobjoetom.elementgame.Game;
import io.github.bobjoetom.elementgame.cards.Card;
import io.github.bobjoetom.elementgame.cards.Earth;
import io.github.bobjoetom.elementgame.cards.Fire;
import io.github.bobjoetom.elementgame.cards.Smeltery;
import io.github.bobjoetom.elementgame.cards.Water;

/**
 * Created by Brenden on 4/12/2016.
 */
//**COULD EXTEND AS ANOTHER PLAYER**//
public class DrawPile {

    Random rando = new Random();

    ArrayList<Card> cards = new ArrayList<Card>();
    private Card selected;

    private final int RADIUS = (Game.WIDTH/6);
    private final Vector2 CENTER = new Vector2(Game.WIDTH/2,Game.HEIGHT/2);
    private float D1 = 0;
    private float D2 = 360;

    ////////////////////
    //CONSTRUCTOR///////
    ////////////////////
    public DrawPile(){
        for(int i = 0; i < 3; i++){
            cards.add(i, generateNewCard());
        }
        refresh();
    }
    ////////////////////
    //UTILITIES/////////
    ////////////////////
    public int nextIndex(){
        for(int i = 0; i < 3; i++){
            if(cards.get(i)==null){
                return i;
            }
        }
        System.out.println("You fucked up");
        return -1;
    }

    public int getSize(){
        return cards.size();
    }

    public void addCard(int i, Card e){
        cards.add(i, e);
        refresh();
    }

    private void condence(int i){
        for(int x = i+1; x < cards.size(); i++){
            Card buffer = cards.get(x);
            cards.remove(x);
            cards.add(x-1, buffer);
        }
    }

    /*
    public void removeCard(int i){
        if( i <cards.size()){
            cards.remove(i);
            refresh();
        }
    }
    */
    ////////////////////
    //SELECTION/////////
    ////////////////////
    public Card transferCard(int i){
        Card result = cards.get(i);
        cards.remove(i);
        condence(i);
        refresh();
        return result;
    }

    public Card transferCard(){
        Card result = getSelected();
        cards.remove(getSelected());
        //condence();
        refresh();
        return result;
    }

    public Card getSelected() {
        //REFRESH THE DRAW PILE
        //TODO FIX PLAYERS TURN IN GAME STATE
        return selected;
    }

    public void setSelected(Card selected) {
        this.selected = selected;
    }

    public void setSelectedNull(){
        this.selected = null;
    }
    ////////////////////
    //COORDINATES///////
    ////////////////////
    //TODO FIX HOW THE YARE REPLACED AND HOW THETAS CORESPOND TO REPLACE THE ONE REMOVED
    public void refresh(){
        for(int i = 0; i < 3; i ++){
            if(cards.size()<3) {
                int r = rando.nextInt(3);
                if (r == 0) {
                    cards.add(i, new Fire());
                } else if (r == 1) {
                    cards.add(i, new Water());
                } else if (r == 2) {
                    cards.add(i, new Earth());
                }//else if (r == 3){
                //    cards.add(i, new Smeltery());
                //}
            }
        }

        setTheta();
        setTruePosistion();
        setRenderPosistion();
    }

    private Card generateNewCard(){
        int r = rando.nextInt(3);
        if(r==0){
            return  new Water();
        }else if(r==1){
            return new Fire();
        }else if(r==2){
           return new Water();
        //else if (r == 3){
           //return new Smeltery();
        }else return new Card();

    }

    public void setTheta(){
        float change;
        float arc = D2 - D1;
        change = arc/3;
        for(int i = 0; i < cards.size();i++){
            cards.get(i).setTheta((i*change));
        }
    }

    public void setTruePosistion(){
        for(int i = 0; i < cards.size(); i++)
            cards.get(i).setTruePosistion(new Vector2((float) ((Math.cos(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.x), (float) ((Math.sin(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.y)));
    }

    public void setRenderPosistion(){
        for(int i = 0; i < cards.size(); i++){
            cards.get(i).setRenderPosistion(cards.get(i).getTruePosistion());
        }
    }
    ////////////////////
    // UPDATE & RENDER///
    ////////////////////
    public void update(){
        refresh();
        if(Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
            for (int i = 0; i < cards.size(); i++) {
                if(cards.get(i).checkIfClicked(Gdx.input.getX(), Gdx.input.getY())){
                    setSelected(cards.get(i));
                }
            }
        }
    }

    public void render(SpriteBatch sb){
        //refresh();
        for(int i = 0; i < cards.size(); i++){
            cards.get(i).render(sb);
        }
    }
}