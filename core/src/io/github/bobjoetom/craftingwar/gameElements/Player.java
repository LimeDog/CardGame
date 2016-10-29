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
        drawCard(crafter.get(0));
        drawCard(crafter.get(1));
    }

    public void combine(){
        drawCard(crafter.get(0).combine(crafter.get(1)));
    }

    public Card getCard(int i){
        return cards.get(i);
    }
    public Card getCrafter(int i){ return crafter.get(i);}
    public Card removeCard(int i){
        return cards.remove(i);
    }
    public int getCardsSize(){ return cards.size(); }
    public int getCrafterSize(){ return crafter.size();}


    public void updateCards(){
        setTheta();
        setCardRenderPosistion();
        setCardsPosistion();
    }

    public void setTheta(){
        float change;
        float arc = D2 - D1;
        if(cards.size()%2==0){
            change = arc/cards.size();
        }else{
            change = arc/(cards.size()+1);
        }
        System.out.print("Theta: ");
        for(int i = 0; i < cards.size();i++){
            if(cards.size()%2==0){
                cards.get(i).setTheta(D1+(change/2) + (i*change));
            }else{
                cards.get(i).setTheta(D1+change + (i*change));
            }
            System.out.print(cards.get(i).getTheta() + ", ");
        }
        System.out.println();
    }

    public void setCardsPosistion() {
        for (int i = 0; i < cards.size(); i++){
            cards.get(i).setTruePosistion(new Vector2((float) ((Math.cos(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.x), (float) ((Math.sin(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.y)));
            //System.out.println(cards.get(i).getTruePosistion());
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
                        System.out.println("IM THERE");
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
                        System.out.println("IM THERE");
                    }
                }
            }else {
                if (cards.get(i).getRenderPosistion().x < cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed,-speed));
                    }else if(cards.get(i).getRenderPosistion().y < ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, speed));
                    }else{
                        cards.get(i).setRender(new Vector2(speed, (float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x + 1) - CENTER.x, 2)) + CENTER.y)));
                        System.out.println("IM THERE");
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
                        System.out.println("IM THERE");
                    }
                }
            }
            //fixBounds(crafter.get(i));
        }
    }

    public void setCrafterRenderPosition(){

    }

    public boolean ifAnyCombinable(){
        if(cards.size()<2){//GOOD
            return false;
        }else{
            for (int e1 = 0; e1 < cards.size(); e1++){
                for(int e2=e1+1; e2< cards.size(); e2++){
                    if(cards.get(e1).isCombinable(cards.get(e2))==true){
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
    Vector2 btnEndCombinePosistion = new Vector2((Game.WIDTH/2)- btnEndCombineWIDTH,(Game.HEIGHT/2)- btnEndCombineHEIGHT);

    public Vector2 getBtnEndCombinePosistion() {
        return btnEndCombinePosistion;
    }

    public void setBtnEndCombinePosistion(Vector2 btnEndCombinePosistion) {
        this.btnEndCombinePosistion = btnEndCombinePosistion;
    }

    public Button getBtnEndCombine() {
        return btnEndCombine;
    }

    public int getBtnEndCombineWIDTH() {
        return btnEndCombineWIDTH;
    }

    public void setBtnEndCombineWIDTH(int btnEndCombineWIDTH) {
        this.btnEndCombineWIDTH = btnEndCombineWIDTH;
    }

    public int getBtnEndCombineHEIGHT() {
        return btnEndCombineHEIGHT;
    }

    public void setBtnEndCombineHEIGHT(int btnEndCombineHEIGHT) {
        this.btnEndCombineHEIGHT = btnEndCombineHEIGHT;
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
        btnEndCombine.setRenderPosistion(btnEndCombinePosistion);
    }
    ////////////////////
    //END CONSTRUCTOR///
    ////////////////////
}
