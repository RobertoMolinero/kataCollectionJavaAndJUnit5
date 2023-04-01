package euler.p054.entity;

import java.util.List;
import java.util.Objects;

public class PokerHand {
    List<Card> cards;

    public PokerHand(List<Card> pokerHand) {
        this.cards = pokerHand;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Card c : cards) {
            stringBuilder.append(c.toString());
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return cards.equals(pokerHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
