package esolang.befunge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    public void getRandomDirection() {
        // act
        Direction randomDirection = Direction.getRandomDirection();

        // assert
        Assertions.assertNotNull(randomDirection);
    }
}