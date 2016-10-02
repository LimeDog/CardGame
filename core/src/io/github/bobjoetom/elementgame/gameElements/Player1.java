package io.github.bobjoetom.elementgame.gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


import io.github.bobjoetom.elementgame.Game;
import io.github.bobjoetom.elementgame.cards.Card;

/**
 * Created by Brenden on 4/10/2016.
 */
public class Player {

    private boolean turn;//DETERMINES SELECTABLE;

    private int playerNumber;
    public int getPlayerNumber() { return playerNumber; }

    private int Score;
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Card> crafter = new ArrayList<Card>();

    ////////////////////
    //CIRCLE////////////
    ////////////////////
    private final int RADIUS = (Game.WIDTH/2)-50;
    private final Vector2 CENTER = new Vector2();
    private float D1;
    private float D2;

    ////////////////////
    //CONSTRUCTOR///////
    ////////////////////
    public Player(int p){
        if(p ==1){
            CENTER.set(Game.WIDTH/2,0);
            D1 = 0;
            D2 = 180;
        } else if(p ==2){
            CENTER.set(Game.WIDTH/2,Game.HEIGHT);
            D1 = 180;
            D2 = 360;
        }
        playerNumber = p;
        //cards.add(0,new Card());
    }
    ////////////////////
    //PROPERTIES////////
    ////////////////////
    public Card getCard(int i){
        if(i<0) return new Card();
        return cards.get(i);
    }
    public int getCardsSize(){ return cards.size(); }

    public boolean isTurn() {
        return turn;
    }
    public void setTurn(boolean turn) {
        this.turn = turn;
        if(turn==true){setDrawing(true); }
    }

    public boolean isDrawing() {
        return drawing;
    }
    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public boolean isCombining() {return combining;}
    public void setCombining(boolean combining) {this.combining = combining;}

    ////////////////////
    //UTILITIES///////
    ////////////////////
    public int cardsNextIndex(){
        /*if(cards.size()==0)return 0;
        for(int i = 0; i < cards.size(); i++){
            System.out.println(cards.get(i).toString());
        }
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i)==null){
                return i;
            }else return i+1;
        }
        System.out.println("You fucked up");
        return -1;
        */
        return cards.size();
    }

    public int crafterNextIndex(){
        return crafter.size();
    }
    public int getCrafterSize(){ return crafter.size(); }
    public Card getCrafter(int i){ return cards.get(i); }

    public void addCard(int i, Card e){
        cards.add(i, e);
        refresh();
    }

    public void addCard( Card e){
        cards.add(cardsNextIndex(), e);
        refresh();
    }

    public void removeCard(int i){
        if( i <cards.size())cards.remove(i);
       /// condence(i);
    }

    public void removeCard(Card e){
        cards.remove(e);
    }

    private void condence(int i){
        for(int x = i+1; x < cards.size(); i++){
            Card buffer = cards.get(x);
            cards.remove(x);
            cards.add(x-1, buffer);
        }
    }
    ////////////////////
    //COORDINATES///////
    ////////////////////
    public void refresh(){
        setTheta();
        setTruePosistion();
        setRenderPosistion();
    }

    public void setTheta(){
        float change;
        float arc = D2 - D1;
        if(cards.size()%2==0){
            change = arc/cards.size();
        }else{
            change = arc/(cards.size()+1);
        }
        //if(D2>180)change*=-1f;
        for(int i = 0; i < cards.size();i++){
            if(cards.size()%2==0){
                cards.get(i).setTheta(D1+(change/2) + (i*change));
            }else{
                cards.get(i).setTheta(D1+change + (i*change));
            }
            //System.out.println(cards.get(i).getTheta());
        }

    }
    //X
    public void setTheta(float d1, float d2){
        float change;
        float arc = d2 -d1;
        if(cards.size()%2==0){
            change = arc/cards.size();
        }else{
            change = arc/(cards.size()+1);
        }
        for(int i = 0; i < cards.size();i++){
            if(cards.size()%2==0){
                cards.get(i).setTheta(change/2 + (i*change));
            }else{
                cards.get(i).setTheta(change + (i*change));
            }
        }
    }
    //X
    public void setTheta(int n, float d1, float d2){
        float change;
        float arc = d2 -d1;
        if(n%2==0){
            change = arc/n;
        }else{
            change = arc/(n+1);
        }
        for(int i = 0; i < n;i++){
            if(n%2==0){
                cards.get(i).setTheta(change/2 + (i*change));
            }else{
                cards.get(i).setTheta(change + (i*change));
            }
        }
    }
    //X
    public void setTheta(Card e, int n){
        float change;
        float arc = D2 -D1;
        if(n%2==0){
            change = arc/n;
        }else{
            change = arc/(n+1);
        }
        for(int i = 0; i < n;i++){
            if(n%2==0){
               e.setTheta(change/2 + (i*change));
            }else{
                e.setTheta(change + (i*change));
            }
        }
    }

    public void setTruePosistion() {
        for (int i = 0; i < cards.size(); i++){
            cards.get(i).setTruePosistion(new Vector2((float) ((Math.cos(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.x), (float) ((Math.sin(Math.toRadians(cards.get(i).getTheta())) * RADIUS) + CENTER.y)));
            //System.out.println(cards.get(i).getTruePosistion());
        }
    }

    public void setTruePosistion(Card e){
        e.setTruePosistion(new Vector2((float) ((Math.cos(Math.toRadians(e.getTheta())) * RADIUS) + CENTER.x), (float) ((Math.sin(Math.toRadians(e.getTheta())) * RADIUS) + CENTER.y)));
    }

    private final float speed = 3f;

    public void setRenderPosistion(){
        for(int i = 0; i < cards.size(); i++){
            if(getPlayerNumber()==2){//
                if (cards.get(i).getRenderPosistion().x < cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y < ((float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2))) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, speed));
                    }else if(cards.get(i).getRenderPosistion().y > ((float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2))) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, -speed));
                    }else{
                        cards.get(i).calculateRender(new Vector2(speed, (float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x + 1) - CENTER.x, 2))) + CENTER.y)));
                        //System.out.println("124151251244125");
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
                        cards.get(i).calculateRender(new Vector2(-speed, (float) (-1*(Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2))) + CENTER.y)));
                        //System.out.println("124151251244125");
                    }
                }
            }else {
                if (cards.get(i).getRenderPosistion().x < cards.get(i).getTruePosistion().x) {
                    if(cards.get(i).getRenderPosistion().y > ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed,-speed));
                    }else if(cards.get(i).getRenderPosistion().y < ((float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x) - CENTER.x, 2)) + CENTER.y))){
                        cards.get(i).translateRender(new Vector2(speed, speed));
                    }else{
                        cards.get(i).calculateRender(new Vector2(speed, (float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x + 1) - CENTER.x, 2)) + CENTER.y)));
                        //System.out.println("124151251244125");
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
                        cards.get(i).calculateRender(new Vector2(-speed, (float) (Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((cards.get(i).getRenderPosistion().x - 1) - CENTER.x, 2)) + CENTER.y)));
                        //System.out.println("124151251244125");
                    }
                }
            }
            //fixBounds(crafter.get(i));
        }
        for(int i = 0; i < crafter.size(); i++){
                if (crafter.get(i).getRenderPosistion().x < crafter.get(i).getTruePosistion().x) {
                    if(crafter.get(i).getRenderPosistion().y < crafter.get(i).getTruePosistion().y){
                        crafter.get(i).translateRender(new Vector2(speed, speed));
                    }
                    if(crafter.get(i).getRenderPosistion().y > crafter.get(i).getTruePosistion().y){
                        crafter.get(i).translateRender(new Vector2(speed, -speed));
                    }
                }
                if (crafter.get(i).getRenderPosistion().x > crafter.get(i).getTruePosistion().x) {
                    if(crafter.get(i).getRenderPosistion().y < crafter.get(i).getTruePosistion().y){
                        crafter.get(i).translateRender(new Vector2(-speed, speed));
                    }
                    if(crafter.get(i).getRenderPosistion().y > crafter.get(i).getTruePosistion().y){
                        crafter.get(i).translateRender(new Vector2(-speed, -speed));
                    }
                }
        }
    }

    private void fixBounds(Card e){
        if(Math.abs(e.getRenderPosistion().x - e.getTruePosistion().x)<=2 && Math.abs(e.getRenderPosistion().y - e.getTruePosistion().y)<=2){
            e.setRenderPosistion(new Vector2(e.getTruePosistion().x, e.getRenderPosistion().y));
        }
    }
    ////////////////////
    //UPDATE & RENDER///
    ////////////////////
    public void transferToCrafter(Card card){//transfers Card to Crafter
        crafter.add(crafterNextIndex(),card);
        cards.remove(cards.indexOf(card));
        //MIGHT NEED TO CONDENCE HERE^
        /*
        if(cards.size()<2){
            setCombining(false);
        }else{
            setCombining(false);

        }
        */
    }
    public boolean ifAnyCombinable(){
        if(cards.size()<2){//GOOD
            return false;
        }else{
            /*
            for(int i = 0; i < cards.size(); i++){
                for(int e1 = 0;e1 <= i;i++){
                    for(int e2 = cards.size()-1; e2 > i; e2--)
                    if(cards.get(e1).ifCombinable(cards.get(e2))==true){
                        if(e2>=cards.size()) break;
                        System.out.println(cards.get(e1).toString() + " " + cards.get(e2));
                        return true;
                    }
                }
            }
            */
            for (int e1 = 0; e1 < cards.size(); e1++){
                for(int e2=e1+1; e2< cards.size(); e2++){
                    if(cards.get(e1).isCombinable(cards.get(e2))==true){
                        System.out.println(cards.get(e1).toString() + " " + cards.get(e2));
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public void combine(){
        addCard(cardsNextIndex(), crafter.get(0).combine(crafter.get(1)));
        crafter.remove(0);crafter.remove(1);
    }

    public void dumpToCards(){//Transferes all cards in crafter to cards
        crafter.get(0).select();
        crafter.get(1).select();
        cards.add(cardsNextIndex(),crafter.get(0));
        cards.add(cardsNextIndex(),crafter.get(1));
        crafter.remove(1);
        crafter.remove(0);
    }

    public void update(){
        //TODO WHEN HANDLING SELECTIONS WE WANT TO CONDENCE THE ARRAY
        if(Gdx.input.justTouched()) {
            //System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
            for (int i = 0; i < cards.size(); i++) {
                if(cards.get(i).checkIfClicked(Gdx.input.getX(), Gdx.input.getY())){
                    if(crafter.size()< 2){
                        cards.get(i).select();
                        transferToCrafter(cards.get(i));
                    }

                }
            }
        }
        if(!cards.isEmpty()){
           //Game.consolePrint(100, "It works?");
        }
    }

    public void render(SpriteBatch sb){
        for(int i = 0; i < cards.size(); i++){
            //System.out.println("RENDERING!!!!!");
            cards.get(i).render(sb);
        }
        for(int i = 0; i < crafter.size(); i++) {
            if(playerNumber==2)crafter.get(i).setTruePosistion(new Vector2((Game.WIDTH / 3) + (i * (Game.WIDTH / 3)), (7*(Game.HEIGHT / 8))));
            else crafter.get(i).setTruePosistion(new Vector2((Game.WIDTH / 3) + (i * (Game.WIDTH / 3)), Game.HEIGHT / 8));
            crafter.get(i).render(sb);
        }
        refresh();
    }
}