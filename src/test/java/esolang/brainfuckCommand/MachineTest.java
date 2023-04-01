package esolang.brainfuckCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MachineTest {

    private static final byte[] eightBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
    private static final byte[] sixteenBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    @Test
    public void whenMemoryIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Machine(new byte[]{}, 0));
        Assertions.assertEquals("Memory is empty.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource
    public void whenPointerIsOutOfRange(int pointer, byte[] memory) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Machine(memory, pointer));
        Assertions.assertEquals("Pointer position out of range.", exception.getMessage());
    }

    static Stream<Arguments> whenPointerIsOutOfRange() {
        return Stream.of(
                arguments(-1, new byte[]{0}),
                arguments(1, new byte[]{0}),
                arguments(2, new byte[]{0}),
                arguments(8, eightBytes),
                arguments(15, eightBytes),
                arguments(16, sixteenBytes)
        );
    }
}