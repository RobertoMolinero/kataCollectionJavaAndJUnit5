package gameOfLife.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WorldTest {

    private static final Cell TOP_LEFT = new Cell(0, 0);
    private static final Cell TOP_CENTER = new Cell(1, 0);
    private static final Cell TOP_RIGHT = new Cell(2, 0);
    private static final Cell LEFT = new Cell(0, 1);
    private static final Cell CENTER = new Cell(1, 1);
    private static final Cell RIGHT = new Cell(2, 1);
    private static final Cell BOTTOM_LEFT = new Cell(0, 2);
    private static final Cell BOTTOM_CENTER = new Cell(1, 2);
    private static final Cell BOTTOM_RIGHT = new Cell(2, 2);

    private static final World allDirections = new World(Arrays.asList(TOP_LEFT, TOP_CENTER, TOP_RIGHT, LEFT, RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT));
    private static final World block = new World(Arrays.asList(LEFT, CENTER, BOTTOM_LEFT, BOTTOM_CENTER));
    private static final World circle = new World(Arrays.asList(TOP_CENTER, LEFT, RIGHT, BOTTOM_CENTER));
    private static final World cross = new World(Arrays.asList(TOP_LEFT, TOP_RIGHT, CENTER, BOTTOM_LEFT, BOTTOM_RIGHT));
    private static final World empty = new World(List.of());

    @ParameterizedTest
    @MethodSource
    void countLivingNeighbors(World world, int livingNeighbors) {
        assertEquals(livingNeighbors, world.countLivingNeighbors(new Cell(1, 1)));
    }

    static Stream<Arguments> countLivingNeighbors() {
        return Stream.of(
                arguments(allDirections, 8),
                arguments(block, 3),
                arguments(circle, 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAllCellsToCalculate(World world, int cellsToCalculate) {
        assertEquals(cellsToCalculate, world.getAllCellsToCalculate().size());
    }

    static Stream<Arguments> getAllCellsToCalculate() {
        return Stream.of(
                arguments(allDirections, 25),
                arguments(block, 16),
                arguments(circle, 21),
                arguments(cross, 25),
                arguments(new World(Arrays.asList(TOP_RIGHT, CENTER)), 14),
                arguments(new World(Arrays.asList(TOP_RIGHT, CENTER, BOTTOM_LEFT)), 19),
                arguments(new World(Arrays.asList(TOP_RIGHT, CENTER, BOTTOM_RIGHT)), 18)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isExtinct(World world, boolean isExtinct) {
        assertEquals(isExtinct, world.isExtinct());
    }

    static Stream<Arguments> isExtinct() {
        return Stream.of(
                arguments(empty, true),
                arguments(allDirections, false),
                arguments(block, false),
                arguments(circle, false),
                arguments(cross, false)
        );
    }
}