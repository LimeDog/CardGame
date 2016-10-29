package io.github.bobjoetom.craftingwar.cards;

/**
 * Created by Brenden on 4/16/2016.
 */
public class Smeltery extends Card{

    public Smeltery() {
        super();
        setID("Smeltery");
        setCardTexturePath("smeltery.png");
        setCardSelectedTexturePath("smeltery-Selected.png");
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
