package io.github.bobjoetom.elementgame.cards;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brenden on 4/12/2016.
 */
public class Fire extends Card {

    public Fire() {
        super();
        setID("Fire");
        setCardTexturePath("fire.png");
        setCardSelectedTexturePath("fire-Selected.png");
        setRenderedCard(getCardTexturePath());

    }
    /*
    @Override
    public boolean ifCombinable(Card e) {
        if(e.getID()=="Earth"){
            return true;
        }
        return false;
    }
    */
    @Override
    public Card combine(Card e) {
        if(e.getID()=="Earth"){
            return new Smeltery();
        }
        return new Card();
    }
}
