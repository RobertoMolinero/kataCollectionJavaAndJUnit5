package algorithms.linked_lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class DoublyLinkedListTest {

    @ParameterizedTest
    @MethodSource
    <T> void addFirst(DoublyLinkedList<T> sut, T value, List<T> expectedElements) {
        // act
        sut.addFirst(value);

        // assert
        Assertions.assertEquals(expectedElements.size(), sut.size());
        Assertions.assertEquals(expectedElements.get(0), sut.peekFirst());
        Assertions.assertEquals(expectedElements.get(expectedElements.size() - 1), sut.peekLast());

        sut.forEach((element) -> Assertions.assertTrue(expectedElements.contains(element)));
    }

    static Stream<Arguments> addFirst() {

        DoublyLinkedList<Object> noValue = getNoValue();

        List<String> noValueResult = new ArrayList<>();
        noValueResult.add("kattis/abc");

        DoublyLinkedList<String> oneString = getOneString();

        List<String> oneStringResult = new ArrayList<>();
        oneStringResult.add("2");
        oneStringResult.add("1");

        DoublyLinkedList<Integer> twoIntegers = getTwoIntegers();

        List<Integer> twoIntegersResult = new ArrayList<>();
        twoIntegersResult.add(3);
        twoIntegersResult.add(1);
        twoIntegersResult.add(2);

        DoublyLinkedList<String> threeStrings = getThreeStrings();

        List<String> threeStringsResult = new ArrayList<>();
        threeStringsResult.add("ahoi");
        threeStringsResult.add("kattis/abc");
        threeStringsResult.add("xyz");
        threeStringsResult.add("rhabarber");

        DoublyLinkedList<Point> threePoints = getThreePoints();

        List<Point> threePointsResult = new ArrayList<>();
        threePointsResult.add(new Point(1, 1));
        threePointsResult.add(new Point(0, 0));
        threePointsResult.add(new Point(1, 0));
        threePointsResult.add(new Point(0, 1));

        DoublyLinkedList<Color> fourColors = getFourColors();

        List<Color> fourColorsResult = new ArrayList<>();
        fourColorsResult.add(Color.MAGENTA);
        fourColorsResult.add(Color.YELLOW);
        fourColorsResult.add(Color.BLUE);
        fourColorsResult.add(Color.RED);
        fourColorsResult.add(Color.WHITE);

        return Stream.of(
                arguments(noValue, "kattis/abc", noValueResult),
                arguments(oneString, "2", oneStringResult),
                arguments(twoIntegers, 3, twoIntegersResult),
                arguments(threeStrings, "ahoi", threeStringsResult),
                arguments(threePoints, new Point(1, 1), threePointsResult),
                arguments(fourColors, Color.MAGENTA, fourColorsResult)
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void addLast(DoublyLinkedList<T> sut, T value, List<T> expectedElements) {
        // act
        sut.addLast(value);

        // assert
        Assertions.assertEquals(expectedElements.size(), sut.size());
        Assertions.assertEquals(expectedElements.get(0), sut.peekFirst());
        Assertions.assertEquals(expectedElements.get(expectedElements.size() - 1), sut.peekLast());

        sut.forEach((element) -> Assertions.assertTrue(expectedElements.contains(element)));
    }

    static Stream<Arguments> addLast() {

        DoublyLinkedList<Object> noValue = getNoValue();

        List<String> noValueResult = new ArrayList<>();
        noValueResult.add("kattis/abc");

        DoublyLinkedList<String> oneString = getOneString();

        List<String> oneStringResult = new ArrayList<>();
        oneStringResult.add("1");
        oneStringResult.add("2");

        DoublyLinkedList<Integer> twoIntegers = getTwoIntegers();

        List<Integer> twoIntegersResult = new ArrayList<>();
        twoIntegersResult.add(1);
        twoIntegersResult.add(2);
        twoIntegersResult.add(3);

        DoublyLinkedList<String> threeStrings = getThreeStrings();

        List<String> threeStringsResult = new ArrayList<>();
        threeStringsResult.add("kattis/abc");
        threeStringsResult.add("xyz");
        threeStringsResult.add("rhabarber");
        threeStringsResult.add("ahoi");

        DoublyLinkedList<Point> threePoints = getThreePoints();

        List<Point> threePointsResult = new ArrayList<>();
        threePointsResult.add(new Point(0, 0));
        threePointsResult.add(new Point(1, 0));
        threePointsResult.add(new Point(0, 1));
        threePointsResult.add(new Point(1, 1));

        DoublyLinkedList<Color> fourColors = getFourColors();

        List<Color> fourColorsResult = new ArrayList<>();
        fourColorsResult.add(Color.YELLOW);
        fourColorsResult.add(Color.BLUE);
        fourColorsResult.add(Color.RED);
        fourColorsResult.add(Color.WHITE);
        fourColorsResult.add(Color.MAGENTA);

        return Stream.of(
                arguments(noValue, "kattis/abc", noValueResult),
                arguments(oneString, "2", oneStringResult),
                arguments(twoIntegers, 3, twoIntegersResult),
                arguments(threeStrings, "ahoi", threeStringsResult),
                arguments(threePoints, new Point(1, 1), threePointsResult),
                arguments(fourColors, Color.MAGENTA, fourColorsResult)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void clear(DoublyLinkedList<T> sut) {
        // act
        sut.clear();

        // assert
        Assertions.assertEquals(0, sut.size());
    }

    static Stream<Arguments> clear() {

        return Stream.of(
                arguments(getNoValue()),
                arguments(getOneString()),
                arguments(getTwoIntegers()),
                arguments(getThreeStrings()),
                arguments(getThreePoints()),
                arguments(getFourColors())
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void contains(DoublyLinkedList<T> sut, T element, boolean expectedResult) {
        // act
        boolean contains = sut.contains(element);

        // assert
        Assertions.assertEquals(expectedResult, contains);
    }

    static Stream<Arguments> contains() {

        return Stream.of(
                arguments(getNoValue(), "a", false),
                arguments(getOneString(), "1", true),
                arguments(getOneString(), "2", false),
                arguments(getTwoIntegers(), 2, true),
                arguments(getTwoIntegers(), 0, false),
                arguments(getThreeStrings(), "rhabarber", true),
                arguments(getThreeStrings(), "gurke", false),
                arguments(getThreePoints(), new Point(1, 0), true),
                arguments(getThreePoints(), new Point(2, 2), false),
                arguments(getFourColors(), Color.RED, true),
                arguments(getFourColors(), Color.BLACK, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void indexOf(DoublyLinkedList<T> sut, T element, int expectedResult) {
        // act
        int indexOf = sut.indexOf(element);

        // assert
        Assertions.assertEquals(expectedResult, indexOf);
    }

    static Stream<Arguments> indexOf() {

        return Stream.of(
                arguments(getNoValue(), "a", -1),
                arguments(getOneString(), "1", 0),
                arguments(getOneString(), "2", -1),
                arguments(getTwoIntegers(), 2, 1),
                arguments(getTwoIntegers(), 0, -1),
                arguments(getThreeStrings(), "rhabarber", 2),
                arguments(getThreeStrings(), "gurke", -1),
                arguments(getThreePoints(), new Point(1, 0), 1),
                arguments(getThreePoints(), new Point(2, 2), -1),
                arguments(getFourColors(), Color.RED, 2),
                arguments(getFourColors(), Color.BLACK, -1)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void isEmpty(DoublyLinkedList<T> sut, boolean expectedResult) {
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
    <T> void peekFirst(DoublyLinkedList<T> sut, T expectedResult) {
        // act
        T first = sut.peekFirst();

        // assert
        Assertions.assertEquals(expectedResult, first);
    }

    static Stream<Arguments> peekFirst() {

        return Stream.of(
                arguments(getOneString(), "1"),
                arguments(getTwoIntegers(), 1),
                arguments(getThreeStrings(), "kattis/abc"),
                arguments(getThreePoints(), new Point(0, 0)),
                arguments(getFourColors(), Color.YELLOW)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void peekLast(DoublyLinkedList<T> sut, T expectedResult) {
        // act
        T first = sut.peekLast();

        // assert
        Assertions.assertEquals(expectedResult, first);
    }

    static Stream<Arguments> peekLast() {

        return Stream.of(
                arguments(getOneString(), "1"),
                arguments(getTwoIntegers(), 2),
                arguments(getThreeStrings(), "rhabarber"),
                arguments(getThreePoints(), new Point(0, 1)),
                arguments(getFourColors(), Color.WHITE)
        );
    }


    @Test
    void peekOperationOnEmptyListThrowsNoElementException() {
        // act
        DoublyLinkedList<String> sut = new DoublyLinkedList<>();
        NoSuchElementException thrownByPeekFirst = Assertions.assertThrows(NoSuchElementException.class, sut::peekFirst);
        NoSuchElementException thrownByPeekLast = Assertions.assertThrows(NoSuchElementException.class, sut::peekLast);

        // assert
        Assertions.assertEquals("Empty list", thrownByPeekFirst.getMessage());
        Assertions.assertEquals("Empty list", thrownByPeekLast.getMessage());
    }


    @ParameterizedTest
    @MethodSource
    <T> void remove(DoublyLinkedList<T> sut, T element, boolean expectedResult) {
        // act
        boolean remove = sut.remove(element);

        // assert
        Assertions.assertEquals(expectedResult, remove);
    }

    static Stream<Arguments> remove() {

        return Stream.of(
                arguments(getOneString(), "1", true),
                arguments(getOneString(), "2", false),
                arguments(getTwoIntegers(), 1, true),
                arguments(getTwoIntegers(), 2, true),
                arguments(getTwoIntegers(), 3, false),
                arguments(getThreeStrings(), "kattis/abc", true),
                arguments(getThreeStrings(), "xyz", true),
                arguments(getThreeStrings(), "rhabarber", true),
                arguments(getThreeStrings(), "tomate", false),
                arguments(getThreePoints(), new Point(0, 0), true),
                arguments(getThreePoints(), new Point(1, 0), true),
                arguments(getThreePoints(), new Point(0, 1), true),
                arguments(getThreePoints(), new Point(1, 1), false),
                arguments(getFourColors(), Color.YELLOW, true),
                arguments(getFourColors(), Color.BLUE, true),
                arguments(getFourColors(), Color.RED, true),
                arguments(getFourColors(), Color.WHITE, true),
                arguments(getFourColors(), Color.BLACK, false)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void removeAt(DoublyLinkedList<T> sut, int index, T expectedResult) {
        // act
        T removeAt = sut.removeAt(index);

        // assert
        Assertions.assertEquals(expectedResult, removeAt);
    }

    static Stream<Arguments> removeAt() {

        return Stream.of(
                arguments(getOneString(), 0, "1"),
                arguments(getTwoIntegers(), 0, 1),
                arguments(getTwoIntegers(), 1, 2),
                arguments(getThreeStrings(), 0, "kattis/abc"),
                arguments(getThreeStrings(), 1, "xyz"),
                arguments(getThreeStrings(), 2, "rhabarber"),
                arguments(getThreePoints(), 0, new Point(0, 0)),
                arguments(getThreePoints(), 1, new Point(1, 0)),
                arguments(getThreePoints(), 2, new Point(0, 1)),
                arguments(getFourColors(), 0, Color.YELLOW),
                arguments(getFourColors(), 1, Color.BLUE),
                arguments(getFourColors(), 2, Color.RED),
                arguments(getFourColors(), 3, Color.WHITE)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void removeAtWithOutOfBoundIndexThrowsIllegalArgumentException(DoublyLinkedList<T> sut, int index) {
        // act
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> sut.removeAt(index));

        // assert
        Assertions.assertEquals("Index out of bounds", illegalArgumentException.getMessage());
    }

    static Stream<Arguments> removeAtWithOutOfBoundIndexThrowsIllegalArgumentException() {

        return Stream.of(
                arguments(getNoValue(), 1),
                arguments(getOneString(), -1),
                arguments(getOneString(), 1),
                arguments(getTwoIntegers(), 2),
                arguments(getThreeStrings(), 3),
                arguments(getThreePoints(), -3),
                arguments(getThreePoints(), 3),
                arguments(getFourColors(), -2),
                arguments(getFourColors(), 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    <T> void removeFirst(DoublyLinkedList<T> sut, T expectedResult) {
        // act
        T t = sut.removeFirst();

        // assert
        Assertions.assertEquals(expectedResult, t);
    }

    static Stream<Arguments> removeFirst() {

        return Stream.of(
                arguments(getOneString(), "1"),
                arguments(getTwoIntegers(), 1),
                arguments(getThreeStrings(), "kattis/abc"),
                arguments(getThreePoints(), new Point(0, 0)),
                arguments(getFourColors(), Color.YELLOW)
        );
    }


    @ParameterizedTest
    @MethodSource
    <T> void removeLast(DoublyLinkedList<T> sut, T expectedResult) {
        // act
        T t = sut.removeLast();

        // assert
        Assertions.assertEquals(expectedResult, t);
    }

    static Stream<Arguments> removeLast() {

        return Stream.of(
                arguments(getOneString(), "1"),
                arguments(getTwoIntegers(), 2),
                arguments(getThreeStrings(), "rhabarber"),
                arguments(getThreePoints(), new Point(0, 1)),
                arguments(getFourColors(), Color.WHITE)
        );
    }


    @Test
    void removeFirstRespectivelyRemoveLastOnEmptyListThrowsNoElementException() {
        // act
        DoublyLinkedList<String> sut = new DoublyLinkedList<>();
        RuntimeException thrownByRemoveFirst = Assertions.assertThrows(RuntimeException.class, sut::removeFirst);
        RuntimeException thrownByRemoveLast = Assertions.assertThrows(RuntimeException.class, sut::removeLast);

        // assert
        Assertions.assertEquals("Empty list", thrownByRemoveFirst.getMessage());
        Assertions.assertEquals("Empty list", thrownByRemoveLast.getMessage());
    }


    private static DoublyLinkedList<Object> getNoValue() {
        return new DoublyLinkedList<>();
    }

    private static DoublyLinkedList<String> getOneString() {
        DoublyLinkedList<String> oneString = new DoublyLinkedList<>();
        oneString.add("1");
        return oneString;
    }

    private static DoublyLinkedList<Integer> getTwoIntegers() {
        DoublyLinkedList<Integer> twoIntegers = new DoublyLinkedList<>();
        List.of(1, 2).forEach(twoIntegers::add);
        return twoIntegers;
    }

    private static DoublyLinkedList<String> getThreeStrings() {
        DoublyLinkedList<String> threeStrings = new DoublyLinkedList<>();
        List.of("kattis/abc", "xyz", "rhabarber").forEach(threeStrings::add);
        return threeStrings;
    }

    private static DoublyLinkedList<Point> getThreePoints() {
        DoublyLinkedList<Point> threePoints = new DoublyLinkedList<>();
        List.of(new Point(0, 0), new Point(1, 0), new Point(0, 1)).forEach(threePoints::add);
        return threePoints;
    }

    private static DoublyLinkedList<Color> getFourColors() {
        DoublyLinkedList<Color> fourColors = new DoublyLinkedList<>();
        List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.WHITE).forEach(fourColors::add);
        return fourColors;
    }
}
