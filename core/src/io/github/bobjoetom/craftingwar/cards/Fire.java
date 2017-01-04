package io.github.bobjoetom.craftingwar.cards;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brenden on 4/12/2016.
 */
public class Fire extends Card {

    public Fire() {
        super();
        setID("Fire");
        setPoints(5);
        setCardTexturePath("fire.png");
        setCardSelectedTexturePath("fire-Selected.png");
        setRenderedCard(getCardTexturePath());

    }
    public boolean isCombinable(Card e) {
        return e.getID() == "Earth";
    }
    @Override
    public Card combine(Card e) {
        if(e.getID()=="Earth"){
            return new Smeltery();
        }
        return new Fire();
    }
}
