package euler.p349.entity;

public enum Direction {

    TOP(0, -1),
    RIGHT(1, 0),
    BOTTOM(0, 1),
    LEFT(-1, 0);

    int horizontal;

    int vertical;

    Direction(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public Direction turnClockwise() {
        return Direction.values()[(this.ordinal() + 1) % 4];
    }

    public Direction turnCounterclockwise() {
        return Direction.values()[(this.ordinal() + 3) % 4];
    }
}
