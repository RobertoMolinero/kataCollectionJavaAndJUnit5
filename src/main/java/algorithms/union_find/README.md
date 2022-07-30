## Union Find

Union Find is a data structure that keeps track of elements which are split into one or more disjoint sets.

It has two primary operations: 'find' and 'union'.

## Examples of applications

- Kruskal's minimum spanning tree algorithm
- Grid percolation
- Network connectivity
- Least common ancestor in trees
- Image processing

Complexity

| Operation          | Big-O    |
|--------------------|----------|
| Construction       | O(n)     |
| Union              | alpha(n) |
| Find               | alpha(n) |
| Get component size | alpha(n) |
| Check if connected | alpha(n) |
| Count components   | 0(1)     |

# Kruskal's Minimum Spanning Tree

G= (V,E)

A minimum spanning tree is a subset of the edges which connect all vertices in the graph with the minimal total edge cost.

## Procedure

1. Sort edges by ascending edge weight
2. Walk through the sorted edges and look at the two nodes the edge belongs to. If the nodes are already unified we don't include this edge, otherwise we include it and unify the nodes.
3. The algorithm terminates when every edge has been processed or all vertices have been unified.

# Union Find Operations

## Creating Union Find

Construct a bijection (mapping) between objects and the integers in the range[0, n).

Note: This step is not necessary in general, but it will allow to construct an array-based union find.

Find: Find the root of that component by following the parent nodes until a self loop is reached.
Union: Compare the root nodes of the components and if they are different make on of the root nodes be the parent of the other.
