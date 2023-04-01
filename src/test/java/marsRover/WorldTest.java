package marsRover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Disabled("Work in progress")
class WorldTest {

    @ParameterizedTest
    @MethodSource
    public void checkRepresentation(int size, String expectedRepresentation) {
        // arrange
        World world = new World(size);

        // act
        String toString = world.toString();

        // assert
        Assertions.assertEquals(expectedRepresentation, toString);
    }

    static Stream<Arguments> checkRepresentation() {
        return Stream.of(
                arguments(1, "\u25a1"),
                arguments(2, "\u25a1\u25a1\n\u25a1\u25a1"),
                arguments(3, "\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1"),
                arguments(4, "\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1"),
                arguments(5, "\u25a1\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\u25a1")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void checkRepresentationWithMarsRover(int size, String expectedRepresentation, MarsRover marsRover) {
        // arrange
        World world = new World(size);

        // act
        String toString = world.toString();

        // assert
        Assertions.assertEquals(expectedRepresentation, toString);
    }

    static Stream<Arguments> checkRepresentationWithMarsRover() {
        return Stream.of(
                arguments(1, "\u25b3", new MarsRover(new Position(0, 0), Direction.NORTH)),
                arguments(2, "\u25a1\u25c1\n\u25a1\u25a1", new MarsRover(new Position(1, 0), Direction.WEST)),
                arguments(3, "\u25a1\u25a1\u25a1\n\u25a1\u25bd\u25a1\n\u25a1\u25a1\u25a1", new MarsRover(new Position(1, 1), Direction.SOUTH)),
                arguments(4, "\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25a1\n\u25a1\u25a1\u25a1\u25b7", new MarsRover(new Position(3, 3), Direction.EAST))
        );
    }
}
