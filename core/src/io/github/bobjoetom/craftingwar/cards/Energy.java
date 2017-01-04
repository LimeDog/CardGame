package io.github.bobjoetom.craftingwar.cards;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brenden on 4/12/2016.
 */
public class Energy extends Card {

    public Energy() {
        super();
        setID("Energy");
        setPoints(5);
        setCardTexturePath("energy.png");
        setCardSelectedTexturePath("energy-Selected.png");
        setRenderedCard(getCardTexturePath());

    }
    public boolean isCombinable(Card e) {
        return e.getID() == "Energy";
    }
    @Override
    public Card combine(Card e) {
        if(e.getID()=="Energy"){
            return new Energy_Boost();
        }
        return new Energy();
    }
}
