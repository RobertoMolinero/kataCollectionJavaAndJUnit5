# The Greeting Kata

Once upon a time there was a series of 5 books about a very English hero called Harry. (At least when this Kata was invented, there were only 5. Since then they have multiplied) Children all over the world thought he was fantastic, and, of course, so did the publisher. So in a gesture of immense generosity to mankind, (and to increase sales) they set up the following pricing model to take advantage of Harry’s magical powers.

One copy of any of the five books costs 8 EUR. If, however, you buy two different books from the series, you get a 5% discount on those two books. If you buy 3 different books, you get a 10% discount. With 4 different books, you get a 20% discount. If you go the whole hog, and buy all 5, you get a huge 25% discount.

| Basket                   | Example         | Preis                 |
| ------------------------ |:---------------:| ---------------------:|
| One Book                 | [0]             | $8 (No discount)      |
| Multiple identical books | [1, 1, 1]       | $8 (No discount)      |
| Two different books      | [0, 1]          | $15.20 (5% discount)  |
| Three different books    | [0, 1, 2]       | $15.20 (10% discount) |
| Four different books     | [0, 1, 2, 3]    | $15.20 (20% discount) |
| Five different books     | [0, 1, 2, 3, 4] | $15.20 (25% discount) |

Note that if you buy, say, four books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the fourth book still costs 8 EUR.

Potter mania is sweeping the country and parents of teenagers everywhere are queueing up with shopping baskets overflowing with Potter books. Your mission is to write a piece of code to calculate the price of any conceivable shopping basket, giving as big a discount as possible.

## Test Cases

```
def testBasics
  assert_equal(0, price([]))
  assert_equal(8, price([1]))
  assert_equal(8, price([2]))
  assert_equal(8, price([3]))
  assert_equal(8, price([4]))
  assert_equal(8 * 3, price([1, 1, 1]))
end

def testSimpleDiscounts
  assert_equal(8 * 2 * 0.95, price([0, 1]))
  assert_equal(8 * 3 * 0.9, price([0, 2, 4]))
  assert_equal(8 * 4 * 0.8, price([0, 1, 2, 4]))
  assert_equal(8 * 5 * 0.75, price([0, 1, 2, 3, 4]))
end

def testSeveralDiscounts
  assert_equal(8 + (8 * 2 * 0.95), price([0, 0, 1]))
  assert_equal(2 * (8 * 2 * 0.95), price([0, 0, 1, 1]))
  assert_equal((8 * 4 * 0.8) + (8 * 2 * 0.95), price([0, 0, 1, 2, 2, 3]))
  assert_equal(8 + (8 * 5 * 0.75), price([0, 1, 1, 2, 3, 4]))
end

def testEdgeCases
  assert_equal(2 * (8 * 4 * 0.8), price([0, 0, 1, 1, 2, 2, 3, 4]))
  assert_equal(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8), 
    price([0, 0, 0, 0, 0, 
           1, 1, 1, 1, 1, 
           2, 2, 2, 2, 
           3, 3, 3, 3, 3, 
           4, 4, 4, 4]))
end
```
