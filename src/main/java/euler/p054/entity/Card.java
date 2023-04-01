package euler.p054.entity;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private CardColor cardColor;
    private CardSymbol cardSymbol;

    public Card(CardColor cardColor, CardSymbol cardSymbol) {
        this.cardColor = cardColor;
        this.cardSymbol = cardSymbol;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardSymbol getCardSymbol() {
        return cardSymbol;
    }

    @Override
    public String toString() {
        return "[" + cardSymbol + "#" + cardColor + "]";
    }

    @Override
    public int compareTo(Card o) {
        Integer thisOrdinal = cardSymbol.ordinal();
        Integer thatOrdinal = o.getCardSymbol().ordinal();
        return thisOrdinal.compareTo(thatOrdinal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardColor == card.cardColor && cardSymbol == card.cardSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardColor, cardSymbol);
    }
}
