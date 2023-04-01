package marsRover;

public enum Direction {
    NORTH("\u25b3", 0, -1),
    SOUTH("\u25bd", 0, 1),
    WEST("\u25c1", -1, 0),
    EAST("\u25b7", 1, 0);

    private final String representation;

    private final int horizontal;

    private final int vertical;

    Direction(String representation, int horizontal, int vertical) {
        this.representation = representation;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    @Override
    public String toString() {
        return representation;
    }
}
