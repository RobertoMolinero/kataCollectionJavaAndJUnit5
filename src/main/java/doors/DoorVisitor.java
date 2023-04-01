package doors;

import java.util.ArrayList;
import java.util.List;

public class DoorVisitor {

    public List<Door> initDoors(int count) {
        List<Door> doors = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            doors.add(new Door());
        }

        return doors;
    }

    public String printVisitedDoors(List<Door> doors) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Door d : doors) {
            stringBuilder.append(d.isOpen() ? "1" : "0");
        }

        return stringBuilder.toString();
    }

    public List<Door> visitDoorsWithStepWith(List<Door> doors, int stepWidth) {

        if (stepWidth > doors.size()) return doors;

        for (int i = (stepWidth - 1); i < doors.size(); i += stepWidth) {
            doors.get(i).invertOpen();
        }

        return doors;
    }

    public List<Door> visitDoors(List<Door> doors) {

        List<Door> visitedDoors = doors;

        for (int i = 1; i <= doors.size(); i++) {
            visitedDoors = visitDoorsWithStepWith(visitedDoors, i);
        }

        return visitedDoors;
    }
}
