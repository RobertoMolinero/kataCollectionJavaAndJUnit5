package kattis.rankproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RankProblem {

    public List<String> generateRanking(int numberOfTeams) {
        return IntStream.rangeClosed(1, numberOfTeams).mapToObj(i -> String.format("T%d", i)).collect(Collectors.toList());
    }

    public List<String> calculateNewRanking(String winningTeam, String losingTeam, List<String> oldRanking) {
        ArrayList<String> newRanking = new ArrayList<>(oldRanking);

        int winningIndex = newRanking.indexOf(winningTeam);
        int losingIndex = newRanking.indexOf(losingTeam);

        if (winningIndex > losingIndex) {
            newRanking.remove(losingIndex);
            newRanking.add(winningIndex, losingTeam);
        }

        return newRanking;
    }
}
