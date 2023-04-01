package esolang.brainfuckCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ProgramTest {

    private static final String helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
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

    private static final String helloWorldWithOutComments = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.+++.";

    private static final String loop = "[-]";
    private static final String plus = ">[-]<[>+<-]";

    private static final String broken = ">]-";
    private static final String brokenTooManyClosingBrackets = ">[-]<[>+<-]]";
    private static final String brokenTooManyOpeningBrackets = ">[-<[>+<-]";

    @ParameterizedTest
    @MethodSource
    public void validate(String command) {
        // arrange + act
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Program(command));

        // assert
        Assertions.assertEquals("The brackets within the statement are not balanced.", exception.getMessage());
    }

    static Stream<Arguments> validate() {
        return Stream.of(
                arguments(broken),
                arguments(brokenTooManyClosingBrackets),
                arguments(brokenTooManyOpeningBrackets)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void validateAndBuildBracketMaps(String command, Map<Integer, Integer> expectedOpenCloseAssignment, Map<Integer, Integer> expectedCloseOpenAssignment) {
        // arrange + act
        Program sut = new Program(command);

        // assert
        Assertions.assertEquals(expectedOpenCloseAssignment, sut.getOpenCloseAssignment());
        Assertions.assertEquals(expectedCloseOpenAssignment, sut.getCloseOpenAssignment());
    }

    static Stream<Arguments> validateAndBuildBracketMaps() {
        return Stream.of(
                arguments(loop, Map.of(0, 2), Map.of(2, 0)),
                arguments(plus, Map.of(1, 3, 5, 10), Map.of(3, 1, 10, 5)),
                arguments(helloWorld, Map.of(8, 48, 14, 33, 43, 45), Map.of(48, 8, 33, 14, 45, 43)),
                arguments(helloWorldRecord, Map.of(1, 26, 6, 18, 42, 45), Map.of(26, 1, 18, 6, 45, 42))
        );
    }

    @Test
    public void reduceToInstructionSet() {
        // arrange + act
        Program sut = new Program(helloWorldWithComments);

        // assert
        Assertions.assertEquals(helloWorldWithOutComments, sut.getCommand());
    }
}