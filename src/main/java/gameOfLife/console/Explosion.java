package gameOfLife.console;

import gameOfLife.GameOfLife;
import gameOfLife.entity.Cell;
import gameOfLife.entity.Generation;
import gameOfLife.entity.World;

import java.util.ArrayList;
import java.util.List;

public class Explosion {

    public static void main(String[] args) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(1, 0));
        cells.add(new Cell(2, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(0, 2));
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 2));
        cells.add(new Cell(0, 4));
        cells.add(new Cell(0, 5));
        cells.add(new Cell(2, 4));
        cells.add(new Cell(2, 5));
        cells.add(new Cell(0, 6));
        cells.add(new Cell(1, 6));
        cells.add(new Cell(2, 6));

        World world = new World(cells);
        Generation generation = new Generation(world, 0);
        System.out.println(generation.getTextRepresentation());

        GameOfLife gameOfLife = new GameOfLife();

        do {
            Generation nextGeneration = gameOfLife.calculateNextGeneration(generation);
            System.out.println(nextGeneration.getTextRepresentation());
            generation = nextGeneration;
        } while (!generation.getWorld().isExtinct());
    }
}
