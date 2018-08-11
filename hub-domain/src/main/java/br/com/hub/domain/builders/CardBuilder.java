package br.com.hub.domain.builders;



import br.com.hub.domain.Card;

import java.util.ArrayList;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
public final class CardBuilder {
    private String cardnumber;
    private Double availableAmount;

    private CardBuilder() {
    }

    public static CardBuilder anCard() {
        return new CardBuilder();
    }

    public CardBuilder withCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
        return this;
    }

    public CardBuilder withAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
        return this;
    }


    public Card build() {
        Card card = new Card();
        card.setCardnumber(cardnumber);
        card.setAvailableAmount(availableAmount);
        card.setTransactions(new ArrayList<>());
        return card;
    }
}
