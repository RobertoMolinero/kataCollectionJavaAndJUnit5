package euler.p022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NameScores {

    public static List<String> readNamesFromFile(String fileName) {

        try {
            String text = Files.readString(Path.of(fileName));
            String[] split = text.replace("\r\n", "").split(",");
            return Arrays.stream(split).map(s -> s.replace("\"", "")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static int calculateSingleScore(String name) {

        char[] chars = name.toCharArray();
        int sum = 0;

        for (char c : chars) {
            sum += (c - ('A' - 1));
        }

        return sum;
    }

    public static int calculateCompleteScore(List<String> name) {
        int sum = 0;

        for (int i = 0; i < name.size(); i++) {
            int calculateSingleScore = calculateSingleScore(name.get(i));
            sum += (calculateSingleScore * (i + 1));
        }

        return sum;
    }

    public static int calculateNameScores() {
        List<String> namesFromFile = readNamesFromFile("src/main/java/euler/p022/names.txt");
        Collections.sort(namesFromFile);
        return calculateCompleteScore(namesFromFile);
    }
}
