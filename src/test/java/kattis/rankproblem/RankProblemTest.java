package kattis.rankproblem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class RankProblemTest {

    @ParameterizedTest
    @MethodSource
    void generateRanking(int numberOfTeams, List<String> expectedRanking) {
        RankProblem sut = new RankProblem();
        Assertions.assertEquals(expectedRanking, sut.generateRanking(numberOfTeams));
    }

    static Stream<Arguments> generateRanking() {
        return Stream.of(
                arguments(1, List.of("T1")),
                arguments(2, List.of("T1", "T2")),
                arguments(3, List.of("T1", "T2", "T3")),
                arguments(4, List.of("T1", "T2", "T3", "T4")),
                arguments(5, List.of("T1", "T2", "T3", "T4", "T5")),
                arguments(6, List.of("T1", "T2", "T3", "T4", "T5", "T6")),
                arguments(7, List.of("T1", "T2", "T3", "T4", "T5", "T6", "T7")),
                arguments(8, List.of("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateNewRanking(String winningTeam, String losingTeam, List<String> oldRanking, List<String> expectedNewRanking) {
        RankProblem sut = new RankProblem();
        Assertions.assertEquals(expectedNewRanking, sut.calculateNewRanking(winningTeam, losingTeam, oldRanking));
    }

    static Stream<Arguments> calculateNewRanking() {
        List<String> case0_step0 = List.of("T1", "T2", "T3", "T4", "T5");
        List<String> case0_step1 = List.of("T2", "T3", "T4", "T1", "T5");
        List<String> case0_step2 = List.of("T2", "T3", "T4", "T1", "T5");
        List<String> case0_step3 = List.of("T2", "T4", "T1", "T5", "T3");

        List<String> case1_step0 = List.of("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8");
        List<String> case1_step1 = List.of("T2", "T3", "T4", "T1", "T5", "T6", "T7", "T8");
        List<String> case1_step2 = List.of("T3", "T4", "T1", "T2", "T5", "T6", "T7", "T8");
        List<String> case1_step3 = List.of("T4", "T1", "T2", "T3", "T5", "T6", "T7", "T8");
        List<String> case1_step4 = List.of("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8");

        return Stream.of(
                arguments("T4", "T1", case0_step0, case0_step1),
                arguments("T3", "T1", case0_step1, case0_step2),
                arguments("T5", "T3", case0_step2, case0_step3),
                arguments("T4", "T1", case1_step0, case1_step1),
                arguments("T1", "T2", case1_step1, case1_step2),
                arguments("T2", "T3", case1_step2, case1_step3),
                arguments("T3", "T4", case1_step3, case1_step4)
        );
    }
}