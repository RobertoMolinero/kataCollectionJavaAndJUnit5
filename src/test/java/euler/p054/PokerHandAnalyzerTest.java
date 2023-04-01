package euler.p054;

import euler.p054.entity.Card;
import euler.p054.entity.Game;
import euler.p054.entity.PokerHand;
import euler.p054.entity.PokerHandValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static euler.p054.entity.CardColor.CLUBS;
import static euler.p054.entity.CardColor.DIAMONDS;
import static euler.p054.entity.CardColor.HEARTS;
import static euler.p054.entity.CardColor.SPADES;
import static euler.p054.entity.CardSymbol.ACE;
import static euler.p054.entity.CardSymbol.EIGHT;
import static euler.p054.entity.CardSymbol.FIVE;
import static euler.p054.entity.CardSymbol.FOUR;
import static euler.p054.entity.CardSymbol.JACK;
import static euler.p054.entity.CardSymbol.KING;
import static euler.p054.entity.CardSymbol.NINE;
import static euler.p054.entity.CardSymbol.QUEEN;
import static euler.p054.entity.CardSymbol.SEVEN;
import static euler.p054.entity.CardSymbol.SIX;
import static euler.p054.entity.CardSymbol.TEN;
import static euler.p054.entity.CardSymbol.THREE;
import static euler.p054.entity.CardSymbol.TWO;
import static euler.p054.entity.PokerHandValue.FLUSH;
import static euler.p054.entity.PokerHandValue.FOUR_OF_A_KIND;
import static euler.p054.entity.PokerHandValue.FULL_HOUSE;
import static euler.p054.entity.PokerHandValue.PAIR;
import static euler.p054.entity.PokerHandValue.ROYAL_FLUSH;
import static euler.p054.entity.PokerHandValue.STRAIGHT;
import static euler.p054.entity.PokerHandValue.STRAIGHT_FLUSH;
import static euler.p054.entity.PokerHandValue.THREE_OF_A_KIND;
import static euler.p054.entity.PokerHandValue.TWO_PAIRS;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PokerHandAnalyzerTest {

    @ParameterizedTest
    @MethodSource
    @Disabled("Test files missing")
    void getListOfGamesFromFile(String filename, List<Game> game) {
        Assertions.assertEquals(game, PokerHandAnalyzer.getListOfGamesFromFile(filename));
    }

    static Stream<Arguments> getListOfGamesFromFile() {
        PokerHand pokerHandOneOne = new PokerHand(List.of(new Card(CLUBS, EIGHT), new Card(SPADES, TEN), new Card(CLUBS, KING), new Card(HEARTS, NINE), new Card(SPADES, FOUR)));
        PokerHand pokerHandOneTwo = new PokerHand(List.of(new Card(DIAMONDS, SEVEN), new Card(SPADES, TWO), new Card(DIAMONDS, FIVE), new Card(SPADES, THREE), new Card(CLUBS, ACE)));
        Game one = new Game(pokerHandOneOne, pokerHandOneTwo);

        PokerHand pokerHandTwoOne = new PokerHand(List.of(new Card(CLUBS, FIVE), new Card(DIAMONDS, ACE), new Card(DIAMONDS, FIVE), new Card(CLUBS, ACE), new Card(CLUBS, NINE)));
        PokerHand pokerHandTwoTwo = new PokerHand(List.of(new Card(CLUBS, SEVEN), new Card(HEARTS, FIVE), new Card(DIAMONDS, EIGHT), new Card(DIAMONDS, TEN), new Card(SPADES, KING)));
        Game two = new Game(pokerHandTwoOne, pokerHandTwoTwo);

        PokerHand pokerHandThreeOne = new PokerHand(List.of(new Card(HEARTS, THREE), new Card(HEARTS, SEVEN), new Card(SPADES, SIX), new Card(CLUBS, KING), new Card(SPADES, JACK)));
        PokerHand pokerHandThreeTwo = new PokerHand(List.of(new Card(HEARTS, QUEEN), new Card(DIAMONDS, TEN), new Card(CLUBS, JACK), new Card(DIAMONDS, TWO), new Card(SPADES, EIGHT)));
        Game three = new Game(pokerHandThreeOne, pokerHandThreeTwo);

        return Stream.of(
                arguments("src/test/resources/p054/p054_poker_oneGame.txt", List.of(one)),
                arguments("src/test/resources/p054/p054_poker_twoGames.txt", List.of(one, two)),
                arguments("src/test/resources/p054/p054_poker_threeGames.txt", List.of(one, two, three))
        );
    }

    @ParameterizedTest
    @MethodSource
    void createPokerHand(String[] pokerHandAsStringArray, PokerHand expectedPokerHand) {
        Assertions.assertEquals(expectedPokerHand, PokerHandAnalyzer.createPokerHand(pokerHandAsStringArray));
    }

    static Stream<Arguments> createPokerHand() {
        return Stream.of(
                arguments(new String[]{"8C", "TS", "KC", "9H", "4S"}, new PokerHand(List.of(new Card(CLUBS, EIGHT), new Card(SPADES, TEN), new Card(CLUBS, KING), new Card(HEARTS, NINE), new Card(SPADES, FOUR)))),
                arguments(new String[]{"7D", "2S", "5D", "3S", "AC"}, new PokerHand(List.of(new Card(DIAMONDS, SEVEN), new Card(SPADES, TWO), new Card(DIAMONDS, FIVE), new Card(SPADES, THREE), new Card(CLUBS, ACE)))),
                arguments(new String[]{"5C", "AD", "5D", "AC", "9C"}, new PokerHand(List.of(new Card(CLUBS, FIVE), new Card(DIAMONDS, ACE), new Card(DIAMONDS, FIVE), new Card(CLUBS, ACE), new Card(CLUBS, NINE)))),
                arguments(new String[]{"7C", "5H", "8D", "TD", "KS"}, new PokerHand(List.of(new Card(CLUBS, SEVEN), new Card(HEARTS, FIVE), new Card(DIAMONDS, EIGHT), new Card(DIAMONDS, TEN), new Card(SPADES, KING)))),
                arguments(new String[]{"3H", "7H", "6S", "KC", "JS"}, new PokerHand(List.of(new Card(HEARTS, THREE), new Card(HEARTS, SEVEN), new Card(SPADES, SIX), new Card(CLUBS, KING), new Card(SPADES, JACK)))),
                arguments(new String[]{"QH", "TD", "JC", "2D", "8S"}, new PokerHand(List.of(new Card(HEARTS, QUEEN), new Card(DIAMONDS, TEN), new Card(CLUBS, JACK), new Card(DIAMONDS, TWO), new Card(SPADES, EIGHT))))
        );
    }

    @ParameterizedTest
    @MethodSource
    void analysePokerHandIndividualValues(PokerHand pokerHand, List<PokerHandValue> pokerHandValues) {
        Assertions.assertEquals(pokerHandValues.contains(ROYAL_FLUSH), PokerHandAnalyzer.isRoyalFlush(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(STRAIGHT_FLUSH), PokerHandAnalyzer.isStraightFlush(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(FOUR_OF_A_KIND), PokerHandAnalyzer.isFourOfAKind(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(FULL_HOUSE), PokerHandAnalyzer.isFullHouse(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(FLUSH), PokerHandAnalyzer.isFlush(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(STRAIGHT), PokerHandAnalyzer.isStraight(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(THREE_OF_A_KIND), PokerHandAnalyzer.isThreeOfAKind(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(TWO_PAIRS), PokerHandAnalyzer.isTwoPairs(pokerHand));
        Assertions.assertEquals(pokerHandValues.contains(PAIR), PokerHandAnalyzer.isPair(pokerHand));
    }

    static Stream<Arguments> analysePokerHandIndividualValues() {

        PokerHand royalFlush = new PokerHand(List.of(
                new Card(CLUBS, TEN),
                new Card(CLUBS, JACK),
                new Card(CLUBS, QUEEN),
                new Card(CLUBS, KING),
                new Card(CLUBS, ACE)));

        PokerHand straightFlush = new PokerHand(List.of(
                new Card(DIAMONDS, SIX),
                new Card(DIAMONDS, SEVEN),
                new Card(DIAMONDS, EIGHT),
                new Card(DIAMONDS, NINE),
                new Card(DIAMONDS, TEN)));

        PokerHand fourOfAKind = new PokerHand(List.of(
                new Card(HEARTS, THREE),
                new Card(DIAMONDS, THREE),
                new Card(DIAMONDS, KING),
                new Card(CLUBS, THREE),
                new Card(SPADES, THREE)));

        PokerHand fullHouse = new PokerHand(List.of(
                new Card(CLUBS, FIVE),
                new Card(DIAMONDS, ACE),
                new Card(DIAMONDS, FIVE),
                new Card(CLUBS, ACE),
                new Card(SPADES, ACE)));

        PokerHand flush = new PokerHand(List.of(
                new Card(HEARTS, THREE),
                new Card(HEARTS, TWO),
                new Card(HEARTS, FIVE),
                new Card(HEARTS, QUEEN),
                new Card(HEARTS, JACK)));

        PokerHand straightAceAtStart = new PokerHand(List.of(
                new Card(CLUBS, FIVE),
                new Card(DIAMONDS, FOUR),
                new Card(DIAMONDS, THREE),
                new Card(CLUBS, TWO),
                new Card(SPADES, ACE)));

        PokerHand straightAceAtEnd = new PokerHand(List.of(
                new Card(CLUBS, TEN),
                new Card(DIAMONDS, JACK),
                new Card(DIAMONDS, QUEEN),
                new Card(CLUBS, KING),
                new Card(SPADES, ACE)));

        PokerHand straight = new PokerHand(List.of(
                new Card(CLUBS, SIX),
                new Card(DIAMONDS, SEVEN),
                new Card(DIAMONDS, EIGHT),
                new Card(CLUBS, NINE),
                new Card(SPADES, TEN)));

        PokerHand threeOfAKind = new PokerHand(List.of(
                new Card(CLUBS, FIVE),
                new Card(DIAMONDS, ACE),
                new Card(DIAMONDS, FOUR),
                new Card(CLUBS, ACE),
                new Card(SPADES, ACE)));

        PokerHand twoPairs = new PokerHand(List.of(
                new Card(CLUBS, FIVE),
                new Card(DIAMONDS, ACE),
                new Card(DIAMONDS, FIVE),
                new Card(CLUBS, ACE),
                new Card(CLUBS, NINE)));

        PokerHand pair = new PokerHand(List.of(
                new Card(HEARTS, FIVE),
                new Card(DIAMONDS, KING),
                new Card(DIAMONDS, FIVE),
                new Card(CLUBS, ACE),
                new Card(CLUBS, NINE)));

        return Stream.of(
                arguments(royalFlush, List.of(ROYAL_FLUSH, STRAIGHT_FLUSH, FLUSH, STRAIGHT)),
                arguments(straightFlush, List.of(STRAIGHT_FLUSH, FLUSH, STRAIGHT)),
                arguments(fourOfAKind, List.of(FOUR_OF_A_KIND)),
                arguments(fullHouse, List.of(FULL_HOUSE, THREE_OF_A_KIND, PAIR)),
                arguments(flush, List.of(FLUSH)),
                arguments(straightAceAtStart, List.of(STRAIGHT)),
                arguments(straightAceAtEnd, List.of(STRAIGHT)),
                arguments(straight, List.of(STRAIGHT)),
                arguments(threeOfAKind, List.of(THREE_OF_A_KIND)),
                arguments(twoPairs, List.of(TWO_PAIRS, PAIR)),
                arguments(pair, List.of(PAIR))
        );
    }
}
