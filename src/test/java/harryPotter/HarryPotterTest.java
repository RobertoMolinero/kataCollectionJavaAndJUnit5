package harryPotter;

import static org.junit.jupiter.api.Assertions.*;

class HarryPotterTest {

    // Todo: Covert from Spock to JUnit5

    /*

    package harryPotter

import spock.lang.Specification
import spock.lang.Unroll

class HarryPotterTest extends Specification {

    @Unroll
    def "Simple shopping baskets (1 object) are calculated correctly. For the input of \"#input\" we get the solution \"#output\""() {
        given:
        HarryPotter sut = new HarryPotter()

        when:
        def result = sut.calculatePrice(input)

        then:
        result == output

        where:
        input | output
        []    | 0
        [0]   | 8
        [1]   | 8
        [2]   | 8
        [3]   | 8
        [4]   | 8
    }

    @Unroll
    def "Simple shopping baskets (objects of 1 kind) are calculated correctly. For the input of \"#input\" we get the solution \"#output\""() {
        given:
        HarryPotter sut = new HarryPotter()

        when:
        def result = sut.calculatePrice(input)

        then:
        result == output

        where:
        input              | output
        [0, 0]             | 16
        [1, 1, 1]          | 24
        [2, 2, 2, 2]       | 32
        [3, 3, 3, 3, 3]    | 40
        [4, 4, 4, 4, 4, 4] | 48
    }

    @Unroll
    def "Simple shopping baskets (no duplicates) are calculated correctly. For the input of \"#input\" we get the solution \"#output\""() {
        given:
        HarryPotter sut = new HarryPotter()

        when:
        def result = sut.calculatePrice(input)

        then:
        result == output

        where:
        input           | output
        [0]             | 8
        [0, 1]          | 2 * 8 * 0.95
        [0, 1, 2]       | 3 * 8 * 0.9
        [0, 1, 2, 3]    | 4 * 8 * 0.8
        [0, 1, 2, 3, 4] | 5 * 8 * 0.75
    }

    @Unroll
    def "Complex shopping baskets (no duplicates) are calculated correctly. For the input of \"#input\" we get the solution \"#output\""() {
        given:
        HarryPotter sut = new HarryPotter()

        when:
        def result = sut.calculatePrice(input)

        then:
        result == output

        where:
        input              | output
        [0, 0, 1]          | 8 + (8 * 2 * 0.95)
        [0, 0, 1, 1]       | 2 * (8 * 2 * 0.95)
        [0, 0, 1, 2, 2, 3] | (8 * 4 * 0.8) + (8 * 2 * 0.95)
        [0, 1, 1, 2, 3, 4] | 8 + (8 * 5 * 0.75)
    }

    @Unroll
    def "Complex shopping baskets (Edge Cases) are calculated correctly. For the input of \"#input\" we get the solution \"#output\""() {
        given:
        HarryPotter sut = new HarryPotter()

        when:
        def result = sut.calculatePrice(input)

        then:
        result == output

        where:
        input                                                                 | output
        [0, 0, 1, 1, 2, 2, 3, 4]                                              | 2 * (8 * 4 * 0.8)
        [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4] | 3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8)
    }
}



     */

}