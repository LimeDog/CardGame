package io.github.bobjoetom.craftingwar.gameElements.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.bobjoetom.craftingwar.Game;

/**
 * Created by Brenden on 4/17/2016.
 */
public class Button {

    private int WIDTH;
    private int HEIGHT;
    private float degreeRotated;

    private  String texturePath;
    private  String selectedTexturePath;

    private Texture renderedButton;
    private Vector2 renderPosistion;

    private boolean selected = false;
    private boolean visible = false;

    public boolean isVisible() {return visible;}
    public void setVisible(boolean visible) {this.visible = visible;}

    public void render(SpriteBatch sb){
        if(visible)sb.draw(renderedButton, (renderPosistion.x - (WIDTH/2)), (renderPosistion.y - (HEIGHT/2)), WIDTH, HEIGHT);
    }

    public void update(){
    }

    public int getHEIGHT() {return HEIGHT;}
    public void setHEIGHT(int HEIGHT) {this.HEIGHT = HEIGHT;}

    public int getWIDTH() {return WIDTH;}
    public void setWIDTH(int WIDTH) {this.WIDTH = WIDTH;}

    public Vector2 getRenderPosistion() { return renderPosistion; }
    public void setRenderPosistion(Vector2 coords) { renderPosistion = (coords); }

    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean b){
        selected = b;
    }

    public void select(){
        if(isSelected()==true){
            setSelected(false);
            setRenderedButton(getSelectedTexturePath());
        }else if(isSelected()==false){
            setSelected(true);
            setRenderedButton(getTexturePath());
        }
    }

    public String getSelectedTexturePath() {return selectedTexturePath;}
    public void setSelectedTexturePath(String selectedTexturePath) {this.selectedTexturePath = selectedTexturePath;}

    public String getTexturePath() {return texturePath;}
    public void setTexturePath(String texturePath) {this.texturePath = texturePath;}

    public Texture getRenderedButton() {return renderedButton;}
    public void setRenderedButton(Texture renderedButton) {
        this.renderedButton = renderedButton;
    }
    public void setRenderedButton(String renderedButton) {
        this.renderedButton = new Texture(renderedButton);
    }

    public void rotate(float degree){
        degreeRotated+= degree;
    }

    public void setDegreeRotated(float degreeRotated) {
        this.degreeRotated = degreeRotated;
    }

    public float getDegreeRotated(){
        return degreeRotated;
    }

    //////////////MAYBE HAVE ENABLED TO CLICK AND THE ENABLED BELOW TO A CLICK ENABLE, MIGHT BE EASIER FOR LOGIC
    private int enabledCounter = 0;
    private boolean enabled;

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
                    System.out.println("Button clicked!!!");
                    System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
                    System.out.println(renderPosistion.x + " " + renderPosistion.y);
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
                    System.out.println("Button clicked!!!");
                    System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
                    System.out.println(renderPosistion.x + " " + renderPosistion.y);
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

    public void checkClicked () {
        if(Gdx.input.justTouched()) {
            int iy = Gdx.input.getY();
            int ix = Gdx.input.getY();
            iy = Game.HEIGHT - iy;
            if (ix > getRenderPosistion().x - WIDTH / 2 && ix < (getRenderPosistion().x + WIDTH / 2)) {
                //if (ix > getRenderPosistion().x && ix < (getRenderPosistion().x + WIDTH)){
                if (iy > getRenderPosistion().y - HEIGHT / 2 && iy < (getRenderPosistion().y + HEIGHT / 2)) {
                    // if (iy > getRenderPosistion().y && iy < (getRenderPosistion().y + HEIGHT)) {
                    if (isEnabled() == true) {
                        System.out.println("Button clicked!!!");
                        System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
                        System.out.println(renderPosistion.x + " " + renderPosistion.y);
                        select();
                    } else if (getEnabledCounter() % 5 == 0) {
                        setEnabled(true);
                    } else {
                        setEnabledCounter(getEnabledCounter() + 1);
                        System.out.println("Not Enabled");
                    }
                }
            }
            System.out.println("NO CARD HERE");
        }
    }

    public boolean checkIfClicked () {
        if(Gdx.input.justTouched()) {
            int iy = Gdx.input.getY();
            int ix = Gdx.input.getX();
            iy = Game.HEIGHT - iy;
            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
            System.out.println(ix + " " + iy);

            System.out.println(renderPosistion.x + " " + renderPosistion.y);
            System.out.println(getRenderPosistion().x + " " + getRenderPosistion().y);
            System.out.println(getRenderPosistion().x - WIDTH / 2 + " in between " + (getRenderPosistion().x + WIDTH / 2));
            if (ix > getRenderPosistion().x - WIDTH / 2 && ix < (getRenderPosistion().x + WIDTH / 2)) {
                //if (ix > getRenderPosistion().x && ix < (getRenderPosistion().x + WIDTH)){
                System.out.println("X IS GOOD");
                if (iy > getRenderPosistion().y - HEIGHT / 2 && iy < (getRenderPosistion().y + HEIGHT / 2)) {
                    System.out.println("Y IS GOOD");
                    // if (iy > getRenderPosistion().y && iy < (getRenderPosistion().y + HEIGHT)) {
                    if (isEnabled() == true) {
                        System.out.println("Button clicked!!!");
                        System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
                        System.out.println(renderPosistion.x + " " + renderPosistion.y);
                        return true;
                    } else if (getEnabledCounter() % 5 == 0) {
                        setEnabled(true);
                    } else {
                        setEnabledCounter(getEnabledCounter() + 1);
                        System.out.println("Not Enabled");
                    }
                }
            }
            System.out.println("NO CARD HERE");
        }
        return false;
    }
}
