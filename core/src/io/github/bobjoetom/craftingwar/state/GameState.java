package io.github.bobjoetom.craftingwar.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.bobjoetom.craftingwar.Game;
import io.github.bobjoetom.craftingwar.gameElements.DrawPile;
import io.github.bobjoetom.craftingwar.gameElements.Player;

/**
 * Created by Brenden on 8/7/2016.
 */
//TODO*************************************************************
    //WHEN A CARD IS SELECTED IT IS REMOVED FROM THE CARDS AND CAN BE DRAGED ANNYWHERE, THIS IS Card movable; movable ='s card removed; always rendered if not null to follow the newed input
public class GameState extends State{

    Player currentPlayer;//Object Pointer of cutternt players turn
    Player player1;
    Player player2;

    final int CARDCAP = 8;
    DrawPile drawPile;


    public GameState(GameStateManager gsm) {
        super(gsm);

        drawPile = new DrawPile();
        player1 = new Player();
        player1.setD1(0);
        player1.setD2(180);
        player1.setCENTER(new Vector2(Game.WIDTH/2,0));
        player1.setBtnEndCombinePosistion(new Vector2((Game.WIDTH/2),player1.getBtnEndCombineHEIGHT()/2));


        player2 = new Player();
        player2.setD1(180);
        player2.setD2(360);
        player2.setCENTER(new Vector2(Game.WIDTH/2,Game.HEIGHT));
        player2.setBtnEndCombinePosistion(new Vector2((Game.WIDTH/2),(Game.HEIGHT)-(player2.getBtnEndCombineHEIGHT()/2)));
        player2.getBtnEndCombine().setDegreeRotated(180);//TODO FLIPPPPPPP COOOOORRREEECCTTTLLLYYYY


        currentPlayer = player1;
        currentPlayer.setTurn(true);
        currentPlayer.setDrawing(true);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){

            if(currentPlayer.isCombining()) {//While combining

                if (currentPlayer.getBtnEndCombine().checkIfClicked()) {// if the palyer does not want to craft/combine
                    currentPlayer.setCombining(false);
                    currentPlayer.setTaking(true);
                    currentPlayer.getBtnEndCombine().setEnabled(false);
                    currentPlayer.getBtnEndCombine().setVisible(false);
                    currentPlayer.dumpToCards();
                }

                for(int i = 0; i < currentPlayer.getCardsSize(); i++){
                    if(currentPlayer.getCard(i).checkIfClicked(Gdx.input.getX(),Gdx.input.getY())){
                        currentPlayer.insertCrafter(currentPlayer.removeCard(i));
                    }
                }

                for(int i = 0; i < currentPlayer.getCrafterSize(); i++){
                   // if(currentPlayer.getCrafter(i).checkIfClicked(Gdx.input.getX(),Gdx.input.getY())){
                    //    currentPlayer.drawCard(currentPlayer.removeCrafter(i));
                    //}
                }
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player1.update(dt);
        player2.update(dt);
        drawPile.update();


        //CUREENT PLAYERS TURN
        if(currentPlayer.isDrawing()){
            System.out.println("Drawing");
            if(currentPlayer.getCardsSize()>=CARDCAP){//CANT DRAW IF OVER CARD LIMIT
                    System.out.println("CARDCAP!!!!");
                   currentPlayer.setDrawing(false);
                   currentPlayer.setCombining(true);
            }else {
                if (drawPile.getSelected() == null) {//IF NO CARD IS SELECTED
                    drawPile.drawing();//UPDATE IF THEY GOT A CARD
                } else if (drawPile.getSelected() != null) {//IF THEY SELECTED A CARD
                    currentPlayer.drawCard(drawPile.transferCard());
                    drawPile.setSelectedNull();
                    currentPlayer.setDrawing(false);
                    currentPlayer.setCombining(true);
                }
            }
        }

        if(currentPlayer.isCombining()){//LETES FIX THIS
            //System.out.println("Combining");
            if(!currentPlayer.ifAnyCombinable()&&currentPlayer.getCrafterSize()==0){//END COMBINE IF YOU DONT HAVE AN COMBINABLE CARDS
                System.out.println("********************************\n****************************\n********************");
                currentPlayer.setCombining(false);
                currentPlayer.setTaking(true);
            }else {
                //ENABLE ENDCOMBINE BUTTON
                currentPlayer.getBtnEndCombine().setEnabled(true);
                currentPlayer.getBtnEndCombine().setVisible(true);

                //IF THEY CLICK THE BUTTON THEN ENDCOMBINE


                if (currentPlayer.getCrafterSize() == 2) {//Make if 2 && Combinable then a combine button shows up, on action the combine button makes the combination
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    if (currentPlayer.getCrafter(0).isCombinable(currentPlayer.getCrafter(1)) != true) {
                        currentPlayer.dumpToCards();
                    } else {
                        currentPlayer.combine();//TODO GET POINTS
                        System.out.println("COMBINED");
                        currentPlayer.removeCrafter(1);
                        currentPlayer.removeCrafter(0);
                        currentPlayer.setCombining(false);
                        currentPlayer.setTaking(true);
                        currentPlayer.getBtnEndCombine().setEnabled(false);
                        currentPlayer.getBtnEndCombine().setVisible(false);
                    }
                }
            }
        }

        if(currentPlayer.isTaking()) {
            System.out.println("Taking Turn/ End Turn");
            //USE ATTACK CARDS
            //TRADE?
            //STARTS NEXT TURN
            currentPlayer.setTaking(false);
            nextTurn();
        }
    }

    public void nextTurn(){
        if(player1.isTurn()==true){
            currentPlayer=player2;
            player1.setTurn(false);
            player2.setTurn(true);
        }else{
            currentPlayer=player1;
            player2.setTurn(false);
            player1.setTurn(true);
        }
        currentPlayer.setDrawing(true);
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