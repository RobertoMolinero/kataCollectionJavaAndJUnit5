package euler.p054;

import euler.p054.entity.Card;
import euler.p054.entity.CardColor;
import euler.p054.entity.CardSymbol;
import euler.p054.entity.Game;
import euler.p054.entity.PokerHand;
import euler.p054.entity.PokerHandValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PokerHandAnalyzer {

    private PokerHandAnalyzer() {
    }

    public static List<Game> getListOfGamesFromFile(String filename) {

        try {
            List<String> allLines = Files.readAllLines(Path.of(filename));
            List<Game> games = new ArrayList<>();

            for (String line : allLines) {
                String[] splitComplete = line.split(" ");

                var splitOne = new String[]{
                        splitComplete[0], splitComplete[1], splitComplete[2], splitComplete[3], splitComplete[4]
                };

                var splitTwo = new String[]{
                        splitComplete[5], splitComplete[6], splitComplete[7], splitComplete[8], splitComplete[9]
                };

                var pokerHandOne = createPokerHand(splitOne);
                var pokerHandTwo = createPokerHand(splitTwo);
                games.add(new Game(pokerHandOne, pokerHandTwo));
            }

            return games;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static PokerHand createPokerHand(String[] splittedCards) {

        List<Card> cards = new ArrayList<>();

        for (String cardAsString : splittedCards) {
            var cardSymbol = CardSymbol.getCardSymbolByAbbreviation(cardAsString.substring(0, 1));
            var cardColor = CardColor.getCardColorByAbbreviation(cardAsString.substring(1, 2));
            var card = new Card(cardColor, cardSymbol);
            cards.add(card);
        }

        return new PokerHand(cards);
    }


    public static boolean didPlayerOneWin(PokerHand one, PokerHand two) {

        var pokerHandValueOne = analysePokerHandValue(one);
        var pokerHandValueTwo = analysePokerHandValue(two);

        if (pokerHandValueOne.ordinal() != pokerHandValueTwo.ordinal())
            return pokerHandValueOne.ordinal() > pokerHandValueTwo.ordinal();



        return false;
    }

    public static int analysePokerHandFineValuation(PokerHand pokerHand, PokerHandValue pokerHandValue) {

        return 0;
    }

    public static PokerHandValue analysePokerHandValue(PokerHand pokerHand) {

        if (isRoyalFlush(pokerHand)) return PokerHandValue.ROYAL_FLUSH;
        if (isStraightFlush(pokerHand)) return PokerHandValue.STRAIGHT_FLUSH;
        if (isFourOfAKind(pokerHand)) return PokerHandValue.FOUR_OF_A_KIND;
        if (isFullHouse(pokerHand)) return PokerHandValue.FULL_HOUSE;
        if (isFlush(pokerHand)) return PokerHandValue.FLUSH;
        if (isStraight(pokerHand)) return PokerHandValue.STRAIGHT;
        if (isThreeOfAKind(pokerHand)) return PokerHandValue.THREE_OF_A_KIND;
        if (isTwoPairs(pokerHand)) return PokerHandValue.TWO_PAIRS;
        if (isPair(pokerHand)) return PokerHandValue.PAIR;

        return PokerHandValue.HIGH_CARD;
    }

    public static Map<CardSymbol, Long> analyseCardSymbols(PokerHand pokerHand) {
        Set<CardSymbol> cardSymbols = pokerHand.getCards().stream().map(Card::getCardSymbol).collect(Collectors.toSet());
        Map<CardSymbol, Long> countCardSymbols = new EnumMap<>(CardSymbol.class);

        for (CardSymbol symbol : cardSymbols) {
            long count = pokerHand.getCards().stream().map(Card::getCardSymbol).filter(c -> c.equals(symbol)).count();
            countCardSymbols.put(symbol, count);
        }

        return countCardSymbols;
    }

    public static boolean isRoyalFlush(PokerHand pokerHand) {
        List<Card> cards = new ArrayList<>(pokerHand.getCards());
        Collections.sort(cards);
        return isStraightFlush(pokerHand) && cards.get(4).getCardSymbol().equals(CardSymbol.ACE);
    }

    public static boolean isStraightFlush(PokerHand pokerHand) {
        return isStraight(pokerHand) && isFlush(pokerHand);
    }

    public static boolean isFourOfAKind(PokerHand pokerHand) {
        return analyseCardSymbols(pokerHand).containsValue(4L);
    }

    public static boolean isFullHouse(PokerHand pokerHand) {
        return isThreeOfAKind(pokerHand) && isPair(pokerHand);
    }

    public static boolean isFlush(PokerHand pokerHand) {
        List<CardColor> cardColors = pokerHand.getCards().stream().map(Card::getCardColor).collect(Collectors.toList());
        Set<CardColor> cardColorsUnique = Set.copyOf(cardColors);
        return cardColorsUnique.size() == 1;
    }

    public static boolean isStraight(PokerHand pokerHand) {
        List<Card> cards = new ArrayList<>(pokerHand.getCards());
        Collections.sort(cards);

        var highestCardAce = false;

        if (cards.get(4).getCardSymbol().equals(CardSymbol.ACE)) {
            cards.remove(4);
            highestCardAce = true;
        }

        for (var i = 0; i < cards.size() - 1; i++) {
            int ordinalOfCardIncremented = cards.get(i).getCardSymbol().ordinal() + 1;
            int ordinalOfNextCard = cards.get(i + 1).getCardSymbol().ordinal();

            if (ordinalOfCardIncremented != ordinalOfNextCard) {
                return false;
            }
        }

        return !highestCardAce || cards.get(0).getCardSymbol().equals(CardSymbol.TWO) || cards.get(3).getCardSymbol().equals(CardSymbol.KING);
    }

    public static boolean isThreeOfAKind(PokerHand pokerHand) {
        return analyseCardSymbols(pokerHand).containsValue(3L);
    }

    public static boolean isTwoPairs(PokerHand pokerHand) {
        return analyseCardSymbols(pokerHand).values().stream().filter(v -> v == 2).count() == 2;
    }

    public static boolean isPair(PokerHand pokerHand) {
        return analyseCardSymbols(pokerHand).containsValue(2L);
    }
}
