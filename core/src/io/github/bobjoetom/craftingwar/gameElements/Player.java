package io.github.bobjoetom.craftingwar.gameElements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import io.github.bobjoetom.craftingwar.Game;
import io.github.bobjoetom.craftingwar.cards.Card;
import io.github.bobjoetom.craftingwar.gameElements.buttons.Button;
import io.github.bobjoetom.craftingwar.gameElements.buttons.EndCombineButton;

/**
 * Created by Brenden on 8/7/2016.
 */
public class Player {

    ////////////////////
    //GAME LOGIC////////
    ////////////////////
    private boolean turn;
    private boolean taking;
    private boolean drawing;
    private boolean combining;
    private int Score;
    public int getScore() {return Score;}

    public void setScore(int score) {Score = score;}

    public ArrayList<Card> getCards() {return cards;}

    public void setCards(ArrayList<Card> cards) {this.cards = cards;}

    public ArrayList<Card> getCrafter() {return crafter;}

    public void setCrafter(ArrayList<Card> crafter) {this.crafter = crafter;}

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

    public void takeTurn(){}

    public void render(SpriteBatch sb) {//RENDER===RENDER===RENDER
        btnEndCombine.render(sb);
        renderCards(sb);
        //TODO RENDER SCORE
    }
    public void update(float delta){
       updateCards();
    }

    ////////////////////
    //END GAME LOGIC////
    ////////////////////

    ////////////////////
    //CARDS/////////////
    ////////////////////
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Card> crafter = new ArrayList<Card>();

    public void insertCard(Card card){
        for (int i=0; i< cards.size();i++){
            if(card.getTruePosistion().x > cards.get(i).getTruePosistion().x){
                cards.add(i+1, card);
            }
        }
    }

    public void drawCard(Card card){
        cards.add(card);
    }

    public void renderCards(SpriteBatch sb){
        for(int i = 0; i < cards.size(); i++){cards.get(i).render(sb);}//Render CARDS
        for(int i = 0; i < crafter.size(); i++){crafter.get(i).render(sb);}//RENDER CRAFTER
    }

    public void dumpToCards(){
        if(crafter.size()==2)drawCard(crafter.remove(1));
        if(crafter.size()==1)drawCard(crafter.remove(0));

    }

    public void combine(){
        drawCard(crafter.get(0).combine(crafter.get(1)));
    }

    public Card getCard(int i){
        return cards.get(i);
    }
    public Card getCrafter(int i){ return crafter.get(i);}
    public void insertCrafter(Card card){
        if(crafter.size()<=1){
            crafter.add(card);
        }
    }
    public Card removeCard(int i){
        //CONDENCEING ACTION
        Card result = cards.remove(i);//Remove should move everything back to the left
        return result;

    }
    public Card removeCrafter(int i){ return crafter.remove(i);}
    public int getCardsSize(){ return cards.size(); }
    public int getCrafterSize(){ return crafter.size();}


    public void updateCards(){
        setTheta();
        setCardRenderPosistion();
        setCardsPosistion();
        setCrafterRenderPosition();
    }

    public void setTheta(){
        float change;
        float arc = D2 - D1;
        if(cards.size()%2==0){
            change = arc/cards.size();
        }else{
            change = arc/(cards.size()+1);
        }

        for(int i = 0; i < cards.size();i++){
            if(cards.size()%2==0){
                cards.get(i).setTheta(D1+(change/2) + (i*change));
            }else{
                cards.get(i).setTheta(D1+change + (i*change));
            }
        }
    }

    public void setCardsPosistion() {
        for (int i = 0; i < cards.size(); i++){
            cards.get(i).setTruePosistion(new Vector2((float) ((Math.cos(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.x), (float) ((Math.sin(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.y)));
        }
    }


    private final float speed = 3f;

    public void setCardRenderPosistion(){//TODO Fuck this, make rendering easier
        for(int i = 0; i < cards.size(); i++){
            if(CENTER.y > Game.HEIGHT/2){//
                if (cards.get(i).getRenderPosistion().x < cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y < ((float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2))) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, speed));
                    }else if(cards.get(i).getRenderPosistion().y > ((float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2))) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, -speed));
                    }else{
                        cards.get(i).setRender(new Vector2(speed, (float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x + 1) - CENTER.x, 2))) + CENTER.y)));

                    }
                }
                if (cards.get(i).getRenderPosistion().x > cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y < ((float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2))) + CENTER.y))){
                        //if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2)) + CENTER.y))){ ???-1 how does it affect
                        cards.get(i).translateRender(new Vector2(-speed, speed));
                    }else if(cards.get(i).getRenderPosistion().y > ((float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2))) + CENTER.y))){
                        //if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2)) + CENTER.y))){ ???-1 how does it affect
                        cards.get(i).translateRender(new Vector2(-speed, -speed));
                    }else{
                        cards.get(i).setRender(new Vector2(-speed, (float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2))) + CENTER.y)));

                    }
                }
                if(crafter.size()==1) {
                    if (crafter.get(0).getRenderPosistion().x < 220)
                        crafter.get(0).translateRender(new Vector2(2, 0));
                    if (crafter.get(0).getRenderPosistion().x > 220)
                        crafter.get(0).translateRender(new Vector2(-2, 0));
                    if (crafter.get(0).getRenderPosistion().y < 760)
                        crafter.get(0).translateRender(new Vector2(0, 2));
                    if (crafter.get(0).getRenderPosistion().y > 760)
                        crafter.get(0).translateRender(new Vector2(0, -2));
                }
                if(crafter.size()==2) {
                    if (crafter.get(1).getRenderPosistion().x < 260)
                        crafter.get(1).translateRender(new Vector2(2, 0));
                    if (crafter.get(1).getRenderPosistion().x > 260)
                        crafter.get(1).translateRender(new Vector2(-2, 0));
                    if (crafter.get(1).getRenderPosistion().y < 760)
                        crafter.get(1).translateRender(new Vector2(0, 2));
                    if (crafter.get(1).getRenderPosistion().y > 760)
                        crafter.get(1).translateRender(new Vector2(0, -2));
                }
            }else {
                if (cards.get(i).getRenderPosistion().x < cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed,-speed));
                    }else if(cards.get(i).getRenderPosistion().y < ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, speed));
                    }else{
                        cards.get(i).setRender(new Vector2(speed, (float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x + 1) - CENTER.x, 2)) + CENTER.y)));

                    }
                }
                if (cards.get(i).getRenderPosistion().x > cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        //if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2)) + CENTER.y))){ ???-1 how does it affect
                        cards.get(i).translateRender(new Vector2(-speed,-speed));
                    }else if(cards.get(i).getRenderPosistion().y < ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        //if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2)) + CENTER.y))){ ???-1 how does it affect
                        cards.get(i).translateRender(new Vector2(-speed, speed));
                    }else{
                        cards.get(i).setRender(new Vector2(-speed, (float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2)) + CENTER.y)));

                    }
                }
                if(crafter.size()==1) {
                    if (crafter.get(0).getRenderPosistion().x < 220)
                        crafter.get(0).translateRender(new Vector2(2, 0));
                    if (crafter.get(0).getRenderPosistion().x > 220)
                        crafter.get(0).translateRender(new Vector2(-2, 0));
                    if (crafter.get(0).getRenderPosistion().y < 100)
                        crafter.get(0).translateRender(new Vector2(0, 2));
                    if (crafter.get(0).getRenderPosistion().y > 100)
                        crafter.get(0).translateRender(new Vector2(0, -2));
                }
                if(crafter.size()==2) {
                    if (crafter.get(1).getRenderPosistion().x < 260)
                        crafter.get(1).translateRender(new Vector2(2, 0));
                    if (crafter.get(1).getRenderPosistion().x > 260)
                        crafter.get(1).translateRender(new Vector2(-2, 0));
                    if (crafter.get(1).getRenderPosistion().y < 100)
                        crafter.get(1).translateRender(new Vector2(0, 2));
                    if (crafter.get(1).getRenderPosistion().y > 100)
                        crafter.get(1).translateRender(new Vector2(0, -2));
                }
            }
            //fixBounds(crafter.get(i));
        }
    }

    public void setCrafterRenderPosition(){//TODO is going to be replaced with a fre emovement if inside bounds box to craft not forced possitioning

    }

    public boolean ifAnyCombinable(){
        if(cards.size()<2){//GOOD
            return false;
        }else{
            for (int e1 = 0; e1 < cards.size(); e1++){
                for(int e2=e1+1; e2< cards.size(); e2++){
                    if(cards.get(e1).isCombinable(cards.get(e2))){
                        return true;
                    }
                }
            }
            return false;
        }
    }


    ////////////////////
    //END CARDS/////////
    ////////////////////

    ////////////////////
    //BUTTONS///////////
    ////////////////////
    Button btnEndCombine = new EndCombineButton();
    int btnEndCombineWIDTH = 400;//TODO CHANGE TO PORPOTRIONAL TO SCREEN
    int btnEndCombineHEIGHT = 200;//TODO CHANGE TO PORPOTIONAL TO SCREEN
    Vector2 btnEndCombinePosistion;

    public Vector2 getBtnEndCombinePosistion() {
        return btnEndCombine.getRenderPosistion();
    }

    public void setBtnEndCombinePosistion(Vector2 btnEndCombinePosistion) {btnEndCombine.setRenderPosistion(btnEndCombinePosistion);}
    public Button getBtnEndCombine() {
        return btnEndCombine;
    }
    public int getBtnEndCombineWIDTH() {
        return btnEndCombineWIDTH;
    }
    public int getBtnEndCombineHEIGHT() {
        return btnEndCombineHEIGHT;
    }


    ////////////////////
    //END BUTTONS///////
    ////////////////////

    ////////////////////
    //CIRCLE////////////
    ////////////////////

    private final int RADIUS = (Game.WIDTH/2)-50;
    private Vector2 CENTER;
    private float D1;
    private float D2;

    public void setCENTER(Vector2 CENTER) {
        this.CENTER = CENTER;
    }

    public Vector2 getCENTER() {
        return CENTER;
    }

    public float getD1() {
        return D1;
    }

    public void setD1(float d1) {
        D1 = d1;
    }

    public float getD2() {
        return D2;
    }

    public void setD2(float d2) {
        D2 = d2;
    }
    ////////////////////
    //END CIRCLE////////
    ////////////////////
    ////////////////////
    //CONSTRUCTOR///////
    ////////////////////
    public void Player(){
        setScore(0);
        setCombining(false);
        setTaking(false);
        setTurn(false);
        setDrawing(false);
        btnEndCombine.setWIDTH(btnEndCombineWIDTH);
        btnEndCombine.setHEIGHT(btnEndCombineHEIGHT);
    }
    ////////////////////
    //END CONSTRUCTOR///
    ////////////////////
}
