package euler.p013;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LargeSum {

    public static long calculateLargeSum(String filename) {

        try {
            List<String> allLines = Files.readAllLines(Path.of(filename));
            long sum = allLines.stream().map(s -> s.substring(0, 13)).mapToLong(Long::parseLong).sum();
            return Long.parseLong(("" + sum).substring(0, 10));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0L;
    }
}
