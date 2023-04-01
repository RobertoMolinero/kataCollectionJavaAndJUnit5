package greeting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class GreetingTest {

    @ParameterizedTest
    @MethodSource
    public void simpleGreeting(String name, String expectedGreet) {
        // act
        String greet = Greeting.greet(List.of(name));

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> simpleGreeting() {
        return Stream.of(
                arguments("Bob", "Hello, Bob."),
                arguments("Jill", "Hello, Jill."),
                arguments("Jane", "Hello, Jane."),
                arguments("Amy", "Hello, Amy."),
                arguments("Brian", "Hello, Brian."),
                arguments("Charlotte", "Hello, Charlotte.")
        );
    }

    @Test
    public void simpleGreetingForNullValue() {
        // act
        String greet = Greeting.greet(null);

        // assert
        Assertions.assertEquals("Hello, my friend.", greet);
    }

    @ParameterizedTest
    @MethodSource
    public void simpleShouting(String name, String expectedGreet) {
        // act
        String greet = Greeting.greet(List.of(name));

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> simpleShouting() {
        return Stream.of(
                arguments("JERRY", "HELLO JERRY!"),
                arguments("JILL", "HELLO JILL!"),
                arguments("JANE", "HELLO JANE!"),
                arguments("AMY", "HELLO AMY!")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void twoGreetings(List<String> names, String expectedGreet) {
        // act
        String greet = Greeting.greet(names);

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> twoGreetings() {
        return Stream.of(
                arguments(List.of("Jill", "Jane"), "Hello, Jill and Jane."),
                arguments(List.of("Amy", "Brian"), "Hello, Amy and Brian."),
                arguments(List.of("Bob", "Charlotte"), "Hello, Bob and Charlotte.")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void multipleGreetings(List<String> names, String expectedGreet) {
        // act
        String greet = Greeting.greet(names);

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> multipleGreetings() {
        return Stream.of(
                arguments(List.of(), "Hello, my friend."),
                arguments(List.of("Amy"), "Hello, Amy."),
                arguments(List.of("Amy", "Brian"), "Hello, Amy and Brian."),
                arguments(List.of("Amy", "Brian", "Charlotte"), "Hello, Amy, Brian and Charlotte."),
                arguments(List.of("Amy", "Brian", "Charlotte", "Jill"), "Hello, Amy, Brian, Charlotte and Jill."),
                arguments(List.of("Amy", "Brian", "Charlotte", "Jill", "Jane"), "Hello, Amy, Brian, Charlotte, Jill and Jane."),
                arguments(List.of("Amy", "Brian", "Charlotte", "Jill", "Jane", "Bob"), "Hello, Amy, Brian, Charlotte, Jill, Jane and Bob.")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void multipleGreetingsOrShoutings(List<String> names, String expectedGreet) {
        // act
        String greet = Greeting.greet(names);

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> multipleGreetingsOrShoutings() {
        return Stream.of(
                arguments(List.of("Amy", "BRIAN", "Charlotte"), "Hello, Amy and Charlotte. AND HELLO BRIAN!"),
                arguments(List.of("Amy", "BOB", "BRIAN", "Charlotte"), "Hello, Amy and Charlotte. AND HELLO BOB! AND HELLO BRIAN!"),
                arguments(List.of("Amy", "Brian", "CHARLOTTE"), "Hello, Amy and Brian. AND HELLO CHARLOTTE!"),
                arguments(List.of("Amy", "BOB", "Brian", "CHARLOTTE"), "Hello, Amy and Brian. AND HELLO BOB! AND HELLO CHARLOTTE!"),
                arguments(List.of("Amy", "Brian"), "Hello, Amy and Brian."),
                arguments(List.of("BOB", "CHARLOTTE"), "HELLO BOB! AND HELLO CHARLOTTE!"),
                arguments(List.of("Amy", "CHARLOTTE"), "Hello, Amy. AND HELLO CHARLOTTE!")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void entriesWithCommaWillBeExpanded(List<String> names, String expectedGreet) {
        // act
        String greet = Greeting.greet(names);

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> entriesWithCommaWillBeExpanded() {
        return Stream.of(
                arguments(List.of("Bob", "Charlie, Dianne"), "Hello, Bob, Charlie and Dianne."),
                arguments(List.of("Bob", "Charlie, Dianne, Amy"), "Hello, Bob, Charlie, Dianne and Amy."),
                arguments(List.of("Bob, Charlotte", "Charlie, Dianne"), "Hello, Bob, Charlotte, Charlie and Dianne."),
                arguments(List.of("Bob, Charlotte", "Dianne"), "Hello, Bob, Charlotte and Dianne."),
                arguments(List.of("BOB, CHARLOTTE"), "HELLO BOB! AND HELLO CHARLOTTE!"),
                arguments(List.of("Amy, BOB", "Brian, CHARLOTTE"), "Hello, Amy and Brian. AND HELLO BOB! AND HELLO CHARLOTTE!")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void allowEscapeIntentionalCommas(List<String> names, String expectedGreet) {
        // act
        String greet = Greeting.greet(names);

        // assert
        Assertions.assertEquals(expectedGreet, greet);
    }

    static Stream<Arguments> allowEscapeIntentionalCommas() {
        return Stream.of(
                arguments(List.of("Bob", "\"Charlie, Dianne\""), "Hello, Bob and Charlie, Dianne."),
                arguments(List.of("\"Bob, Charlotte\"", "\"Charlie, Dianne\""), "Hello, Bob, Charlotte and Charlie, Dianne."),
                arguments(List.of("\"Bob, Charlotte\"", "Charlie"), "Hello, Bob, Charlotte and Charlie."),
                arguments(List.of("\"BOB, CHARLOTTE\""), "HELLO BOB, CHARLOTTE!"),
                arguments(List.of("\"Bob, Charlotte\""), "Hello, Bob, Charlotte."),
                arguments(List.of("\"BOB, Charlotte\""), "Hello, BOB, Charlotte."),
                arguments(List.of("\"Bob, CHARLOTTE\""), "Hello, Bob, CHARLOTTE.")
        );
    }
}
