package gameOfLife;

import gameOfLife.entity.Cell;
import gameOfLife.entity.Generation;
import gameOfLife.entity.World;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GameOfLifeTest {

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

    private static final World full = new World(Arrays.asList(TOP_LEFT, TOP_CENTER, TOP_RIGHT, LEFT, CENTER, RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT));
    private static final World block = new World(Arrays.asList(LEFT, CENTER, BOTTOM_LEFT, BOTTOM_RIGHT));
    private static final World circle = new World(Arrays.asList(TOP_CENTER, LEFT, RIGHT, BOTTOM_CENTER));

    private static final World blinker_horizontal = new World(Arrays.asList(LEFT, CENTER, RIGHT));
    private static final World blinker_vertical = new World(Arrays.asList(TOP_CENTER, CENTER, BOTTOM_CENTER));

    @ParameterizedTest
    @MethodSource
    void willALivingCellStayAlive(World world, Cell cell, boolean isStayingAlive) {
        GameOfLife gameOfLife = new GameOfLife();
        assertEquals(isStayingAlive, gameOfLife.willALivingCellStayAlive(world, cell, world.countLivingNeighbors(cell)));
    }

    static Stream<Arguments> willALivingCellStayAlive() {
        return Stream.of(
                // underpopulation
                arguments(new World(List.of(CENTER)), CENTER, false),
                arguments(new World(Arrays.asList(CENTER, TOP_LEFT)), CENTER, false),
                arguments(new World(Arrays.asList(CENTER, BOTTOM_CENTER)), CENTER, false),
                arguments(new World(Arrays.asList(CENTER, BOTTOM_RIGHT)), CENTER, false),

                // ideal environment
                arguments(new World(Arrays.asList(CENTER, TOP_LEFT, BOTTOM_RIGHT)), CENTER, true),
                arguments(new World(Arrays.asList(CENTER, LEFT, RIGHT)), CENTER, true),
                arguments(new World(Arrays.asList(CENTER, LEFT, RIGHT)), CENTER, true),
                arguments(block, CENTER, true),
                arguments(new World(Arrays.asList(CENTER, TOP_CENTER, TOP_RIGHT, BOTTOM_CENTER)), CENTER, true),
                arguments(new World(Arrays.asList(CENTER, LEFT, TOP_RIGHT, BOTTOM_CENTER)), CENTER, true),

                // overpopulation
                arguments(full, CENTER, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void willADeadCellBeginToLive(World world, Cell cell, boolean willBeginToLive) {
        GameOfLife gameOfLife = new GameOfLife();
        assertEquals(willBeginToLive, gameOfLife.willADeadCellBeginToLive(world, cell, world.countLivingNeighbors(cell)));
    }

    static Stream<Arguments> willADeadCellBeginToLive() {
        return Stream.of(
                // underpopulation
                arguments(new World(Arrays.asList(TOP_LEFT, BOTTOM_RIGHT)), CENTER, false),
                arguments(new World(Arrays.asList(TOP_LEFT, RIGHT)), CENTER, false),
                arguments(new World(Arrays.asList(LEFT, BOTTOM_LEFT)), CENTER, false),

                // ideal environment
                arguments(new World(Arrays.asList(TOP_LEFT, RIGHT, BOTTOM_RIGHT)), CENTER, true),
                arguments(new World(Arrays.asList(TOP_LEFT, LEFT, RIGHT)), CENTER, true),
                arguments(new World(Arrays.asList(LEFT, RIGHT, BOTTOM_LEFT)), CENTER, true),

                // overpopulation
                arguments(circle, CENTER, false),
                arguments(allDirections, CENTER, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateNextGeneration(Generation generation, Generation nextGeneration) {
        GameOfLife gameOfLife = new GameOfLife();
        Generation result = gameOfLife.calculateNextGeneration(generation);
        assertTrue(nextGeneration.getWorld().getLivingCells().containsAll(result.getWorld().getLivingCells()));
        assertTrue(result.getWorld().getLivingCells().containsAll(nextGeneration.getWorld().getLivingCells()));
    }

    static Stream<Arguments> calculateNextGeneration() {
        return Stream.of(
                arguments(new Generation(blinker_horizontal, 0), new Generation(blinker_vertical, 1)),
                arguments(new Generation(blinker_vertical, 1), new Generation(blinker_horizontal, 2))
        );
    }
}