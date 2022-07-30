# Sparse Tables

## Motiviation

Sparse tables are all about doing efficient range queries on static arrays. Range queries come in a variety of flavors,
but the most common types are min, max, sum and gcd range queries.

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  | 12  |
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| 4   | 2   | 3   | 7   | 1   | 5   | 3   | 3   | 9   | 6   | 7   | -1  | 4   |

## Sparse Table Intuition

Every positive integer can easily be represented as a sum of powers of 2 given by its binary representation:

19 = 10011 = 2^4 + 2^1 + 2^0 = 19

Similarly, any interval [l, r] can be broken down into smaller intervals of powers of 2:

[5, 17] = [5, 5 + 2^3] U [13, 13 + 2^2) U [17, 17 + 2^0]
= [5, 13) U [13, 17) U [17, 18)

Now, imagine if we could precompute the range query answer (i.e. min, max, sum...) for all these intervals and combine
them...

## Range combination function

### ...for associative functions

For associative functions, a sparse table can answer range queries in O(log2(n)).

A function f(x, y) is associative if: f(a, f(b, c)) = f(f(a, b), c) for all valid a, b, c.

Operations such as addition and multiplication are associative, but functions like subtraction and exponentiation are
not. Here's a counterexample proving subtraction is not associative:

Let f(a, b) = a - b

f(1, g(2, 3))
= f(1, 2 - 3))
= 1 - (2 - 3)
= 2

f(g(1, 2), 3)
= f(1 - 2, 3)
= (1 - 2) - 3 = -4

f(x, y) is not associative

### ...for overlap friendly functions

Logarithmic time range queries are really good, however we can do better. When the range query combination function is "
overlap friendly", then range queries on a sparse table can be answered in O(1).

Being overlap friendly means a function yields the same answer regardless of wether it is combining ranges which overlap
or those that do not.

We say a binary function f(x, y) is overlap friendly if: f(f(a, b), f(b, c)) = f(a, f(b,c)) for all valid a, b, c.

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  | 12  | 13  | 14  | 15  |
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| 4   | 2   | 3   | -6  | 6   | 7   | 1   | -2  | 8   | 9   | 8   | 1   | 1   | 3   | -6  | 2   |

Let f(x, y) = x + y

r1 [0,5] = 16 
r2 [6,8] = 7 
r3 [9,15] = 18

f(f(r1,r2), f(r2,r3)) = f(f(16,7), f(7,18)) = f(23, 25) = 48 
f(r1, f(r2,r3)) = f(16, f(7,18)) = f(16, 25) = 41

f(x, y) is not overlap friendly

| Function f(,a b)  | Overlap friendly |
|-------------------|------------------|
| 1 * b             | true             |
| a * b             | false            |
| min(a, b)         | true             |
| max(a, b)         | true             |
| a + b             | false            |
| a - b             | false            |
| (a * b) / a, a!=0 | true             |
| gcd(a, b)         | true             |

## Table construction

The idea behind a sparse table is to precompute the answer for all intervals of size 2^x to efficiently answer range queries between [l, r].

Let N be the size of the input values array, and let 2^P be the largest power of 2 that fits in the length of the values array.

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  | 12  |
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| 4   | 2   | 3   | 7   | 1   | 5   | 3   | 3   | 9   | 6   | 7   | -1  | 4   |

N = 13

P = floor(log2(N)) = floor(log2(13)) = 3

Begin by initializing a table with P+1 rows and N columns. Then, fill the first row with the input values.

Each cell (i, j) represents the answer for the range [j, j+2^i) in the original array.

Example: f(a, b) = min(a, b)

|        | 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  | 12  |
|--------|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| 0, 2^0 | 4   | 2   | 3   | 7   | 1   | 5   | 3   | 3   | 9   | 6   | 7   | -1  | 4   |
| 0, 2^1 | 2   | 2   | 3   | 1   | 1   | 3   | 3   | 3   | 6   | 6   | -1  | -1  | #   |
| 0, 2^2 | 2   | 1   | 1   | 1   | 1   | 3   | 3   | 3   | -1  | -1  | #   | #   | #   |
| 0, 2^3 | 1   | 1   | 1   | 1   | -1  | -1  | #   | #   | #   | #   | #   | #   | #   |

Example: f(a, b) = a + b

Hint: We can finish filling the sparse table by combining values from the previous rows (dynamic programming).

## Range queries

### ...for overlap friendly functions

### ...for associative functions

Example: f(a, b) = a * b

|        | 0   | 1   | 2   | 3   | 4   | 5   | 6   |
|--------|-----|-----|-----|-----|-----|-----|-----|
| 0, 2^0 | 1   | 2   | -3  | 2   | 4   | -1  | 5   |
| 0, 2^1 | 2   | -6  | -6  | 8   | -4  | -5  | #   |
| 0, 2^2 | -12 | -48 | 24  | -40 | #   | #   | #   |

Break interval [0, 6] into powers of 2: [0, 2) U [4, 4+2^1) U [6, 6+2^0)

table[2][0] * table[1][4] * table[0][6]
= -12 * -4 * 5 
= 240

## Pseudo code

# The number of elements in the array
N = ...

# P, short for power. The largest 2^P at fits in N
P = floor(log2(n))

# A quick lookup table for floor(log2(i)), 1 <= i <= N
log2 = ... # size N+1, index 0 unused

# The sparse table containing integer values
dp = ... # P+1 rows and N columns

# Index table (IT) associated with the values in the sparse table. This table is only useful when we want to query the index of the min (or max) element in the range [l, r] rather than the value itself. The index table doesn't make sense for most other range query types like gcd or sum.
it = ... # size N+1, index 0 unused

```
function BuildMinSparseTable(values):
    N = length(values)
    P = floor(log(n) / log(2))
    
    # Quick lookup table for floor(log2(i)), 1 <= i <= N
    log2 = [0,0,...,0,0] # size N+1
    for (i=2; i<=N; i++):
        log2[i] = log2[i/2] + 1
        
    # Fill first row
    for (i=0; i<N; i++):
        dp[0][i] = values[i]
        it[0][i] = i
        
    for (p=1; p <= P; p++):
        for (i=0; i + (1 << p) <= N; i++):
            left = dp[p-1][i]
            right = dp[p-1][i+(1<<(p-1))]
            dp[p][i] = min(left, right)
            
            # Save/propagate the index of smallest element
            if left <= right:
                it[p][i] = it[p-1][i]
            else:
                it[p][i] = it[p-1][i+(1<<(p-1))]
```

```
# Query the smallest element in the range [l, r], O(1)
function MinQuery(l, r):
    len = r - l + 1
    p = log2[len]
    left = dp[p][l]
    right = dp[p][r - (1 << p) + 1]
    return min(left, right) 
```

```
# Query the smallest element in the range [l, r] by doing a cascading min query, O(log2(n)).
function CascadingMinQuery(l, r):
    min_val = +UNDENDLICH
    
    for (p = log2[r - l + 1]; l <=r; p = log2[r - l + 1]):
        min_val = min(min_val, dp[p][l])
        l += (1 << p)
    
    return min_val;
```

```
# Returns an index of the minimum element in the range [l, r] in the input values array. If there are multiple smallest elements, the index of leftmost is returned.
function MinIndexQuery(l, r):
    len = r - l + 1
    p = log2[len]
    left = dp[p][l]
    right = dp[p][r - (1 << p) + 1]
    
    if left <= right:
        return it[p][l]
        
    return it[p][r - (1 << p) + 1]
```
