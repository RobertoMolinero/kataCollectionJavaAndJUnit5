package kattis.problems99;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class Problems99Test {

    @ParameterizedTest
    @MethodSource
    void getNearest99(int oldPrice, int newPrice) {
        Problems99 sut = new Problems99();
        Assertions.assertEquals(newPrice, sut.getNearest99(oldPrice));
    }

    static Stream<Arguments> getNearest99() {
        return Stream.of(
                arguments(10, 99),
                arguments(249, 299),
                arguments(248, 199),
                arguments(10000, 9999)
        );
    }
}