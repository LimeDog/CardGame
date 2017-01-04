package io.github.bobjoetom.craftingwar.cards;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Emerson Chin on 11/16/2016.
 */
public class Human extends Card {

    public Human() {
        super();
        setID("Human");
        setPoints(5);
        setCardTexturePath("human.png");
        setCardSelectedTexturePath("human-Selected.png");
        setRenderedCard(getCardTexturePath());

    }
    public boolean isCombinable(Card e) {
        return e.getID() == "Human";
    }
    @Override
    public Card combine(Card e) {
        if(e.getID()=="Human"){
            return new Human();
        }
        return new Human();
    }
}
