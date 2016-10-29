package io.github.bobjoetom.craftingwar.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.bobjoetom.craftingwar.Game;

/**
 * Created by Brenden on 4/10/2016.
 */
public class Card {//THIS REALLY SHOULD EXTEND BUTTON
    //TODO ALSO PACK GDX TEXTURES INTO A GGDX TEXTURE PACK
    //TODO INIT AND CONSTRUCTOR PROBLEMS?? SHOULD TEXTURE BE INITIALIZED IN CONSTRUCTOR OR
    public final int WIDTH = 50;
    public final int HEIGHT = 50;
    private String ID = "Card";
    private  String cardTexturePath = "water.png";
    private  String cardSelectedTexturePath = "water-Selected.png";

    private Vector2 truePosistion = new Vector2(0f,0f);//FIX
    private Vector2 renderPosistion = new Vector2(0f,0f);//FIX
    private float theta = 0;

    //SWITCH THESE 2 TO STRINGS FOR TEH STRIONG PATHS
    private Texture renderedCard;//TODO EACH ONE SHOULDNT HAVE 3 TEXTURES SELECTED SHOULD SITCH RENDER EBTWEEN FILES RATHER THAN SWITCH RENDRED BETWEEN TEXTURE OBJECTS//PROBABLY JUST SET THESE VARIABLES TO A STRING OF TEH TEXTURE PATH
    private boolean selected = false;

    ////////////////////
    //CONSTRUCTOR///////
    ////////////////////
    public Card(){
        setRenderedCard(getCardTexturePath());
        setTruePosistion(new Vector2(0f,0f));
        setRenderPosistion(new Vector2(0f,0f));
    }

    public Card(Vector2 initPosistion){
        setTruePosistion(initPosistion);
        setRenderPosistion(initPosistion);
    }
    ////////////////////
    //UPDATE & RENDER///
    ////////////////////
    public void update() {
        //TODO SELECTION/COMBINABLE
        //IF SELECTED CHANGE TEXTURE TO SELECTED TEXTURE
    }

    public void render(SpriteBatch sb){
        sb.draw(renderedCard, (renderPosistion.x - (WIDTH/2)), (renderPosistion.y - (HEIGHT/2)), WIDTH, HEIGHT);
        //System.out.println(getRenderPosistion().x + " " + renderPosistion.y);
        Game.consolePrint(200, getRenderPosistion().toString());
    }
    ////////////////////
    //UTILITIES/////////
    ////////////////////
    public String toString(){
        return renderPosistion.toString();
    }

    public void setTheta(float theta){
        this.theta=theta;
    }
    public double getTheta(){
        return (double)theta;
    }

    public Vector2 getTruePosistion() { return truePosistion; }
    public Vector2 getRenderPosistion() { return renderPosistion; }

    public void setTruePosistion(Vector2 coords){ truePosistion.set(coords); }
    public void setRenderPosistion(Vector2 coords) { renderPosistion.set(coords); }

    public void setRender(Vector2 increase){ setRenderPosistion(new Vector2(getRenderPosistion().x+increase.x, increase.y)); }
    //^NOT A TOTAL TRANSLATION, ONLY TRANSLATES THE X AND CALCULATES THE Y
    public void translateRender(Vector2 increase){ setRenderPosistion(new Vector2(getRenderPosistion().x+increase.x, getRenderPosistion().y+increase.y)); }

    ////////////////////
    //Combination///////
    ////////////////////
    public boolean isCombinable(Card e){
        //TODO USE ID's
        //LIST ALL IDS's that are combinable and return true if tehy are combinable
        return false;
    }

    public Card combine(Card e){
        //TODO USE ID'S
        //FOR ALL IDS THAT ARE COMBINABLE RETURN THE CARD THEY MAKE
        return new Card();

    }
    ////////////////////
    //SELECTION/////////
    ////////////////////
    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean b){
        selected = b;
    }

    public void select(){
        if(isSelected()==true){
            setSelected(false);
            setRenderedCard(getCardTexturePath());
        }else if(isSelected()==false){
            setSelected(true);
            setRenderedCard(getCardSelectedTexturePath());
        }
    }
    ////////////////////
    //TEXTURES//////////
    ////////////////////
    public String getCardTexturePath() { return cardTexturePath; }

    public void setCardTexturePath(String card) { cardTexturePath = card; }

    public String getCardSelectedTexturePath() { return cardSelectedTexturePath; }

    public void setCardSelectedTexturePath(String cardSelected) { cardSelectedTexturePath = cardSelected; }

    public Texture getRenderedCard() { return renderedCard; }

    public void setRenderedCard(String renderedCardPath) {
        this.renderedCard = new Texture(renderedCardPath);
    }
    ////////////////////
    //ID////////////////
    ////////////////////
    public String getID() {
        return ID;
    }
    public void setID(String i) { ID = i;}
    ////////////////////
    ////////////////////

    //////////////TODO *!*!*!MAYBE HAVE ENABLED TO CLICK AND THE ENABLED BELOW TO A CLICK ENABLE, MIGHT BE EASIER FOR LOGIC
    private int enabledCounter = 0;
    private boolean enabled = true;

    public int getEnabledCounter() {
        return enabledCounter;
    }
    public void setEnabledCounter(int enabledCounter) {
        this.enabledCounter = enabledCounter;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void checkClicked (float ix, float iy) {
        iy = Game.HEIGHT - iy;
        if (ix > getRenderPosistion().x-WIDTH/2 && ix < (getRenderPosistion().x + WIDTH/2)){
            //if (ix > getRenderPosistion().x && ix < (getRenderPosistion().x + WIDTH)){
            if (iy > getRenderPosistion().y - HEIGHT/2 && iy < (getRenderPosistion().y + HEIGHT/2)) {
                // if (iy > getRenderPosistion().y && iy < (getRenderPosistion().y + HEIGHT)) {
                if(isEnabled() == true) {
                    System.out.println("Card clicked!!!");
                    //System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
                    //System.out.println(renderPosistion.x + " " + renderPosistion.y);
                    select();
                }else if(getEnabledCounter()%5==0){
                    setEnabled(true);
                }else{
                    setEnabledCounter(getEnabledCounter()+1);
                    System.out.println("Not Enabled");
                }
            }
        }
        System.out.println("NO CARD HERE");
    }

    public boolean checkIfClicked (float ix, float iy) {
        iy = Game.HEIGHT - iy;
        if (ix > getRenderPosistion().x-WIDTH/2 && ix < (getRenderPosistion().x + WIDTH/2)){
            //if (ix > getRenderPosistion().x && ix < (getRenderPosistion().x + WIDTH)){
            if (iy > getRenderPosistion().y - HEIGHT/2 && iy < (getRenderPosistion().y + HEIGHT/2)) {
                // if (iy > getRenderPosistion().y && iy < (getRenderPosistion().y + HEIGHT)) {
                if(isEnabled() == true) {
                    System.out.println("Card clicked!!!");
                    //System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
                    //System.out.println(renderPosistion.x + " " + renderPosistion.y);
                    return true;
                }else if(getEnabledCounter()%5==0){
                    setEnabled(true);
                }else{
                    setEnabledCounter(getEnabledCounter()+1);
                    System.out.println("Not Enabled");
                }
            }
        }
        System.out.println("NO CARD HERE");
        return false;
    }

}
