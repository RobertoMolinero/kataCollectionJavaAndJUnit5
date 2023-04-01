package kattis.addingwords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class AddingWordsTest {

    @ParameterizedTest
    @MethodSource
    void evaluateExpression(HashMap<String, Integer> values, String expression, int expectedSize, List<String> expectedKeys) {
        // arrange
        AddingWords sut = new AddingWords(values);

        // act
        sut.evaluateExpression(expression);

        // assert
        Assertions.assertEquals(expectedSize, sut.getVariables().size());
        for(String key : expectedKeys) {
            Assertions.assertTrue(sut.getVariables().containsKey(key));
        }
    }

    static Stream<Arguments> evaluateExpression() {
        HashMap<String, Integer> foo = new HashMap<>();
        foo.put("foo", 3);

        HashMap<String, Integer> fooBar = new HashMap<>();
        fooBar.put("foo", 3);
        fooBar.put("bar", 7);

        HashMap<String, Integer> fooBarProgramming = new HashMap<>();
        fooBarProgramming.put("foo", 3);
        fooBarProgramming.put("bar", 7);
        fooBarProgramming.put("programming", 10);

        return Stream.of(
                arguments(new HashMap<>(), "def foo 3", 1, List.of("foo")),
                arguments(foo, "def bar 7", 2, List.of("foo", "bar")),
                arguments(fooBar, "def programming 10", 3, List.of("foo", "bar", "programming")),
                arguments(fooBarProgramming, "def is 4", 4, List.of("foo", "bar", "programming", "is"))

//                arguments("def fun 8"),
//                arguments("def fun 1"),
//                arguments("clear")
        );
    }
}