package esolang.befunge;

import java.util.OptionalInt;
import java.util.Random;

public enum Direction {

    NORTH("^", 0, -1),
    SOUTH("v", 0, 1),
    WEST("<", -1, 0),
    EAST(">", 1, 0);

    private final int horizontal;

    private final int vertical;

    private final String representation;

    Direction(String representation, int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.representation = representation;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public String getRepresentation() {
        return representation;
    }

    public static Direction getDirection(String representation) throws IllegalArgumentException {
        for (Direction value : Direction.values()) {
            if (representation.equals(value.getRepresentation())) return value;
        }

        throw new IllegalArgumentException();
    }

    public static Direction getRandomDirection() {
        Random random = new Random();
        OptionalInt randomOrdinal = random.ints(0, Direction.values().length).findFirst();

        if (randomOrdinal.isEmpty())
            throw new IllegalStateException("Random variable not determinable.");

        return Direction.values()[randomOrdinal.getAsInt()];
    }

    @Override
    public String toString() {
        return representation;
    }
}
