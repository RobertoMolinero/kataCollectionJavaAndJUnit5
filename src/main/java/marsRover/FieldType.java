package marsRover;

public enum FieldType {
    EMPTY("\u25a1"),
    OBSTACLE("\u25a0");

    private final String representation;

    FieldType(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
