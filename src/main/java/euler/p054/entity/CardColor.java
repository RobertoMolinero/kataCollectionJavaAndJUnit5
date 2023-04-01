package euler.p054.entity;

public enum CardColor {
    CLUBS("C"), SPADES("S"), HEARTS("H"), DIAMONDS("D");

    private String abbreviation;

    CardColor(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static CardColor getCardColorByAbbreviation(String abbreviation) {
        for (CardColor value : CardColor.values()) {
            if (value.abbreviation.equals(abbreviation)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Color " + abbreviation + " not found.");
    }
}
