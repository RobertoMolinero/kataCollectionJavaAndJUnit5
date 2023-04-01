package kattis.rankproblem;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String numberOfTeamsAndGames = scanner.nextLine();
        int numberOfTeams = Integer.parseInt(numberOfTeamsAndGames.split(" ")[0]);
        int numberOfGames = Integer.parseInt(numberOfTeamsAndGames.split(" ")[1]);

        RankProblem rankProblem = new RankProblem();
        List<String> ranking = rankProblem.generateRanking(numberOfTeams);

        for (int x = 0; x < numberOfGames; x++) {
            // scanner.nextLine();
            String match = scanner.nextLine();
            String winningTeam = match.split(" ")[0];
            String losingTeam = match.split(" ")[1];

            ranking = rankProblem.calculateNewRanking(winningTeam, losingTeam, ranking);
        }

        System.out.print(String.join(" ", ranking));
    }
}
