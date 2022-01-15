package euler.p349.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DirectionTest {

    @ParameterizedTest
    @MethodSource
    void turnClockwise(Direction direction, Direction nextDirection) {
        assertEquals(nextDirection, direction.turnClockwise());
    }

    static Stream<Arguments> turnClockwise() {
        return Stream.of(
                arguments(Direction.TOP, Direction.RIGHT),
                arguments(Direction.RIGHT, Direction.BOTTOM),
                arguments(Direction.BOTTOM, Direction.LEFT),
                arguments(Direction.LEFT, Direction.TOP)
        );
    }

    @ParameterizedTest
    @MethodSource
    void turnCounterclockwise(Direction direction, Direction nextDirection) {
        assertEquals(nextDirection, direction.turnCounterclockwise());
    }

    static Stream<Arguments> turnCounterclockwise() {
        return Stream.of(
                arguments(Direction.TOP, Direction.LEFT),
                arguments(Direction.LEFT, Direction.BOTTOM),
                arguments(Direction.BOTTOM, Direction.RIGHT),
                arguments(Direction.RIGHT, Direction.TOP)
        );
    }
}
