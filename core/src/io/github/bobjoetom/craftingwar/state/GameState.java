package io.github.bobjoetom.craftingwar.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.bobjoetom.craftingwar.Game;
import io.github.bobjoetom.craftingwar.gameElements.DrawPile;
import io.github.bobjoetom.craftingwar.gameElements.Player;

/**
 * Created by Brenden on 8/7/2016.
 */
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
        player1.setBtnEndCombinePosistion(new Vector2((Game.WIDTH/2)- player1.getBtnEndCombineWIDTH(),(Game.HEIGHT/2)- player1.getBtnEndCombineHEIGHT()));

        player2 = new Player();
        player2.setD1(180);
        player2.setD2(360);
        player2.setCENTER(new Vector2(Game.WIDTH/2,Game.HEIGHT));
        player2.setBtnEndCombinePosistion(new Vector2((Game.WIDTH/2)- player1.getBtnEndCombineWIDTH(),(Game.HEIGHT/2)- player1.getBtnEndCombineHEIGHT()));

        currentPlayer = player1;
        currentPlayer.setTurn(true);
        currentPlayer.setDrawing(true);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
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
            }
            if(drawPile.getSelected()==null){//IF NO CARD IS SELECTED
                   drawPile.drawing();//UPDATE IF THEY GOT A CARD
            }else if(drawPile.getSelected()!=null){//IF THEY SELECTED A CARD
                currentPlayer.drawCard(drawPile.transferCard());
                drawPile.setSelectedNull();
                currentPlayer.setDrawing(false);
                currentPlayer.setCombining(true);
            }
        }

        if(currentPlayer.isCombining()){//LETES FIX THIS
            System.out.println("Combining");
            if(currentPlayer.ifAnyCombinable()){//END COMBINE IF YOU DONT HAVE AN COMBINABLE CARDS
                 currentPlayer.setCombining(false);
                 currentPlayer.setTaking(true);
            }
            //ENABLE ENDCOMBINE BUTTON
            currentPlayer.getBtnEndCombine().setEnabled(true);
            currentPlayer.getBtnEndCombine().setVisible(true);

                //IF THEY CLICK THE BUTTON THEN ENDCOMBINE
            if(currentPlayer.getBtnEndCombine().checkIfClicked()){
                currentPlayer.setCombining(false);
                currentPlayer.setTaking(true);
            }

            if(currentPlayer.getCrafterSize()==2){//Make if 2 && Combinable then a combine button shows up, on action the combine button makes the combination
                if(currentPlayer.getCrafter(0).isCombinable(currentPlayer.getCrafter(1))!=true){
                    currentPlayer.dumpToCards();
                }else{
                    //System.out.println(currentPlayer.getCrafter(0).toString() +" "+ currentPlayer.getCrafter(1).toString());
                    currentPlayer.combine();
                    System.out.println("COMBINED");
                    currentPlayer.setCombining(false);
                    currentPlayer.setTaking(true);
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