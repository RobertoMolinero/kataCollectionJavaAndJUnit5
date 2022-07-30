# Hash tables

A Hash table is a data structure that provides a mapping from keys to values using a technique called hashing.

## Hash table and hash function

Hash table is consists of key-value pairs.

Keys must be unique, but values can be repeated.

Keys must be hashable.

Example: Track item frequencies in Shakespeare's Hamlet.

| Key (Word) | Value (Word Frequency) |
|------------|------------------------|
| "hamlet"   | 114                    |
| "ghost"    | 33                     |
| "the"      | 1151                   |
| "lord"     | 223                    |
| "a"        | 550                    |
| "cabbage"  | null                   |
| ...        | ...                    |

Challenge: Create a hash function H(person) to map a person to the set {0,1,2,3,4,5}.

| Name    | Age | Sex | Hash  |
|---------|-----|-----|-------|
| William | 21  | M   | ?     |
| Kate    | 19  | F   | ?     |
| Bob     | 33  | M   | ?     |
| Rose    | 26  | F   | ?     |

## Properties of hash functions

- H(x) = H(y) ? x and y might be equal
- H(x) != H(y) ? x and y are not equal
- H(x) must be deterministic
- H(x) should be as uniform as possible

## Discussion on collision resolution methods, in particular: separate chaining and open addressing

Separate chaining -> Maintain a data structure (linked list) to hold all the different values which hashed to a particular value. 
Open adressing -> Find another place by offsetting it from the position to which it hashed to. 

## Complexity Analysis

| Operation | Average | Worst  |
|-----------|---------|--------|
| Insertion | O(1)*   | O(n)   |
| Removal   | O(1)*   | O(n)   |
| Search    | O(1)*   | O(n)   |

* With a good uniform hash function.

## Open addressing techniques implementation details

To be able to move the objects it is very important to keep an eye on the size of the hashtable.

```
Load factor = items in table / size of table 
```

Load factor must be lower than a fixed value to keep constant time behaviour O(1).

General idea:

```
x := 1
keyHash := H(k)
index := keyHash

while table[index] != null:
    index = (keyHash + P(k, x)) mod N
    x = x + 1
    
insert (k, v) at table[index]
```

H(k) Hash Function
P(k, x) Probing Function

## Probing sequences

### Linear probing

P(x) = ax +b (a, b are constants)

### Quadratic probing

P(x) = ax² + bx + c (a, b, c are constants)

Three ways to prevent cycles:

1. Let P(x) = x² and keep the table size a prime number > 3 and also keep alpha <= 0.5.
2. Let P(x) = (x²+x)/2 and keep the table size a power of two.
3. Let P(x) = (-1^x)*x² and keep the table size a prime N where N === 3 mod 4.

### Double hashing

P(k, x) = x * H2(k) (H2(k) is a secondary hash function)

To fix the issue of cycles pick the table size to be a prime number an also compute the value of d.

d = H2(k) mod N

If d == 0 the we are guaranteed to be stuck in a cycle, so when this happens set d = 1.

GCD(d, N) = 1 since N is prime.

Constructing H2(k)

### Pseudo random number generator

P(k, x) = x * RNG(H(k), x) (RNG is a random number generator function seeded with H(k))

## Removing elements

Remove means replacing old value with a tombstone.
Add means replacing tombstone with new value.












