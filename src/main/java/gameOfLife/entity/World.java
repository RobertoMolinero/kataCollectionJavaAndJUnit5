package gameOfLife.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World {

    private List<Cell> livingCells;

    public World(List<Cell> livingCells) {
        this.livingCells = livingCells;
    }

    public List<Cell> getLivingCells() {
        return livingCells;
    }

    public int countLivingNeighbors(Cell referencePoint) {
        int x = referencePoint.getX();
        int y = referencePoint.getY();

        int count = 0;

        for (Direction value : Direction.values()) {
            if (livingCells.contains(new Cell(x + value.horizontal, y + value.vertical))) {
                count += 1;
            }
        }

        return count;
    }

    public List<Cell> getAllCellsToCalculate() {
        Set<Cell> neighbors = new HashSet<>();

        for (Cell cell : livingCells) {
            for (Direction value : Direction.values()) {
                neighbors.add(new Cell(cell.getX() + value.horizontal, cell.getY() + value.vertical));
            }
        }

        return List.copyOf(neighbors);
    }

    public boolean isExtinct() {
        return livingCells.isEmpty();
    }
}
