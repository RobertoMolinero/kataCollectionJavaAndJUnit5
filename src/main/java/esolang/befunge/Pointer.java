package esolang.befunge;

import java.util.Objects;

public record Pointer(Position position, Direction direction) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pointer pointer = (Pointer) o;
        return Objects.equals(position, pointer.position) && direction == pointer.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }
}
