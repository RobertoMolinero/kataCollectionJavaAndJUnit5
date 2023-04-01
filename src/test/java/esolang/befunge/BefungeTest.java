package esolang.befunge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class BefungeTest {

    private static final String helloWorldSimple =
            """
                    "!dlroW olleH"v
                     @,,,,,,,,,,,,<
                    """;

    private static final String helloWorld = "\"!dlroW olleH\">:#,_@";

    private static final String helloWorldExtended = """
            91+70pv
            v p173<        v  <
            >"!dlroW olleH">,:|
                 v:-1g17      <
            ^ p17_$70g,v
            ^_ #&@#p173<
            """;

    private static final String addition = "4 3 + . @";

    private static final String addition2d =
            """
                    v   > . v
                    2   +   @
                    > 5 ^
                    """;

    private static final String addition2dCompact =
            """
                    v*>.v
                    1*+*@
                    >8^**
                    """;

    private static final String fibonacci =
            """
                    0.1>:.:00p+00g\\v
                       ^           <
                    """;

    private static final Map<Position, String> mapAddition = Map.of(
            new Position(0, 0), "4",
            new Position(1, 0), " ",
            new Position(2, 0), "3",
            new Position(3, 0), " ",
            new Position(4, 0), "+",
            new Position(5, 0), " ",
            new Position(6, 0), ".",
            new Position(7, 0), " ",
            new Position(8, 0), "@"
    );

    private static final Map<Position, String> mapAddition2d = new HashMap<>() {
        {
            put(new Position(0, 0), "v");
            put(new Position(1, 0), " ");
            put(new Position(2, 0), " ");
            put(new Position(3, 0), " ");
            put(new Position(4, 0), ">");
            put(new Position(5, 0), " ");
            put(new Position(6, 0), ".");
            put(new Position(7, 0), " ");
            put(new Position(8, 0), "v");
            put(new Position(0, 1), "2");
            put(new Position(1, 1), " ");
            put(new Position(2, 1), " ");
            put(new Position(3, 1), " ");
            put(new Position(4, 1), "+");
            put(new Position(5, 1), " ");
            put(new Position(6, 1), " ");
            put(new Position(7, 1), " ");
            put(new Position(8, 1), "@");
            put(new Position(0, 2), ">");
            put(new Position(1, 2), " ");
            put(new Position(2, 2), "5");
            put(new Position(3, 2), " ");
            put(new Position(4, 2), "^");
        }
    };

    private static final Map<Position, String> mapAddition2dCompact = new HashMap<>() {
        {
            put(new Position(0, 0), "v");
            put(new Position(1, 0), "*");
            put(new Position(2, 0), ">");
            put(new Position(3, 0), ".");
            put(new Position(4, 0), "v");
            put(new Position(0, 1), "1");
            put(new Position(1, 1), "*");
            put(new Position(2, 1), "+");
            put(new Position(3, 1), "*");
            put(new Position(4, 1), "@");
            put(new Position(0, 2), ">");
            put(new Position(1, 2), "8");
            put(new Position(2, 2), "^");
            put(new Position(3, 2), "*");
            put(new Position(4, 2), "*");
        }
    };

    @ParameterizedTest
    @MethodSource
    public void init(String program, Map<Position, String> expectedMap) {
        // arrange
        Befunge sut = new Befunge();

        // act
        sut.init(program);

        // assert
        Assertions.assertEquals(expectedMap, sut.programMap);
    }

    static Stream<Arguments> init() {
        return Stream.of(
                arguments(addition, mapAddition),
                arguments(addition2d, mapAddition2d),
                arguments(addition2dCompact, mapAddition2dCompact)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpretNumbers(String program, ArrayDeque<Integer> expectedStack) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertTrue(sut.stack.containsAll(expectedStack) && expectedStack.containsAll(sut.stack));
    }

    static Stream<Arguments> interpretNumbers() {
        return Stream.of(
                arguments("4@", new ArrayDeque<>(List.of(Integer.valueOf("4")))),
                arguments("23@", new ArrayDeque<>(List.of(Integer.valueOf("3"), Integer.valueOf("2")))),
                arguments("123@", new ArrayDeque<>(List.of(Integer.valueOf("1"), Integer.valueOf("2"), Integer.valueOf("3")))),
                arguments("999@", new ArrayDeque<>(List.of(Integer.valueOf("9"), Integer.valueOf("9"), Integer.valueOf("9"))))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpretNavigation(String program, Pointer expectedPointer) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertTrue(sut.stack.isEmpty());
        Assertions.assertEquals(expectedPointer, sut.pointer);
    }

    static Stream<Arguments> interpretNavigation() {
        return Stream.of(
                arguments("""
                        >@""", new Pointer(new Position(1, 0), Direction.EAST)),
                arguments("""
                        v
                        @""", new Pointer(new Position(0, 1), Direction.SOUTH)),
                arguments("""
                        v
                        >@""", new Pointer(new Position(1, 1), Direction.EAST)),
                arguments("""
                        v>@
                        >^""", new Pointer(new Position(2, 0), Direction.EAST)),
                arguments("""
                        v>v
                        >^v
                        @<<""", new Pointer(new Position(0, 2), Direction.WEST)),
                arguments("""
                        v @
                         >^
                         ^<
                        > ^""", new Pointer(new Position(2, 0), Direction.NORTH)),
                arguments("0_@", new Pointer(new Position(2, 0), Direction.EAST)),
                arguments("3_@.", new Pointer(new Position(2, 0), Direction.WEST)),
                arguments("""
                        v
                        0 > 3.@
                        > |
                          > 5.@
                        """, new Pointer(new Position(6, 3), Direction.EAST)),
                arguments("""
                        v > v
                        3 . v
                        > | @
                          > @
                        """, new Pointer(new Position(4, 2), Direction.SOUTH)),
                arguments(">#5@", new Pointer(new Position(3, 0), Direction.EAST)),
                arguments("""
                        >#v v
                          @ #
                          @ <
                          @ <
                        """, new Pointer(new Position(2, 3), Direction.WEST))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpretRandomNavigation(String program, Position expectedPosition, Direction expectedDirection, Direction expectedDirectionAlternative) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertTrue(sut.stack.isEmpty());
        Assertions.assertEquals(expectedPosition, sut.pointer.position());

        Assertions.assertTrue(sut.pointer.direction().equals(expectedDirection) || sut.pointer.direction().equals(expectedDirectionAlternative));
    }

    static Stream<Arguments> interpretRandomNavigation() {
        return Stream.of(
                arguments("""
                        > v
                          ? @
                          ^
                        """, new Position(4, 1), Direction.EAST, Direction.WEST),
                arguments("""
                        v @
                        > ? <
                        """, new Position(2, 0), Direction.NORTH, Direction.SOUTH)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpretStackOperation(String program, ArrayDeque<Integer> expectedStack, Pointer expectedPointer) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertTrue(sut.stack.containsAll(expectedStack));
        Assertions.assertTrue(expectedStack.containsAll(sut.stack));

        String expectedStackAsString = String.join("", expectedStack.toString());
        String actualStackAsString = String.join("", sut.stack.toString());

        Assertions.assertEquals(expectedStackAsString, actualStackAsString);
        Assertions.assertEquals(expectedPointer, sut.pointer);
    }

    static Stream<Arguments> interpretStackOperation() {
        Integer bOne = Integer.valueOf("1");
        Integer bTwo = Integer.valueOf("2");

        Pointer expectedPointer = new Pointer(new Position(4, 0), Direction.EAST);

        String duplicateCommand = ">12:@";
        Deque<Integer> duplicateStackResult = new ArrayDeque<>(List.of(bTwo, bTwo, bOne));

        String swapCommand = ">12\\@";
        Deque<Integer> swapStackResult = new ArrayDeque<>(List.of(bOne, bTwo));

        String getCommand = ">12$@";
        Deque<Integer> getStackResult = new ArrayDeque<>(List.of(Integer.valueOf("1")));

        return Stream.of(
                arguments(duplicateCommand, duplicateStackResult, expectedPointer),
                arguments(swapCommand, swapStackResult, expectedPointer),
                arguments(getCommand, getStackResult, expectedPointer)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpretOutputOperation(String program, String expectedOutput, Pointer expectedPointer) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertTrue(sut.stack.isEmpty());
        Assertions.assertEquals(expectedOutput, sut.output.toString().trim());
        Assertions.assertEquals(expectedPointer, sut.pointer);
    }

    static Stream<Arguments> interpretOutputOperation() {
        Pointer expectedPointer = new Pointer(new Position(5, 0), Direction.EAST);

        String printAsNumberCommand = ">89*.@";
        String printAsNumberOutput = "72";

        String printAsCharacterCommand = ">89*,@";
        String printAsCharacterOutput = "H";

        return Stream.of(
                arguments(printAsNumberCommand, printAsNumberOutput, expectedPointer),
                arguments(printAsCharacterCommand, printAsCharacterOutput, expectedPointer)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void applyMathematicalOperator(String program, String expectedOutput) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertEquals(expectedOutput, sut.output.toString().trim());
    }

    static Stream<Arguments> applyMathematicalOperator() {
        String addition = ">12+.@";
        String subtraction = ">35-.@";
        String multiplication = ">56*.@";
        String division = ">73/.@";
        String modulo = ">94%.@";
        String not0 = ">0!.@";
        String not1 = ">1!.@";
        String greaterThan0 = ">12`.@";
        String greaterThan0e = ">22`.@";
        String greaterThan1 = ">21`.@";

        return Stream.of(
                arguments(addition, "3"),
                arguments(subtraction, "-2"),
                arguments(multiplication, "30"),
                arguments(division, "2"),
                arguments(modulo, "1"),
                arguments(not0, "1"),
                arguments(not1, "0"),
                arguments(greaterThan0, "0"),
                arguments(greaterThan0e, "0"),
                arguments(greaterThan1, "1")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void readArea(String program, ArrayDeque<Integer> expectedStack) {
        // arrange
        Befunge sut = new Befunge();
        Map<Position, String> programMapInitial = sut.init(program);

        // act
        sut.interpret();

        // assert
        String expectedStackAsString = String.join("", expectedStack.toString());
        String actualStackAsString = String.join("", sut.stack.toString());

        Assertions.assertEquals(expectedStackAsString, actualStackAsString);
        Assertions.assertEquals(programMapInitial, sut.programMap);
    }

    static Stream<Arguments> readArea() {
        String g72 = """
                v @ <
                2 H g
                > 1 ^
                """;

        String g56 = """
                >  #8 v
                v 4   <
                > 0 g @
                """;

        return Stream.of(
                arguments(g72, new ArrayDeque<>(List.of((int) "H".charAt(0)))),
                arguments(g56, new ArrayDeque<>(List.of((int) "8".charAt(0)))),
                arguments(g56, new ArrayDeque<>(List.of((int) "8".charAt(0))))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void modifyArea(String program, String programModified) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        Befunge expected = new Befunge();
        expected.init(programModified);

        // act
        sut.interpret();

        // assert
        Assertions.assertEquals(expected.programMap, sut.programMap);
    }

    static Stream<Arguments> modifyArea() {
        String g64 = ">88*80p <";
        String g64Modified = ">88*80p @";

        return Stream.of(
                arguments(g64, g64Modified)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void inputCharacter(String program, Deque<String> arguments, Integer expectedValue) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program, arguments);

        // act
        sut.interpret();

        // assert
        Assertions.assertTrue(sut.stack.contains(expectedValue));
        Assertions.assertEquals(1, sut.stack.size());
    }

    static Stream<Arguments> inputCharacter() {
        String inputNumber = "&@";
        String inputAscii = "~@";

        return Stream.of(
                arguments(inputNumber, new ArrayDeque<>(List.of("1")), Integer.valueOf("1")),
                arguments(inputNumber, new ArrayDeque<>(List.of("A")), Integer.valueOf("0")),
                arguments(inputNumber, new ArrayDeque<>(), Integer.valueOf("0")),
                arguments(inputAscii, new ArrayDeque<>(List.of("1")), Integer.valueOf("49")),
                arguments(inputAscii, new ArrayDeque<>(List.of("A")), Integer.valueOf("65")),
                arguments(inputAscii, new ArrayDeque<>(), Integer.valueOf("-1"))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpret(String program, String expectedOutput) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret();

        // assert
        Assertions.assertEquals(expectedOutput, sut.output.toString());
    }

    static Stream<Arguments> interpret() {
        return Stream.of(
                arguments(helloWorldSimple, "Hello World!"),
                arguments(helloWorld, "Hello World!"),
                arguments(helloWorldExtended, "Hello World!Hello World!Hello World! ")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void interpretSteps(String program, int steps, String expectedOutput) {
        // arrange
        Befunge sut = new Befunge();
        sut.init(program);

        // act
        sut.interpret(steps);

        // assert
        Assertions.assertEquals(expectedOutput, sut.output.toString());
    }

    static Stream<Arguments> interpretSteps() {
        return Stream.of(
                arguments(fibonacci, 500, "0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 ")
        );
    }
}
