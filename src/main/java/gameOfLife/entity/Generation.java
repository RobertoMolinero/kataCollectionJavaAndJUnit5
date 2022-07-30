package gameOfLife.entity;

public class Generation {

    private final World world;

    private final int time;

    public Generation(World world, int time) {
        this.world = world;
        this.time = time;
    }

    public World getWorld() {
        return world;
    }

    public int getTime() {
        return time;
    }

    public static final String LIVING_CELL = "\u25a0";
    public static final String DEAD_CELL = "\u25a1";

    public String getTextRepresentation() {

        int minX = world.getLivingCells().stream().map(Cell::getX).min(Integer::compare).orElse(0);
        int minY = world.getLivingCells().stream().map(Cell::getY).min(Integer::compare).orElse(0);
        int maxX = world.getLivingCells().stream().map(Cell::getX).max(Integer::compare).orElse(0);
        int maxY = world.getLivingCells().stream().map(Cell::getY).max(Integer::compare).orElse(0);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Generation: ").append(time).append(" ");
        stringBuilder.append("[X min = ").append(minX).append(" max = ").append(maxX).append("] ");
        stringBuilder.append("[Y min = ").append(minY).append(" max = ").append(maxY).append("]");
        stringBuilder.append("\n\n");

        if (world.isExtinct()) {
            stringBuilder.append("The world is extinct!");
            return stringBuilder.toString();
        }

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (world.getLivingCells().contains(new Cell(x, y))) {
                    stringBuilder.append(LIVING_CELL);
                } else {
                    stringBuilder.append(DEAD_CELL);
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
