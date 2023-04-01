package algorithms.stacks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class FifoStackTest {

    @ParameterizedTest
    @MethodSource
    <T> void isEmpty(FifoStack<T> sut, boolean expectedResult) {
        // act
        boolean empty = sut.isEmpty();

        // assert
        Assertions.assertEquals(expectedResult, empty);
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
    <T> void peek(FifoStack<T> sut, T expectedValue, int expectedSize) {
        // act
        T first = sut.peek();

        // assert
        Assertions.assertEquals(expectedValue, first);
        Assertions.assertEquals(expectedSize, sut.size());

    }

    static Stream<Arguments> peek() {

        return Stream.of(
                arguments(getOneString(), "1", 1),
                arguments(getTwoIntegers(), 2, 2),
                arguments(getThreeStrings(), "rhabarber", 3),
                arguments(getThreePoints(), new Point(0, 1), 3),
                arguments(getFourColors(), Color.WHITE, 4)
        );
    }

    @Test
    void peekOnEmptyListThrowsNoElementException() {
        // arrange
        FifoStack<String> sut = new FifoStack<>();

        // act + assert
        Assertions.assertThrows(EmptyStackException.class, sut::peek);
    }

    @ParameterizedTest
    @MethodSource
    <T> void pop(FifoStack<T> sut, T expectedValue, int expectedSize) {
        // act
        T first = sut.pop();

        // assert
        Assertions.assertEquals(expectedValue, first);
        Assertions.assertEquals(expectedSize, sut.size());
    }

    static Stream<Arguments> pop() {

        return Stream.of(
                arguments(getOneString(), "1", 0),
                arguments(getTwoIntegers(), 2, 1),
                arguments(getThreeStrings(), "rhabarber", 2),
                arguments(getThreePoints(), new Point(0, 1), 2),
                arguments(getFourColors(), Color.WHITE, 3)
        );
    }

    @Test
    void popOnEmptyListThrowsNoElementException() {
        // arrange
        FifoStack<String> sut = new FifoStack<>();

        // act + assert
        Assertions.assertThrows(EmptyStackException.class, sut::pop);
    }

    @ParameterizedTest
    @MethodSource
    <T> void push(FifoStack<T> sut, T element, FifoStack<T> expectedStack) {
        // act
        sut.push(element);

        // assert
        Assertions.assertEquals(expectedStack.size(), sut.size());
        Assertions.assertEquals(expectedStack, sut);
    }

    static Stream<Arguments> push() {

        FifoStack<String> oneString = new FifoStack<>();
        List.of("1", "2").forEach(oneString::push);

        FifoStack<Integer> twoIntegers = new FifoStack<>();
        List.of(1, 2, 3).forEach(twoIntegers::push);

        FifoStack<String> threeStrings = new FifoStack<>();
        List.of("kattis/abc", "xyz", "rhabarber", "tomato").forEach(threeStrings::push);

        FifoStack<Point> threePoints = new FifoStack<>();
        List.of(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)).forEach(threePoints::push);

        FifoStack<Color> fourColors = new FifoStack<>();
        List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.WHITE, Color.BLACK).forEach(fourColors::push);

        return Stream.of(
                arguments(getOneString(), "2", oneString),
                arguments(getTwoIntegers(), 3, twoIntegers),
                arguments(getThreeStrings(), "tomato", threeStrings),
                arguments(getThreePoints(), new Point(1, 1), threePoints),
                arguments(getFourColors(), Color.BLACK, fourColors)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void size(FifoStack<T> sut, int expectedSize) {
        // act
        int size = sut.size();

        // assert
        Assertions.assertEquals(expectedSize, size);
    }

    static Stream<Arguments> size() {

        return Stream.of(
                arguments(getNoValue(), 0),
                arguments(getOneString(), 1),
                arguments(getTwoIntegers(), 2),
                arguments(getThreeStrings(), 3),
                arguments(getThreePoints(), 3),
                arguments(getFourColors(), 4)
        );
    }


    private static FifoStack<Object> getNoValue() {
        return new FifoStack<>();
    }

    private static FifoStack<String> getOneString() {
        FifoStack<String> oneString = new FifoStack<>();
        oneString.push("1");
        return oneString;
    }

    private static FifoStack<Integer> getTwoIntegers() {
        FifoStack<Integer> twoIntegers = new FifoStack<>();
        List.of(1, 2).forEach(twoIntegers::push);
        return twoIntegers;
    }

    private static FifoStack<String> getThreeStrings() {
        FifoStack<String> threeStrings = new FifoStack<>();
        List.of("kattis/abc", "xyz", "rhabarber").forEach(threeStrings::push);
        return threeStrings;
    }

    private static FifoStack<Point> getThreePoints() {
        FifoStack<Point> threePoints = new FifoStack<>();
        List.of(new Point(0, 0), new Point(1, 0), new Point(0, 1)).forEach(threePoints::push);
        return threePoints;
    }

    private static FifoStack<Color> getFourColors() {
        FifoStack<Color> fourColors = new FifoStack<>();
        List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.WHITE).forEach(fourColors::push);
        return fourColors;
    }
}
