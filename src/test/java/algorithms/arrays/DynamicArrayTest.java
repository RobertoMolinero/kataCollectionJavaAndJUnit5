package algorithms.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DynamicArrayTest {

    @ParameterizedTest
    @MethodSource
    <T> void add(DynamicArray<T> sut, T value, DynamicArray<T> expectedResult) {
        // act
        sut.add(value);

        // assert
        assertEquals(expectedResult.size(), sut.size());
        assertEquals(expectedResult.capacity(), sut.capacity());
        assertEquals(expectedResult, sut);
    }

    static Stream<Arguments> add() {
        return Stream.of(
                arguments(getNoValue(), "kattis/abc", new DynamicArray<>(new String[]{"kattis/abc"})),
                arguments(getOneString(), "2", new DynamicArray<>(new String[]{"1", "2"})),
                arguments(getTwoIntegers(), 3, new DynamicArray<>(new Integer[]{1, 2, 3})),
                arguments(getThreeStrings(), "ahoi", new DynamicArray<>(new String[]{"kattis/abc", "xyz", "rhabarber", "ahoi"})),
                arguments(getThreePoints(), new Point(1, 1),
                        new DynamicArray<>(new Point[]{
                                new Point(0, 0),
                                new Point(1, 0),
                                new Point(0, 1),
                                new Point(1, 1)
                        })
                ),
                arguments(getFourColors(), Color.MAGENTA, new DynamicArray<>(new Color[]{Color.YELLOW, Color.BLUE, Color.RED, Color.WHITE, Color.MAGENTA}))
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void indexOf(DynamicArray<T> sut, T value, int expected) {
        // act
        int index = sut.indexOf(value);

        // assert
        assertEquals(expected, index);
    }

    static Stream<Arguments> indexOf() {
        return Stream.of(
                arguments(getNoValue(), "x", -1),
                arguments(getOneString(), "1", 0),
                arguments(getOneString(), "2", -1),
                arguments(getTwoIntegers(), 1, 0),
                arguments(getTwoIntegers(), 2, 1),
                arguments(getTwoIntegers(), 3, -1),
                arguments(getThreeStrings(), "kattis/abc", 0),
                arguments(getThreeStrings(), "xyz", 1),
                arguments(getThreeStrings(), "rhabarber", 2),
                arguments(getThreeStrings(), "not_found", -1),
                arguments(getThreePoints(), new Point(1, 0), 1),
                arguments(getThreePoints(), new Point(2, 0), -1),
                arguments(getFourColors(), Color.WHITE, 3),
                arguments(getFourColors(), Color.ORANGE, -1)
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void contains(DynamicArray<T> sut, T value, boolean expected) {
        // act
        boolean contains = sut.contains(value);

        // assert
        assertEquals(expected, contains);
    }

    static Stream<Arguments> contains() {
        return Stream.of(
                arguments(getNoValue(), "x", false),
                arguments(getOneString(), "1", true),
                arguments(getOneString(), "2", false),
                arguments(getTwoIntegers(), 1, true),
                arguments(getTwoIntegers(), 2, true),
                arguments(getTwoIntegers(), 3, false),
                arguments(getThreeStrings(), "kattis/abc", true),
                arguments(getThreeStrings(), "xyz", true),
                arguments(getThreeStrings(), "rhabarber", true),
                arguments(getThreeStrings(), "invalid", false),
                arguments(getThreePoints(), new Point(0, 1), true),
                arguments(getThreePoints(), new Point(-1, 0), false),
                arguments(getFourColors(), Color.BLUE, true),
                arguments(getFourColors(), Color.CYAN, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void isEmpty(DynamicArray<T> sut, boolean expected) {
        // act
        boolean empty = sut.isEmpty();

        // assert
        assertEquals(expected, empty);
    }

    static Stream<Arguments> isEmpty() {
        return Stream.of(
                arguments(getNoValue(), true),
                arguments(getOneString(), false),
                arguments(getTwoIntegers(), false),
                arguments(getThreeStrings(), false),
                arguments(getThreePoints(), false),
                arguments(getFourColors(), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void remove(DynamicArray<T> sut, T object, boolean removed, DynamicArray<T> expectedResult) {
        // act
        boolean result = sut.remove(object);

        // assert
        assertEquals(removed, result);
        assertEquals(expectedResult.size(), sut.size());
        assertEquals(expectedResult.capacity(), sut.capacity());
        assertEquals(expectedResult, sut);
    }

    static Stream<Arguments> remove() {
        return Stream.of(
                arguments(getOneString(), "1", true, new DynamicArray<>()),
                arguments(getTwoIntegers(), 1, true, new DynamicArray<>(new Integer[]{2})),
                arguments(getTwoIntegers(), 2, true, new DynamicArray<>(new Integer[]{1})),
                arguments(getThreeStrings(), "kattis/abc", true, new DynamicArray<>(new String[]{"xyz", "rhabarber"})),
                arguments(getThreeStrings(), "xyz", true, new DynamicArray<>(new String[]{"kattis/abc", "rhabarber"})),
                arguments(getThreeStrings(), "rhabarber", true, new DynamicArray<>(new String[]{"kattis/abc", "xyz"})),
                arguments(getThreePoints(), new Point(0, 0), true, new DynamicArray<>(new Point[]{new Point(1, 0), new Point(0, 1)})),
                arguments(getThreePoints(), new Point(1, 0), true, new DynamicArray<>(new Point[]{new Point(0, 0), new Point(0, 1)})),
                arguments(getThreePoints(), new Point(0, 1), true, new DynamicArray<>(new Point[]{new Point(0, 0), new Point(1, 0)})),
                arguments(getFourColors(), Color.YELLOW, true, new DynamicArray<>(new Color[]{Color.BLUE, Color.RED, Color.WHITE})),
                arguments(getFourColors(), Color.BLUE, true, new DynamicArray<>(new Color[]{Color.YELLOW, Color.RED, Color.WHITE})),
                arguments(getFourColors(), Color.RED, true, new DynamicArray<>(new Color[]{Color.YELLOW, Color.BLUE, Color.WHITE})),
                arguments(getFourColors(), Color.WHITE, true, new DynamicArray<>(new Color[]{Color.YELLOW, Color.BLUE, Color.RED}))
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void removeAt(DynamicArray<T> sut, int index, T object, DynamicArray<T> expectedResult) {
        // act
        T result = sut.removeAt(index);

        // assert
        assertEquals(object, result);
        assertEquals(expectedResult.size(), sut.size());
        assertEquals(expectedResult.capacity(), sut.capacity());
        assertEquals(expectedResult, sut);
    }

    static Stream<Arguments> removeAt() {
        return Stream.of(
                arguments(getOneString(), 0, "1", new DynamicArray<>()),
                arguments(getTwoIntegers(), 0, 1, new DynamicArray<>(new Integer[]{2})),
                arguments(getTwoIntegers(), 1, 2, new DynamicArray<>(new Integer[]{1})),
                arguments(getThreeStrings(), 0, "kattis/abc", new DynamicArray<>(new String[]{"xyz", "rhabarber"})),
                arguments(getThreeStrings(), 1, "xyz", new DynamicArray<>(new String[]{"kattis/abc", "rhabarber"})),
                arguments(getThreeStrings(), 2, "rhabarber", new DynamicArray<>(new String[]{"kattis/abc", "xyz"})),
                arguments(getThreePoints(), 0, new Point(0, 0), new DynamicArray<>(new Point[]{new Point(1, 0), new Point(0, 1)})),
                arguments(getThreePoints(), 1, new Point(1, 0), new DynamicArray<>(new Point[]{new Point(0, 0), new Point(0, 1)})),
                arguments(getThreePoints(), 2, new Point(0, 1), new DynamicArray<>(new Point[]{new Point(0, 0), new Point(1, 0)})),
                arguments(getFourColors(), 0, Color.YELLOW, new DynamicArray<>(new Color[]{Color.BLUE, Color.RED, Color.WHITE})),
                arguments(getFourColors(), 1, Color.BLUE, new DynamicArray<>(new Color[]{Color.YELLOW, Color.RED, Color.WHITE})),
                arguments(getFourColors(), 2, Color.RED, new DynamicArray<>(new Color[]{Color.YELLOW, Color.BLUE, Color.WHITE})),
                arguments(getFourColors(), 3, Color.WHITE, new DynamicArray<>(new Color[]{Color.YELLOW, Color.BLUE, Color.RED}))
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void removeAtExpectedException(DynamicArray<T> sut, int index, String expectedMessage) {
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            // act
            sut.removeAt(index);
        });

        // assert
        assertEquals(expectedMessage, thrown.getMessage());
    }

    static Stream<Arguments> removeAtExpectedException() {
        return Stream.of(
                arguments(getOneString(), -1, "Illegal index: -1"),
                arguments(getOneString(), 1, "Illegal index: 1")
        );
    }

    private static DynamicArray<Object> getNoValue() {
        return new DynamicArray<>();
    }

    private static DynamicArray<String> getOneString() {
        return new DynamicArray<>(new String[]{"1"});
    }

    private static DynamicArray<Integer> getTwoIntegers() {
        return new DynamicArray<>(new Integer[]{1, 2});
    }

    private static DynamicArray<String> getThreeStrings() {
        return new DynamicArray<>(new String[]{"kattis/abc", "xyz", "rhabarber"});
    }

    private static DynamicArray<Point> getThreePoints() {
        return new DynamicArray<>(new Point[]{new Point(0, 0), new Point(1, 0), new Point(0, 1)});
    }

    private static DynamicArray<Color> getFourColors() {
        return new DynamicArray<>(new Color[]{Color.YELLOW, Color.BLUE, Color.RED, Color.WHITE});
    }
}
