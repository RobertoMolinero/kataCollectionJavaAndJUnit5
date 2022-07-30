## Priority Queues

Priority Queues: Queue with values.
Heap: tree based data structure that satisfies the heap invariant

Max Heap:

8   -   7   -   3
            -   2
    -   6   -   5

Min Heap:

0   -   2   -   4
            -   5
    -   3   -   6
            -   4

Complexity Priority Queues with binary heap

| Operation                           | Big-O     |
|-------------------------------------|-----------|
| Binary Heap Construction            | O(n)      |
| Poll                                | O(log(n)) |
| Peek                                | O(1)      |
| Add                                 | O(log(n)) |
| Naive Removing                      | O(n)      |
| Advanced Removing with a hash table | O(log(n)) |
| Naive Contains                      | O(n)      |
| Advanced Contains with a hash table | O(1)      |

Priority Queues are ADT. An implementation is possible with Heaps.

Binary Heap
Fibonacci Heap
Binomial Heap
Pairing Heap

A complete binary tree is completely filled. All the nodes are as far left as possible.

Binary Heap Representation

9   -   8   -   6   -   2
                    -   2
            -   5   -   3
                    -   4
    -   7   -   1   -   0
                    -   1
            -   2   -   2
                    -   1

9 8 7 6 5 1 2 2 2 3 4 0 1 2 1

Let i be the parent node index

Lef child index: 2i + 1
Right child index: 2i + 2

Zero based!






