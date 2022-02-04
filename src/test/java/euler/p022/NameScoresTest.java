package euler.p022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NameScoresTest {

    @ParameterizedTest
    @MethodSource
    void readNamesFromFile(String fileName, int expectedSize, List<String> expectedNames) {
        List<String> namesFromFile = NameScores.readNamesFromFile(fileName);
        assertEquals(expectedSize, namesFromFile.size());
        assertEquals(expectedNames, namesFromFile);
    }

    static Stream<Arguments> readNamesFromFile() {
        return Stream.of(
                arguments("src/test/resources/p022/threeNames.txt", 3, List.of("MARY", "PATRICIA", "LINDA")),
                arguments("src/test/resources/p022/fiveNames.txt", 5, List.of("MARY", "PATRICIA", "LINDA", "BARBARA", "ELIZABETH")),
                arguments("src/test/resources/p022/tenNames.txt", 10, List.of("MARY", "PATRICIA", "LINDA", "BARBARA", "ELIZABETH", "JENNIFER", "MARIA", "SUSAN", "MARGARET", "DOROTHY"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateSingleScore(String name, int expectedScore) {
        assertEquals(expectedScore, NameScores.calculateSingleScore(name));
    }

    static Stream<Arguments> calculateSingleScore() {
        return Stream.of(
                arguments("MARY", 13 + 1 + 18 + 25),
                arguments("PATRICIA", 16 + 1 + 20 + 18 + 9 + 3 + 9 + 1),
                arguments("LINDA", 12 + 9 + 14 + 4 + 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateCompleteScore(List<String> names, int expectedScore) {
        assertEquals(expectedScore, NameScores.calculateCompleteScore(names));
    }

    static Stream<Arguments> calculateCompleteScore() {
        return Stream.of(
                arguments(List.of("MARY"), 13 + 1 + 18 + 25),
                arguments(List.of("PATRICIA"), 16 + 1 + 20 + 18 + 9 + 3 + 9 + 1),
                arguments(List.of("LINDA"), 12 + 9 + 14 + 4 + 1),
                arguments(List.of("MARY", "PATRICIA"), (13 + 1 + 18 + 25) + (16 + 1 + 20 + 18 + 9 + 3 + 9 + 1) * 2),
                arguments(List.of("MARY", "LINDA"), (13 + 1 + 18 + 25) + (12 + 9 + 14 + 4 + 1) * 2),
                arguments(List.of("MARY", "PATRICIA", "LINDA"), (13 + 1 + 18 + 25) + (16 + 1 + 20 + 18 + 9 + 3 + 9 + 1) * 2 + (12 + 9 + 14 + 4 + 1) * 3)
        );
    }

    @Test
    void calculateNameScores() {
        assertEquals(871198282, NameScores.calculateNameScores());
    }
}
