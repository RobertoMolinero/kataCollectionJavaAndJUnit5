package euler.p349;

import euler.p349.entity.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LangtonAlgorithmTest {

    @ParameterizedTest
    @MethodSource
    void determineNewTime(Generation generation, int expectedNewTime) {
        Generation nextGeneration = LangtonAlgorithm.calculateNextGeneration(generation);
        assertEquals(expectedNewTime, nextGeneration.time());
    }

    static Stream<Arguments> determineNewTime() {
        Position position = new Position(0, 0);
        Ant ant = new Ant(position, Direction.TOP);
        List<Cell> noCell = new ArrayList<>();

        return Stream.of(
                arguments(new Generation(new World(ant, noCell), 0), 1),
                arguments(new Generation(new World(ant, noCell), 1), 2),
                arguments(new Generation(new World(ant, noCell), 2), 3)
        );
    }

    @ParameterizedTest
    @MethodSource
    void colorCell(Generation generation, int expectedBlackCells) {
        Generation nextGeneration = LangtonAlgorithm.calculateNextGeneration(generation);
        assertEquals(expectedBlackCells, nextGeneration.world().blackCells().size());
    }

    static Stream<Arguments> colorCell() {
        Position position = new Position(0, 0);
        Ant ant = new Ant(position, Direction.TOP);
        List<Cell> noCell = new ArrayList<>();
        List<Cell> oneCell = List.of(new Cell(new Position(0, 0)));

        return Stream.of(
                arguments(new Generation(new World(ant, noCell), 0), 1),
                arguments(new Generation(new World(ant, oneCell), 0), 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    void determineNewDirection(Generation generation, Direction expectedNewDirection) {
        Generation nextGeneration = LangtonAlgorithm.calculateNextGeneration(generation);
        assertEquals(expectedNewDirection, nextGeneration.world().ant().direction());
    }

    static Stream<Arguments> determineNewDirection() {
        Position position = new Position(0, 0);
        Ant ant = new Ant(position, Direction.TOP);
        List<Cell> noCell = new ArrayList<>();
        List<Cell> oneCell = List.of(new Cell(new Position(0, 0)));

        return Stream.of(
                arguments(new Generation(new World(ant, noCell), 0), Direction.RIGHT),
                arguments(new Generation(new World(ant, oneCell), 0), Direction.LEFT)
        );
    }

    @ParameterizedTest
    @MethodSource
    void determineNewPosition(Generation generation, Position expectedNewPosition) {
        Generation nextGeneration = LangtonAlgorithm.calculateNextGeneration(generation);
        assertEquals(expectedNewPosition, nextGeneration.world().ant().position());
    }

    static Stream<Arguments> determineNewPosition() {
        Position position = new Position(0, 0);
        Ant ant = new Ant(position, Direction.TOP);
        List<Cell> noCell = new ArrayList<>();
        List<Cell> oneCell = List.of(new Cell(new Position(0, 0)));

        return Stream.of(
                arguments(new Generation(new World(ant, noCell), 0), new Position(1, 0)),
                arguments(new Generation(new World(ant, oneCell), 0), new Position(-1, 0))
        );
    }
}
