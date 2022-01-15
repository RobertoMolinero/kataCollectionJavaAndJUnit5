package euler.p349;

import euler.p349.entity.*;

import java.util.ArrayList;
import java.util.List;

public class LangtonAlgorithm {

    public static Generation calculateNextGeneration(Generation generation) {

        int actualTime = generation.time();
        List<Cell> actualBlackCells = generation.world().blackCells();
        Direction actualDirection = generation.world().ant().direction();
        Position actualPosition = generation.world().ant().position();

        int newTime = determineNewTime(actualTime);
        List<Cell> newBlackCells = colorCell(actualPosition, actualBlackCells);
        Direction newDirection = determineNewDirection(actualDirection, newBlackCells.size() < actualBlackCells.size());
        Position newPosition = determineNewPosition(actualPosition, newDirection);

        return new Generation(new World(new Ant(newPosition, newDirection), newBlackCells), newTime);
    }

    private static int determineNewTime(int actualTime) {
        return actualTime + 1;
    }

    private static List<Cell> colorCell(Position actualPosition, List<Cell> actualBlackCells) {
        List<Cell> newBlackCells = new ArrayList<>(actualBlackCells);

        if (!newBlackCells.remove(new Cell(actualPosition)))
            newBlackCells.add(new Cell(actualPosition));

        return newBlackCells;
    }

    private static Direction determineNewDirection(Direction actualDirection, boolean elementRemoved) {
        if (!elementRemoved)
            return actualDirection.turnClockwise();

        return actualDirection.turnCounterclockwise();
    }

    private static Position determineNewPosition(Position actualPosition, Direction newDirection) {
        return new Position(actualPosition.x() + newDirection.getHorizontal(), actualPosition.y() + newDirection.getVertical());
    }
}
