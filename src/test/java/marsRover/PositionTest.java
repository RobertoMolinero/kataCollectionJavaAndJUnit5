package marsRover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class PositionTest {

    @ParameterizedTest
    @MethodSource
    public void
    checkComparison(List<Position> positionList, Position firstElement, Position lastElement) {
        // act
        positionList.sort(Position::compareTo);

        // assert
        Assertions.assertEquals(firstElement, positionList.get(0));
        Assertions.assertEquals(lastElement, positionList.get(1));
    }

    static Stream<Arguments> checkComparison() {
        Position p00 = new Position(0, 0);
        Position p01 = new Position(0, 1);
        Position p10 = new Position(1, 0);
        Position p11 = new Position(1, 1);

        return Stream.of(
                arguments(new ArrayList<>(List.of(p00, p01)), p00, p01),
                arguments(new ArrayList<>(List.of(p01, p00)), p00, p01),
                arguments(new ArrayList<>(List.of(p00, p10)), p00, p10),
                arguments(new ArrayList<>(List.of(p10, p00)), p00, p10),
                arguments(new ArrayList<>(List.of(p01, p11)), p01, p11),
                arguments(new ArrayList<>(List.of(p11, p01)), p01, p11)
        );
    }
}