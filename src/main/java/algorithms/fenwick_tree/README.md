# Fenwick Tree (Binary Indexed Tree)

A Fenwick Tree (also called Binary Indexed Tree) is a data structure that supports sum range queries as well as setting values in a static array and getting the value of the prefix sum up some index efficiently.

## Complexity

| Operation      | Time      |
|----------------|-----------|
| Construction   | O(n)      |
| Point Update   | O(log(n)) |
| Range Sum      | O(log(n)) |
| Range Update   | O(log(n)) |
| Adding Index   | N/A       |
| Removing Index | N/A       |

## Range Queries

| Index | Bit   | #1  | #2  | #3  | #4  | #5  |
|-------|-------|-----|-----|-----|-----|-----|
| 16    | 10000 |     |     |     |     | o   |
| 15    | 01111 | o   |     |     |     | o   |
| 14    | 01110 |     | o   |     |     | o   |
| 13    | 01101 | o   | o   |     |     | o   |
| 12    | 01100 |     |     | o   |     | o   |
| 11    | 01011 | o   |     | o   |     | o   |
| 10    | 01010 |     | o   | o   |     | o   |
| 9     | 01001 | o   | o   | o   |     | o   |
| 8     | 01000 |     |     |     | o   | o   |
| 7     | 00111 | o   |     |     | o   | o   |
| 6     | 00110 |     | o   |     | o   | o   |
| 5     | 00101 | o   | o   |     | o   | o   |
| 4     | 00100 |     |     | o   | o   | o   |
| 3     | 00011 | o   |     | o   | o   | o   |
| 2     | 00010 |     | o   | o   | o   | o   |
| 1     | 00001 | o   | o   | o   | o   | o   |

Find the prefix sum up to index 7.

sum = A[7] + A[6] + A[4]

Find the prefix sum up to index 11.

sum = A[11] + A[10] + A[8]

Find the prefix sum up to index 4.

sum = A[4]

Compute the interval sum between [11, 15].

[1, 15] = A[15] + A[14] + A[12] + A[8]
[1, 11) = A[10] + A[8]

sum = [1, 15] - [1, 11)

## Range Query Algorithm

```
function prefixSum(i):
    sum := 0
    while i != 0
        sum = sum + tree[i]
        i = i - LSB(i)
    return sum
    
function rangeQuery(i, j):
    return prefixSum(j) - prefixSum(i-1)
```

## Point Updates

Point updates are the opposite of querying, we want to add the LSB to propagate the value up to the cells responsible for us.

Udpate on index 9:

9 = 1001, 1001 + 0001 = 1010 
10 = 1010, 1010 + 0010 = 1100
12 = 1100, 1100 + 0100 = 10000
16 = 10000

## Point Update Algorithm

```
function add(i, x):
    while i < N:
        tree[i] = tree[i] + x 
        i = i + LSB(i)
```

## Fenwick tree construction

Let A be an array of values.

Naive: There are n elements and each point update takes O(log(n)) for a total of O(n*log(n)).

### Linear construction

Let i be the current index.

The immediate cell above us is at position j given by:

```
j := i + LSB(i)
```

Example

| Index | Bit   | Init | Value | #1  | #2  | #3  | #4  | #5  |
|-------|-------|------|-------|-----|-----|-----|-----|-----|
| 12    | 01100 | -8   | -11   |     |     | o   |     | o   |
| 11    | 01011 | 4    | 4     | o   |     | o   |     | o   |
| 10    | 01010 | 2    | -7    |     | o   | o   |     | o   |
| 9     | 01001 | -9   | -9    | o   | o   | o   |     | o   |
| 8     | 01000 | -8   | 23    |     |     |     | o   | o   |
| 7     | 00111 | 5    | 5     | o   |     |     | o   | o   |
| 6     | 00110 | 11   | 14    |     | o   |     | o   | o   |
| 5     | 00101 | 3    | 3     | o   | o   |     | o   | o   |
| 4     | 00100 | 7    | 12    |     |     | o   | o   | o   |
| 3     | 00011 | -2   | -2    | o   |     | o   | o   | o   |
| 2     | 00010 | 4    | 7     |     | o   | o   | o   | o   |
| 1     | 00001 | 3    | 3     | o   | o   | o   | o   | o   |

```
function construct(values):
    N := length(values)
    tree = deepCopy(values)
    
    for i = 1,2,3, ... N:
        j := i + LSB(i)
        if j<N:
            tree[j] = tree[j] + tree[i]
            
    return tree
```











