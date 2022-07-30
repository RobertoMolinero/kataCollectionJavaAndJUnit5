# Graph Theory

## Types of Graphs

> Undirected Graph: An undirected graph is a graph in which edges have no orientation. The edge(u, v) is identical to
> the edge (v, u).
>
> Directed Graph: A directed graph or digraph is a graph in which edges have orientation. The edge(u, v) is the edge
> from node u to node v.
>
> Weighted Graph: A graph can have edges that contain a certain weight to represent an arbitrary value such as cost,
> distance, quantity, etc...

## Special Types of Graphs

> Tree: A tree is an graph with no cycles. Equivalently, it is a connected graph with N nodes and N-1 edges.
>
> Rooted Tree: A rooted tree with a designated root node where every edge either points away from or towards the root
> node. When edges point away from the root the graph is called an arborescence (out-tree) and anti-arborescence (
> in-tree)
> otherwise.
>
> Directed Acyclic Graph: DAGs are directed graphs with no cycles. These graphs play an important role in representing
> structures with dependencies. Serveral efficient algorithms exist to operate in DAGs. (Important: All out-trees are
> DAGs
> but not all DAGs are out-trees!)
>
> Bipartite Graph: A bipartite graph is one whose vertices can be split into two independent groups U, V such that every
> edge connects betweens U and V. (Other definition: The graph is two colourable or there is no odd length cycle.)
>
> Complete Graph: A complete graph is one where there is a unique edge between every pair of nodes. A complete graph
> with n vertices is denoted as the graph Kn.

## Representing Graphs

### Adjacency Matrix

An adjacency matrix m is a very simple way to represent a graph. The idea is that the cell m[i][j] represents the ege
weight of going from node i to node j.

|     | A   | B   | C   | D   |
|-----|-----|-----|-----|-----|
| A   | 0   | 4   | 1   | 9   |
| B   | 3   | 0   | 6   | 11  |
| C   | 4   | 1   | 0   | 2   |
| D   | 6   | 5   | -4  | 0   |

| Pros                                          | Cons                                      |
|-----------------------------------------------|-------------------------------------------|
| Space efficient for representing dense graphs | Requires O(V²) space                      |
| Edge weight lookup is O(1)                    | Iterating over all edges takes O(V²) time |
| Simplest graph representation                 |                                           |

### Adjacency List

An adjacency list is a way to represent a graph as a map from nodes to lists of edges.

A -> [(B, 4), (C, 1)]
B -> [(C, 6)]
C -> [(A, 4), (B, 1), (D, 2)]
D -> []

| Pros                                           | Cons                                       |
|------------------------------------------------|--------------------------------------------|
| Space efficient for representing sparse graphs | Less space efficient for denser graphs     |
| Iterating over all edges is efficient          | Edge weight lookup is O(E)                 |
|                                                | Slightly more complex graph representation |

### Edge List

[(C,A,4), (A,C,1), (B,C,6), (A,B,4), (C,B,1), (C,D,2)]

| Pros                                           | Cons                                   |
|------------------------------------------------|----------------------------------------|
| Space efficient for representing sparse graphs | Less space efficient for denser graphs |
| Iterating over all edges is efficient          | Edge weight lookup is O(E)             |
| Very simple structure                          |                                        |

## Common Graph Theory Problems

For the upcoming problem it is important to decide:

1. Is the graph directed or undirected?
2. Are the edges of the graph weighted?
3. Is the graph I will encounter likely to be sparse or dense with edges?
4. Which data structure should you use to represent the graph efficiently?

### Shortest path problem

Given a weighted graph, find the shortest path of edges from node A to node B.

Algorithms: BFS (unweighted graph), Dijkstra's, Bellman-Ford, Floyd-Marshall, A*, and many more.

### Conectivity

Does there exist a path between node A and node B?

Typical solution: Use union find data structure or any search algorithm (e.g. DFS).

### Negative cycles

Does a weighted digraph have any negative cycles? If so, where?

Algorithms: Bellman-Ford and Floyd-Marshall.

### Strongly connected components

Strongly connected components (SCCs) can be thought of as self-contained cycles within a directed graph where every
vertex in a given cycle can reach every other vertex in the same cycle.

Algorithms: Tarjan's and Kosaraju's algorithm.

### Traveling salesman problem

Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits
each city exactly once and returns to the origin city?

Algorithms: Held-Karp, "Branch and bound" and many approximation algorithms.

### Bridges

A bridge / cut edge is any edge in a graph whose removal increases the number of connected components.

Bridges are important in graph theory because they often hint at weak points, bottlenecks or vulnerabilities in a graph.

### Articulation points

An articulation point / cut vertex is any node in graph whose removal increases the number of connected components.

Articulation points are important in graph theory because they often hint at weak points, bottlenecks or vulnerabilities
in a graph.

### Minimum spanning tree (MST)

A Minimum spanning tree (MST) is a subset of the edges of a connected, weighted graph that connects all the vertices
together, without any cycles and with the minimum possible total edge weight.

Algorithms: Kruskal's, Prim's & Boruvka's algorithm.

### Network flow: max flow

With an infinite input source how much "flow" can we push through the network?

Algorithms: Ford-Fulkerson, Edmonds-Karp & Dinic's algorithm.

## Depth First Search Algorithm

Most fundamental search algorithm used to explore nodes and edges of a graph.

Time Complexity: O(V+E)

Often part of other task like...

- count connected components
- determine connectivity
- find bridges/articulation points

Pseudo code:

```
# Global or class scope variables
n = number of nodes in the graph
graph = adjacency list representing graph
visited = [false, ..., false] # size n

function dfs(at):
    if visited[at]: return
    visited[at] = true
    
    neighbours = graph[at]
    
    for next in neighbours:
        dfs(next)
        
# Start DFS at node zero
start_node = 0
dfs(start_node)
```

### Example: Count components

```
# Global or class scope variables
n = number of nodes in the graph
graph = adjacency list representing graph
count = 0
components = empty integer array # size n
visited = [false, ..., false] # size n

function findComponents():
    for (i=0; i<n; i++):
        if !visited[i]:
            count++
            dfs(i)
    return (count, components)

function dfs(at):
    visited[at] = true
    components[at] = count
        
    for next in graph[at]:
        if(!visited[next]:
            dfs(next)
```

Other Examples:

- Compute a graph's minimum spanning tree
- Detect and find cycles in a graph
- Check if a graph is bipartite
- Find strongly connected components
- Topologically sort the nodes of a graph
- Find bridges and articulation points
- Find augementing paths in a flow network
- Generate mazes

## Breadth First Search Algorithm

Time Complexity: O(V+E)

The algorithm is particularly useful for finding the shortest path on unweighted graphs.

### Using a queue

The BFS algorithm uses a queue data structure to track which node to visit next. Upon reaching a new node the algorithm
adds it to the queue to visit it later. The queue data structure works just like a real world queue such as a waiting
line at a restaurant. People can either enter the waiting line (enqueue) or get seated (dequeue).

```
# Global or class scope variables
n = number of nodes in the graph
graph = adjacency list representing graph

# s = start node, e = end node, and 0 <= e, s < n
function bfs(s, e):

    # Do a BFS starting at node s    
    prev = solve(s)
        
    # Return reconstructed path from s -> e
    return reconstructPath(s, e, prev)
    
function solve(s):
    q = queue data structure with enqueue and dequeue
    q.enqueue(s)
    
    visited = [false, ..., false] # size of n
    visited[s] = true
    
    prev = [null, ..., null] # size of n
    
    while !q.isEmpty():
        node = q.dequeue()
        neighbours = g.get(node)
        
        for (next : neighbours):
            if !visited[next]:
                q.enqueue(next)
                visited[next] = true
                prev[next] = node
    
    return prev
    
function reconstructPath(s, e, prev):

    # Reconstruct path going backwards from e
    path = []
    
    for (at = e; at != null; at = prev[at]):
        path.add(at)
        
    path.reverse()
    
    # If s and e are connected return the path
    if path[0] == s:
        return path
        
    return []
```

## Breadth First Search grid shortest path

Many problems in graph theory can be represented using a grid. Grids are a form of implicit graph because we can
determine a node's neighbours based on our location within the grid.

Grid to graph (adjacency matrix).

Numbered grid:

|     |     |     |
|-----|-----|-----|
|     | 0   | 1   |
|     | 2   | 3   |
|     | 4   | 5   |

Adjacency matrix:

|     | 0   | 1   | 2   | 3   | 4   | 5   |
|-----|-----|-----|-----|-----|-----|-----|
| 0   | 0   | 1   | 1   | 0   | 0   | 0   |
| 1   | 1   | 0   | 0   | 1   | 0   | 0   |
| 2   | 1   | 0   | 0   | 1   | 1   | 0   |
| 3   | 0   | 1   | 1   | 0   | 0   | 1   |
| 4   | 0   | 0   | 1   | 0   | 0   | 1   |
| 5   | 0   | 0   | 0   | 1   | 1   | 0   |

## Trees

A tree is an undirected graph with no cycles.

Equivalently, a tree is a connected graph with N nodes and N-1 edges.

### Trees out in the world

Various practical applications:

Filesystem structures are inherently trees
Social hierarchies
Abstract syntax tree to decompose source code and mathematical expressions for easy evaluation
Every webpage is a tree as an HTML DOM structure
The decision outcomes in game theory are often modeled as trees for ease of representation
Family trees
Taxonomies

### Storing undirected trees

Start by labelling the tree nodes from [0, n)

Option 1: edge list storage representation: [(0, 1), (1, 4), ...]
Option 2: adjecency list representation: 0 -> [1]; 1 -> [0, 3, 4]; 2 -> [3]; 3 -> [1, 2, 6, 7]; ...
Option 3: adjecency matrix representation: see above

Definition "Rooted trees": One of the more interesting types of trees is a rooted tree which is a tree with a designated
root node.
Definition "Binary trees": Related to rooted trees are binary trees which are trees for which every node has at most two
child nodes.
Definition "Binary search trees": Related to binary trees are binary search trees which are trees which satisfy the BST
invariant which states that for every node x:

x.left.value <= x.value <= x.right.value

It's often useful to require quniqueness on the node values in your tree. Change the invariant to be strictly < rather
than <=:

x.left.value < x.value < x.right.value

### Storing rooted trees

Each node has access to a list of all its childrens.

Sometimes it's also useful to maintain a pointer to a node's parent node effectively making edges bidirectional.

If your tree is a binary tree, you can store it in a flattened array. The root node is always at index 0 and the
children of the current node i are accessed relative to position i.

Let i be the index of the current node:

left node: 2*i + 1
right node: 2*i + 2

Reciprocally, the parent of node i is:

floor((i-1)/2))
