package io.github.bobjoetom.craftingwar.gameElements.buttons;

import com.badlogic.gdx.math.Vector2;

import io.github.bobjoetom.craftingwar.gameElements.buttons.Button;

/**
 * Created by Brenden on 4/17/2016.
 */
public class EndCombineButton extends Button {
    public EndCombineButton(){
        setSelectedTexturePath("actionText.png");
        setTexturePath("actionText.png");
        setRenderedButton(getTexturePath());
        setRenderPosistion(new Vector2(0,0));
        setWIDTH(150);
        setHEIGHT(50);
    }
}
