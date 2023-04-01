package kattis.aaah;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class AaahTest {

    @ParameterizedTest
    @MethodSource
    void getCanGo(String jonMarius, String doctor, String canGo) {
        Aaah sut = new Aaah();
        Assertions.assertEquals(canGo, sut.getCanGo(jonMarius, doctor));
    }

    static Stream<Arguments> getCanGo() {
        return Stream.of(
                arguments("ah", "kattis/aaah", "no"),
                arguments("kattis/aaah", "ah", "go"),
                arguments("kattis/aaah", "kattis/aaah", "go")
        );
    }
}