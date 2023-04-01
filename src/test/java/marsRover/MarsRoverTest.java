package marsRover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class MarsRoverTest {

    @ParameterizedTest
    @MethodSource
    public void move(World world, MarsRover marsRover, Position expectedNewPosition) {
        // act
        MarsRover result = marsRover.nextStep(world, "M");

        // assert
        Assertions.assertEquals(expectedNewPosition, result.getPosition());
    }

    static Stream<Arguments> move() {
        return Stream.of(
                arguments(new World(10), new MarsRover(new Position(0, 0), Direction.EAST), new Position(1, 0)),
                arguments(new World(10), new MarsRover(new Position(1, 2), Direction.WEST), new Position(0, 2)),
                arguments(new World(10), new MarsRover(new Position(3, 5), Direction.NORTH), new Position(3, 4)),
                arguments(new World(10), new MarsRover(new Position(8, 8), Direction.SOUTH), new Position(8, 9))
        );
    }
}