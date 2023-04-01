package euler.p054.entity;

public enum CardSymbol {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private String abbreviation;

    CardSymbol(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static CardSymbol getCardSymbolByAbbreviation(String abbreviation) {
        for (CardSymbol value : CardSymbol.values()) {
            if (value.abbreviation.equals(abbreviation)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Symbol " + abbreviation + " not found.");
    }
}
