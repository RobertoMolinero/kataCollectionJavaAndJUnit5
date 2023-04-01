package esolang.brainfuck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BrainfuckTest {

    private static final byte[] eightBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
    private static final byte[] sixteenBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    private static final String helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
    private static final String helloWorldRecord = "+[-->-[>>+>-----<<]<--<---]>-.>>>+.>>..+++[.>]<<<<.+++.------.<<-.>>>>+.";
    private static final String loop = "[-]";
    private static final String plus = "+++>[-]<[>+<-]";

    private static final String broken = ">]-";
    private static final String brokenTooManyClosingBrackets = ">[-]<[>+<-]]";
    private static final String brokenTooManyOpeningBrackets = ">[-<[>+<-]";

    @Test
    public void whenMemoryIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Brainfuck(0, new byte[]{}));
        Assertions.assertEquals("Memory is empty.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource
    public void whenPointerIsOutOfRange(int pointer, byte[] memory) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Brainfuck(pointer, memory));
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

    @ParameterizedTest
    @MethodSource
    public void validate(String command, boolean expectedResult) {
        // arrange
        Brainfuck sut = new Brainfuck();

        // act
        boolean validate = sut.validate(command);

        // assert
        Assertions.assertEquals(expectedResult, validate);
    }

    static Stream<Arguments> validate() {
        return Stream.of(
                arguments(helloWorld, true),
                arguments(helloWorldRecord, true),
                arguments(loop, true),
                arguments(plus, true),
                arguments(broken, false),
                arguments(brokenTooManyClosingBrackets, false),
                arguments(brokenTooManyOpeningBrackets, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void validateAndBuildBracketMaps(String command, Map<Integer, Integer> expectedOpenCloseAssignment, Map<Integer, Integer> expectedCloseOpenAssignment) {
        // arrange
        Brainfuck sut = new Brainfuck();

        // act
        sut.validate(command);

        // assert
        Assertions.assertEquals(expectedOpenCloseAssignment, sut.getOpenCloseAssignment());
        Assertions.assertEquals(expectedCloseOpenAssignment, sut.getCloseOpenAssignment());
    }

    static Stream<Arguments> validateAndBuildBracketMaps() {
        return Stream.of(
                arguments(loop, Map.of(0, 2), Map.of(2, 0)),
                arguments(plus, Map.of(4, 6, 8, 13), Map.of(6, 4, 13, 8)),
                arguments(helloWorld, Map.of(8, 48, 14, 33, 43, 45), Map.of(48, 8, 33, 14, 45, 43)),
                arguments(helloWorldRecord, Map.of(1, 26, 6, 18, 42, 45), Map.of(26, 1, 18, 6, 45, 42))
        );
    }

    @ParameterizedTest
    @MethodSource
    void decrementMemoryPointer(int pointer, byte[] memory, int expectedPointerPosition) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);

        // act
        sut.decrementMemoryPointer();

        // assert
        Assertions.assertEquals(expectedPointerPosition, sut.getMemoryPointer());
    }

    static Stream<Arguments> decrementMemoryPointer() {
        return Stream.of(
                arguments(5, eightBytes, 4),
                arguments(3, eightBytes, 2),
                arguments(0, eightBytes, 7),
                arguments(15, sixteenBytes, 14),
                arguments(8, sixteenBytes, 7),
                arguments(0, sixteenBytes, 15)
        );
    }

    @ParameterizedTest
    @MethodSource
    void incrementMemoryPointer(int pointer, byte[] memory, int expectedPointerPosition) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);

        // act
        sut.incrementMemoryPointer();

        // assert
        Assertions.assertEquals(expectedPointerPosition, sut.getMemoryPointer());
    }

    static Stream<Arguments> incrementMemoryPointer() {
        return Stream.of(
                arguments(5, eightBytes, 6),
                arguments(3, eightBytes, 4),
                arguments(0, eightBytes, 1),
                arguments(7, eightBytes, 0),
                arguments(15, sixteenBytes, 0),
                arguments(8, sixteenBytes, 9),
                arguments(0, sixteenBytes, 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void incrementMemoryValue(int pointer, byte[] memory, byte[] expectedNewMemory) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);

        // act
        sut.incrementMemoryValue();

        // assert
        Assertions.assertArrayEquals(expectedNewMemory, sut.getMemory());
    }

    static Stream<Arguments> incrementMemoryValue() {
        return Stream.of(
                arguments(0, new byte[]{-8, 5, 9, 4}, new byte[]{-7, 5, 9, 4}),
                arguments(1, new byte[]{7, 6, 7, 7}, new byte[]{7, 7, 7, 7}),
                arguments(2, new byte[]{0, 1, 2, 3}, new byte[]{0, 1, 3, 3}),
                arguments(3, new byte[]{5, 6, 4, 3}, new byte[]{5, 6, 4, 4})
        );
    }

    @ParameterizedTest
    @MethodSource
    void decrementMemoryValue(int pointer, byte[] memory, byte[] expectedNewMemory) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);

        // act
        sut.decrementMemoryValue();

        // assert
        Assertions.assertArrayEquals(expectedNewMemory, sut.getMemory());
    }

    static Stream<Arguments> decrementMemoryValue() {
        return Stream.of(
                arguments(0, new byte[]{6, 2, 9, -4}, new byte[]{5, 2, 9, -4}),
                arguments(1, new byte[]{7, 5, -8, 1}, new byte[]{7, 4, -8, 1}),
                arguments(2, new byte[]{0, 8, -7, 3}, new byte[]{0, 8, -8, 3}),
                arguments(3, new byte[]{-2, 6, 0, 4}, new byte[]{-2, 6, 0, 3})
        );
    }

    @ParameterizedTest
    @MethodSource
    void output(int pointer, byte[] memory, String expectedOutput) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);

        // act
        sut.output();

        // assert
        Assertions.assertEquals(sut.getOutputBuffer().toString(), expectedOutput);
    }

    static Stream<Arguments> output() {
        return Stream.of(
                arguments(0, new byte[]{87, 2, 9, -4}, "W"),
                arguments(1, new byte[]{7, 111, -8, 1}, "o"),
                arguments(2, new byte[]{0, 8, 114, 3}, "r"),
                arguments(3, new byte[]{-2, 6, 0, 108}, "l"),
                arguments(0, new byte[]{100, 6, 0, 4}, "d")
        );
    }

    @ParameterizedTest
    @MethodSource
    void startLoop(int pointer, byte[] memory, int commandPointer, String command, int expectedNewCommandPointer) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);
        sut.validate(command);

        // act
        int newCommandPointer = sut.startLoop(commandPointer);

        // assert
        Assertions.assertEquals(expectedNewCommandPointer, newCommandPointer);
    }

    static Stream<Arguments> startLoop() {
        return Stream.of(
                arguments(0, new byte[]{0}, 0, loop, 2),
                arguments(1, new byte[]{0, 1, 2, 3}, 0, loop, 0),
                arguments(0, new byte[]{0}, 4, plus, 6),
                arguments(0, new byte[]{0}, 8, plus, 13),
                arguments(0, new byte[]{1}, 5, plus, 5),
                arguments(2, new byte[]{5, -7, 9, -3}, 5, plus, 5),
                arguments(3, new byte[]{0, 0, 0, 0}, 14, helloWorld, 33),
                arguments(3, new byte[]{0, 0, 0, 0}, 43, helloWorld, 45),
                arguments(1, new byte[]{0, 0}, 8, helloWorld, 48)
        );
    }

    @ParameterizedTest
    @MethodSource
    void endLoop(int pointer, byte[] memory, int commandPointer, String command, int expectedNewCommandPointer) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);
        sut.validate(command);

        // act
        int newCommandPointer = sut.endLoop(commandPointer);

        // assert
        Assertions.assertEquals(expectedNewCommandPointer, newCommandPointer);
    }

    static Stream<Arguments> endLoop() {
        return Stream.of(
                arguments(0, new byte[]{1}, 2, loop, 0),
                arguments(1, new byte[]{0, 0, 0, 0}, 2, loop, 2),
                arguments(0, new byte[]{1}, 6, plus, 4),
                arguments(0, new byte[]{1}, 13, plus, 8),
                arguments(0, new byte[]{0}, 10, plus, 10),
                arguments(2, new byte[]{0, 0, 0, 0}, 10, plus, 10),
                arguments(3, new byte[]{0, 1, 2, 3}, 33, helloWorld, 14),
                arguments(3, new byte[]{0, 1, 2, 3}, 45, helloWorld, 43),
                arguments(3, new byte[]{0, 1, 2, 3}, 48, helloWorld, 8)
        );
    }

    @ParameterizedTest
    @MethodSource
    void interpret(int pointer, byte[] memory, String command, String expectedOutput) {
        // arrange
        Brainfuck sut = new Brainfuck(pointer, memory);

        // act
        String output = sut.interpret(command);

        // assert
        Assertions.assertEquals(expectedOutput, output);
    }

    static Stream<Arguments> interpret() {
        return Stream.of(
                arguments(0, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, helloWorld, "Hello World!\n"),
                arguments(0, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, helloWorldRecord, "Hello, World!")
        );
    }
}