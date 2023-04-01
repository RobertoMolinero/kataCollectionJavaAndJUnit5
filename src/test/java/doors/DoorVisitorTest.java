package doors;

import static org.junit.jupiter.api.Assertions.*;

class DoorVisitorTest {

    // Todo: Covert from Spock to JUnit5

    /*

    package doors


import spock.lang.Specification
import spock.lang.Unroll

class DoorVisitorTest extends Specification {

    @Unroll
    def "The method initDoors with parameter #count creates #count doors with state 'closed'"() {
        given:
        def sut = new DoorVisitor()
        def openDoor = new Door()
        openDoor.setOpen(true)

        when:
        def doors = sut.initDoors(count)

        then:
        doors.size() == count
        !doors.contains(openDoor)

        where:
        count << [1, 5, 10, 25, 50, 100]
    }

    @Unroll
    def "The method printVisitedDoors supplies the expected pattern #result"() {
        given:
        def sut = new DoorVisitor()

        when:
        def doorsAsString = sut.printVisitedDoors(doors)

        then:
        doorsAsString == result

        where:
        doors                                               | result
        [getClosedDoor(), getOpenDoor(), getClosedDoor()]   | "010"
        [getOpenDoor(), getClosedDoor(), getOpenDoor()]     | "101"
        [getClosedDoor(), getClosedDoor(), getClosedDoor()] | "000"
        [getOpenDoor(), getOpenDoor(), getOpenDoor()]       | "111"
    }

    def "getOpenDoor"() {
        def openDoor = new Door()
        openDoor.setOpen(true)
        return openDoor
    }

    def "getClosedDoor"() {
        return new Door()
    }

    @Unroll
    def "The method visitDoorsWithStepWith with #countDoors doors and step width #stepWidth creates 10 doors (#doorsOpen doors with state 'open') and the pattern '#pattern'"() {
        given:
        def sut = new DoorVisitor()
        def doors = sut.initDoors(countDoors)

        when:
        def visitedDoors = sut.visitDoorsWithStepWith(doors, stepWidth)

        then:
        visitedDoors.size() == countDoors
        visitedDoors.stream().filter({ d -> d.isOpen() }).count() == doorsOpen
        sut.printVisitedDoors(visitedDoors) == pattern

        where:
        countDoors | stepWidth | doorsOpen | pattern
        10         | 1         | 10        | "1111111111"
        10         | 2         | 5         | "0101010101"
        10         | 3         | 3         | "0010010010"
        10         | 5         | 2         | "0000100001"
        10         | 10        | 1         | "0000000001"
        10         | 12        | 0         | "0000000000"
    }

    @Unroll
    def "The method visitDoors with #countDoors doors creates #countDoors doors (#doorsOpen doors with state 'open') and the pattern '#pattern'"() {
        given:
        def sut = new DoorVisitor()
        def doors = sut.initDoors(countDoors)

        when:
        def visitedDoors = sut.visitDoors(doors)

        then:
        visitedDoors.size() == countDoors
        visitedDoors.stream().filter({ d -> d.isOpen() }).count() == doorsOpen
        sut.printVisitedDoors(visitedDoors) == pattern

        where:
        countDoors | doorsOpen | pattern
        1          | 1         | "1"
        2          | 1         | "10"
        3          | 1         | "100"
        4          | 2         | "1001"
        100        | 10        | "1001000010000001000000001000000000010000000000001000000000000001000000000000000010000000000000000001"
    }
}

     */
}