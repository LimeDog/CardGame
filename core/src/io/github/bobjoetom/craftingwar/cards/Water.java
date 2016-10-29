package io.github.bobjoetom.craftingwar.cards;

/**
 * Created by Brenden on 4/12/2016.
 */
public class Water extends Card {

    public Water() {
        super();
        setID("Water");
        setCardTexturePath("water.png");
        setCardSelectedTexturePath("water-Selected.png");
        setRenderedCard(getCardTexturePath());
    }

    @Override
    public boolean isCombinable(Card e) {
        return super.isCombinable(e);
    }

    @Override
    public Card combine(Card e) {

        return super.combine(e);
    }
}
