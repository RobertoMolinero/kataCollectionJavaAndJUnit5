package algorithms.sparse_table;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Disabled("Work in progress...")
class MinSparseTableTest {


    /*

    Example: f(a, b) = min(a, b)

|        | 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  | 12  |
|--------|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| 0, 2^0 | 4   | 2   | 3   | 7   | 1   | 5   | 3   | 3   | 9   | 6   | 7   | -1  | 4   |
| 0, 2^1 | 2   | 2   | 3   | 1   | 1   | 3   | 3   | 3   | 6   | 6   | -1  | -1  | #   |
| 0, 2^2 | 2   | 1   | 1   | 1   | 1   | 3   | 3   | 3   | -1  | -1  | #   | #   | #   |
| 0, 2^3 | 1   | 1   | 1   | 1   | -1  | -1  | #   | #   | #   | #   | #   | #   | #   |

     */

    @ParameterizedTest
    @MethodSource

    <T> void generateTable(List<Integer> values) {
        // arrange + act
//        MinSparseTable sut = new MinSparseTable(values);

        // assert
//        assertNotNull(sut);
    }

    static Stream<Arguments> add() {
        return Stream.of(
                arguments(List.of(4, 2, 3, 7, 1, 5, 3, 3, 9, 6, 7, -1, 4))
        );
    }
}
