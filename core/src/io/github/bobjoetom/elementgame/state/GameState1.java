package io.github.bobjoetom.elementgame.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import io.github.bobjoetom.elementgame.Game;
import io.github.bobjoetom.elementgame.gameElements.EndCombineButton;
import io.github.bobjoetom.elementgame.gameElements.DrawPile;
import io.github.bobjoetom.elementgame.gameElements.Player;

/**
 * Created by Brenden on 4/10/2016.
 */
public class GameState1 extends State{
    ArrayList<Player> players = new ArrayList<Player>();
    Player currentPlayer;
    public final int CARDCAP = 10;
    EndCombineButton endCombineButton = new EndCombineButton();
    //Player player1 = new Player(1);
    //Player player2 = new Player(2);
    DrawPile drawPile = new DrawPile();
    Random rando = new Random();

    //TODO BIGGEST THING IS TO DISPOSE OF UNUSED TEXTURES NOT CONSTANTLY RENDER OR UPDATE SAME ARITHMATIC AND NO CREATE REUSABLE OBJECTS OVER AND OVER AGAIN LIKE TEXTURES AND RANDOMS
    public GameState1(GameStateManager gsm) {
        super(gsm);
        //STATE INIT
        players.add(0, new Player(1));
        players.add(1, new Player(2));
        players.get(0).setTurn(true);
        players.get(0).setDrawing(true);
        players.get(1).setTurn(false);
        //
        //for(int i = 0; i < 5; i++){
         //   player2.addCard(i,new Card());
        //}
        //cam.setToOrtho(false, 10, 10);//
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
           // player1.addCard(player1.nextIndex(),new Card());
           // player2.removeCard(3);
           // player1.addCard(player1.nextIndex(), drawPile.transferCard(1));
           // player1.getCard(0).select();
           // player2.addCard(player2.nextIndex(), new Fire());
        }
    }

    public void update(float dt) {
        //TODO FIX THE DRAWPILE RANDOMLY SELECTING AND FUFILLING 3 CARDS
        //TODO FIX TURNS BY SWITCHING BETWEEN PLAYERS
        //TODO COMBINATOION
        //ACTIONS?
        ///////COMMENTED OUT GAME LOGIC
        /*
        if(Gdx.input.justTouched()){
            if(drawPile.getSelected() != null) {
                players.get(0).addCard(drawPile.transferCard());
                drawPile.setSelectedNull();
            }
        }
        drawPile.update();
        players.get(0).update();
        /*
        currentPlayer.update();
        if(currentPlayer.isDrawing()){
            Game.consolePrint(10,"Player " + currentPlayer.getPlayerNumber() + " is drawing");
            drawPile.update();
            if(drawPile.getSelected()!=null){
                System.out.println("+#########################################################+");
                currentPlayer.addCard(currentPlayer.nextIndex(), drawPile.getSelected());
                drawPile.setSelectedNull();
                currentPlayer.setDrawing(false);
            }
        }else{//ELSE IF COMBINE AND THEN ACTION
            setNextPlayer();
        }

        for(int i = 0; i < players.size();i++){//LOOPS THROUGH PLAYERS
            if(players.get(i).isTurn()){//FINDS WHOS TURN IT IS
                currentPlayer = players.get(i);//SETS PLAYER TO CURRENT PALYER
                //System.out.println(players.get(i).getPlayerNumber());
            }
            //players.get(i).update();
        }
        -------------------------------------
        */

        if(players.get(0).isTurn())currentPlayer=players.get(0);
        if(players.get(1).isTurn())currentPlayer=players.get(1);
        //DRAWING LOGIC
        if(currentPlayer.isDrawing()){//IF IT IS THE CARD DRAWING PHASE OF THE GAME
            //TEMP CARD CAP
            //TODO STILL ADDS AFTER 10
            if(currentPlayer.getCardsSize()>=CARDCAP){
                currentPlayer.setDrawing(false);
            }
            if(drawPile.getSelected()==null){//IF NO CARD IS SELECTED
                drawPile.update();//UPDATE IF THEY GOT A CARD
            }else if(drawPile.getSelected()!=null){//IF THEY SELECTED A CARD
               drawCard();
            }
            currentPlayer.setCombining(true);

        }else if(currentPlayer.isCombining()){//==============================================================================================================
            System.out.print(1);
            currentPlayer.update();
            System.out.print(2);
            //if((new Fire().ifCombinable(new Earth())))System.out.println("Please WORK");
            if(currentPlayer.ifAnyCombinable()!=true){
                System.out.println("Why are you breaking");
                currentPlayer.setCombining(false);
            }else{
                //TODO BREAKS/UNRESPONSIVE (0xCFFFFFFF) on player2
                System.out.println("Combinable");
                if(currentPlayer.getPlayerNumber()==1)endCombineButton.setRenderPosistion(new Vector2(Game.WIDTH/2, ((currentPlayer.getPlayerNumber()-1)*Game.HEIGHT) +endCombineButton.getHEIGHT()/2));//MOVE TO SET NEXT PLAYER
                else endCombineButton.setRenderPosistion(new Vector2(Game.WIDTH/2, ((currentPlayer.getPlayerNumber()-1)*Game.HEIGHT)-endCombineButton.getHEIGHT()/2));//MOVE TO SET NEXT PLAYER
                endCombineButton.setVisible(true);
                if(Gdx.input.justTouched()){
                    if(endCombineButton.checkIfClicked(Gdx.input.getX(),Gdx.input.getY())){///UPDATING
                        System.out.println("YOU DONE MESSED UP!**(!*&$))!@&!&%#!%#@!$@!$!%_)@@!%&_)@%!@%_@@!%_");
                        currentPlayer.setCombining(false);
                    }
                    //IF TO CHECK SELECT A SELECTED IN CRAFTER TO SEND BACK TO CARDS
                }
            }
            //TODO FIX SELECTION
            //  //NOT COMBINABLE AFTER ONE IS SELECTED// WAS NOT ONLY LIKE THAT CHECK OLD CODE
            System.out.print(3);
            if(currentPlayer.getCrafterSize()==2){//*******MIGHT WANT TO MOVE TO PLAYER IF WE WANT A CLICK TO CRAFT PLAYER
                if(currentPlayer.getCrafter(0).isCombinable(currentPlayer.getCrafter(1))!=true){
                    currentPlayer.dumpToCards();
                }else{
                    System.out.println(currentPlayer.getCrafter(0).toString() +" "+ currentPlayer.getCrafter(1).toString());
                    currentPlayer.combine();
                    System.out.println("COMBINED");
                    currentPlayer.setCombining(false);
                }
            }//******
            System.out.print(4);
        }else if(!currentPlayer.isCombining()){//===================================================================================================================
            endCombineButton.setVisible(false);
            setNextPlayerTurn();
        }
        //INSERT ELSE FOR COMBINATION
    }

    private void drawCard(){
        currentPlayer.addCard(drawPile.transferCard());//ADD CARD
        drawPile.setSelectedNull();//BECAUSE THAT CARD SHOULD NO LONGER BE SELECTED AS THE DRAWN CARD
        currentPlayer.setDrawing(false);//BECAUSE THEY PICKED A CARD
        currentPlayer.setCombining(true);
    }

    private void setNextPlayerTurn(){
        currentPlayer.setTurn(false);
        if(currentPlayer.getPlayerNumber()%players.size()==0){
            players.get(0).setTurn(true);
        }else if(currentPlayer.getPlayerNumber()<players.size()){
            players.get(currentPlayer.getPlayerNumber()).setTurn(true);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined);
        //.out.println("BIG RENDERINGGGGGGGGGGGGGGGGGGGGGGGGGGG");
        sb.begin();
        players.get(0).render(sb);
        players.get(1).render(sb);
        drawPile.render(sb);
        endCombineButton.render(sb);
        //TODO RENDER OTHER TEXTURES and TEXT
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
