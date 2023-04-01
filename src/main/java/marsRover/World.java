package marsRover;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class World {
    private final Map<Position, FieldType> map;

    public World(int mapSize) {
        this.map = new TreeMap<>();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Position position = new Position(i, j);
                this.map.put(position, FieldType.EMPTY);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return this.map.equals(world.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.map);
    }

    @Override
    public String toString() {
        return "World{" +
                "map=" + map +
                '}';
    }
}
