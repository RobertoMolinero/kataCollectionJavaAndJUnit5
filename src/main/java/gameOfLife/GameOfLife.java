package gameOfLife;

import gameOfLife.entity.Cell;
import gameOfLife.entity.Generation;
import gameOfLife.entity.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLife {

    public boolean willALivingCellStayAlive(World world, Cell cell, int livingNeighbors) {
        return world.getLivingCells().contains(cell) && Arrays.asList(2, 3).contains(livingNeighbors);
    }

    public boolean willADeadCellBeginToLive(World world, Cell cell, int livingNeighbors) {
        return !world.getLivingCells().contains(cell) && livingNeighbors == 3;
    }

    public Generation calculateNextGeneration(Generation generation) {
        List<Cell> allCellsToCalculate = generation.getWorld().getAllCellsToCalculate();

        World newWorld = new World(new ArrayList<>());

        for (Cell cell : allCellsToCalculate) {
            int livingNeighbors = generation.getWorld().countLivingNeighbors(cell);
            if (willALivingCellStayAlive(generation.getWorld(), cell, livingNeighbors)) {
                newWorld.getLivingCells().add(cell);
            }
            if (willADeadCellBeginToLive(generation.getWorld(), cell, livingNeighbors)) {
                newWorld.getLivingCells().add(cell);
            }
        }

        return new Generation(newWorld, generation.getTime() + 1);
    }
}
