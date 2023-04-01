package esolang.brainfuckCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class InterpreterTest {

    private static final byte[] eightBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
    private static final byte[] sixteenBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    private static final String helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";

    private static final String helloWorldCache48And72And96 = "++[>++++[>+++[>++>+++>++++<<<-]<-]<-]" +
            ">>>>." +
            ">+++++." +
            "+++++++.." +
            "+++." +
            "<<----." +
            "------------." +
            ">+++++++++++++++." +
            ">." +
            "+++." +
            "------." +
            "--------." +
            "<<+.";

    // 24 - 48 - 72 - 96
    // 24 - 48 - 72 - 96 - 120

    private static final String helloWorldRecord = "+[-->-[>>+>-----<<]<--<---]>-.>>>+.>>..+++[.>]<<<<.+++.------.<<-.>>>>+.";

    private static final String helloWorldWithComments = "++++++++++" +
            "[" +
            ">+++++++>++++++++++>+++>+<<<<-" +
            "]                       Schleife zur Vorbereitung der Textausgabe" +
            ">++.                    Ausgabe von 'H'" +
            ">+.                     Ausgabe von 'e'" +
            "+++++++.                'l'" +
            ".                       'l'" +
            "+++.                    'o'" +
            ">++.                    Leerzeichen" +
            "<<+++++++++++++++.      'W'" +
            ">.                      'o'" +
            "+++.                    'r'" +
            "------.                 'l'" +
            "--------.               'd'" +
            ">+.                     '!'" +
            ">.                      Zeilenvorschub" +
            "+++.                    Wagenrücklauf";

    private static final String loop = "[-]";
    private static final String plus = ">[-]<[>+<-]";

    @ParameterizedTest
    @MethodSource
    void decrementMemoryPointer(int pointer, byte[] memory, int expectedPointerPosition) {
        // arrange
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(helloWorld);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand("<");

        // assert
        Assertions.assertEquals(expectedPointerPosition, sut.machine().getMemoryPointer());
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
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(helloWorld);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand(">");

        // assert
        Assertions.assertEquals(expectedPointerPosition, sut.machine().getMemoryPointer());
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
    void decrementMemoryValue(int pointer, byte[] memory, byte[] expectedNewMemory) {
        // arrange
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(helloWorld);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand("-");

        // assert
        Assertions.assertArrayEquals(expectedNewMemory, sut.machine().getMemory());
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
    void incrementMemoryValue(int pointer, byte[] memory, byte[] expectedNewMemory) {
        // arrange
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(helloWorld);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand("+");

        // assert
        Assertions.assertArrayEquals(expectedNewMemory, sut.machine().getMemory());
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
    void output(int pointer, byte[] memory, String expectedOutput) {
        // arrange
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(helloWorld);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand(".");

        // assert
        Assertions.assertEquals(sut.machine().getOutputBuffer().toString(), expectedOutput);
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
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(command, commandPointer);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand("[");

        // assert
        Assertions.assertEquals(expectedNewCommandPointer, sut.program().getCommandPointer());
    }

    static Stream<Arguments> startLoop() {
        return Stream.of(
                arguments(0, new byte[]{0}, 0, loop, 2),
                arguments(1, new byte[]{0, 1, 2, 3}, 0, loop, 0),
                arguments(0, new byte[]{0}, 1, plus, 3),
                arguments(0, new byte[]{0}, 5, plus, 10),
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
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(command, commandPointer);
        Interpreter sut = new Interpreter(machine, program);

        // act
        sut.executeCommand("]");

        // assert
        Assertions.assertEquals(expectedNewCommandPointer, sut.program().getCommandPointer());
    }

    static Stream<Arguments> endLoop() {
        return Stream.of(
                arguments(0, new byte[]{1}, 2, loop, 0),
                arguments(1, new byte[]{0, 0, 0, 0}, 2, loop, 2),
                arguments(0, new byte[]{1}, 3, plus, 1),
                arguments(0, new byte[]{1}, 10, plus, 5),
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
        Machine machine = new Machine(memory, pointer);
        Program program = new Program(command);
        Interpreter sut = new Interpreter(machine, program);

        // act
        String output = sut.interpret();

        // assert
        Assertions.assertEquals(expectedOutput, output);
    }

    static Stream<Arguments> interpret() {
        return Stream.of(
//                arguments(0, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, helloWorld, "Hello World!\n"),
//                arguments(0, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, helloWorldRecord, "Hello, World!"),
//                arguments(0, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, helloWorldWithComments, "Hello World!\n\r"),
                arguments(0, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, helloWorldCache48And72And96, "Hello, World!")
        );
    }
}