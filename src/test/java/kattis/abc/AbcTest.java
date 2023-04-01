package kattis.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class AbcTest {

    private static final String code1337 = "acaaccaaaac";

    private static final String codeHelloWorld =
            """
                    $aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaa
                    aaaaaaaaaaaaaaaaaaaaaaacaaaaaaaccaaacnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                    aacbbbbbbbbbbbbcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaa
                    aaaaaaaaaaaaaaaacaaacbbbbbbcbbbbbbbbcnaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac
                    """;

    @ParameterizedTest
    @MethodSource
    void executeSingleInstructions(String code, Integer expectedAcc, Boolean expectedStringMode, String expectedOutput) {
        // arrange
        Abc sut = new Abc();

        // act
        sut.interpret(code);

        // assert
        Assertions.assertEquals(expectedAcc, sut.getAcc());
        Assertions.assertEquals(expectedStringMode, sut.getStringMode());
        Assertions.assertEquals(expectedOutput, sut.getOutput().toString());
    }

    static Stream<Arguments> executeSingleInstructions() {
        return Stream.of(
                arguments("a", 1, Boolean.FALSE, ""),
                arguments("aa", 2, Boolean.FALSE, ""),
                arguments("aaa", 3, Boolean.FALSE, ""),
                arguments("aaab", 2, Boolean.FALSE, ""),
                arguments("aabb", 0, Boolean.FALSE, ""),
                arguments("b", -1, Boolean.FALSE, ""),
                arguments("ac", 1, Boolean.FALSE, "1"),
                arguments("aaac", 3, Boolean.FALSE, "3"),
                arguments("abbbc", -2, Boolean.FALSE, "-2"),
                arguments("aaad", -3, Boolean.FALSE, ""),
                arguments("bbd", 2, Boolean.FALSE, ""),
                arguments("aaabn", 0, Boolean.FALSE, ""),
                arguments("abban", 0, Boolean.FALSE, ""),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac", 65, Boolean.FALSE, "65"),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa$c", 65, Boolean.TRUE, "A"),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa;", 65, Boolean.FALSE, "65 A")
        );
    }


    @ParameterizedTest
    @MethodSource
    void executeCompleteProgram(String code, Integer expectedAcc, Boolean expectedStringMode, String expectedOutput) {
        // arrange
        Abc sut = new Abc();

        // act
        sut.interpret(code);

        // assert
        Assertions.assertEquals(expectedAcc, sut.getAcc());
        Assertions.assertEquals(expectedStringMode, sut.getStringMode());
        Assertions.assertEquals(expectedOutput, sut.getOutput().toString());
    }

    static Stream<Arguments> executeCompleteProgram() {
        return Stream.of(
                arguments(code1337, 7, Boolean.FALSE, "1337"),
                arguments(codeHelloWorld, 33, Boolean.TRUE, "Hello, World!")
        );
    }
}