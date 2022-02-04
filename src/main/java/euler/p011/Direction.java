package euler.p011;

public enum Direction {

    RIGHT(1, 0, 0, 3, 0, 0),
    DOWN(0, 1, 0, 0, 0, 3),
    RIGHT_DOWN(1, 1, 0, 3, 0, 3),
    RIGHT_UP(1, -1, 0, 3, 3, 0);

    private final int horizontal;
    private final int vertical;

    private final int marginLeft;
    private final int marginRight;
    private final int marginTop;
    private final int marginBottom;

    Direction(int horizontal, int vertical, int marginLeft, int marginRight, int marginTop, int marginBottom) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }
}
