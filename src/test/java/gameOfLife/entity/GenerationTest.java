package gameOfLife.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerationTest {

    @Test
    void getTextRepresentation() {
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

        assertEquals("""
                Generation: 0 [X min = 0 max = 2] [Y min = 0 max = 6]

                ■■■
                ■□■
                ■□■
                □□□
                ■□■
                ■□■
                ■■■
                """, generation.getTextRepresentation());
    }
}