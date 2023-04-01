package euler.p054.entity;

import java.util.Objects;

public class Game {

    private PokerHand one;
    private PokerHand two;

    public Game(PokerHand one, PokerHand two) {
        this.one = one;
        this.two = two;
    }

    public PokerHand getOne() {
        return one;
    }

    public PokerHand getTwo() {
        return two;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return one.equals(game.one) && two.equals(game.two);
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two);
    }
}
