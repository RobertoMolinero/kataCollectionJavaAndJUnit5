# Beginner tree algorithms

## Problem 1: Leaf node sum

What is the sum of all the leaf node values in a tree?

```
# Sums up leaf node values in a tree.
# Call function like: leafSum(root)
function leafSum(node):

    # Handle empty tree case
    if node == null:
        return 0;
    if isLeaf(node):
        return node.getValue()
        
    total = 0
    
    for child in node.getChildNodes():
        total += leafSum(child)
        
    return total
    
function isLeaf(node):
    return node.getChildNodes().size() == 0
```

## Problem 2: Tree Height

Find the height of a binary tree. The height of a tree is the number of edges from the root to the lowest leaf.

Assuming node x is not a leaf node, we're able to formulate a recurrence for the height:

h(x) = max(h(x.left), h(x.right)) + 1

```
# The height of a tree is the number of
# edges from the root to the lowest leaf.
function treeHeight(node):
    
    # Handle empty tree case
    if node == null:
        return -1
        
    # Identify leaf nodes an return zero
    if node.left == null and node.right == 0:
        return 0
        
    return max(treeHeight(node.left), treeHeight(node.right)) + 1
```

Optimized variant:

```
# The height of a tree is the number of
# edges from the root to the lowest leaf.
function treeHeight(node):
    
    # Return -1 when we hit a null node
    # to correct for the right height.
    if node == null:
        return -1
        
    return max(treeHeight(node.left), treeHeight(node.right)) + 1
```

# Rooting a tree

Sometimes it's useful to root an undirected tree to add structure to the problem you are trying to solve.

Rooting a tree is easily done depth first.

```
# TreeNode object structure
class TreeNode:
    # Unique integer id to identify this node
    int id;
    
    # Pointer to parent TreeNode reference. Only the root node has a null parent TreeNode reference.
    TreeNode parent;
    
    # List of pointers to child TreeNodes.
    TreeNode[] children;
```

```
# g is the graph/tree represented as an adjacency list with undirected edges. rootId is the id of the node to root the tree from.
function rootTree(g, rootId = 0):
    root = TreeNode(rootId, null, [])
    return buildTree(g, root, null)
    
# Build tree recursively depth first.
function buildTree(g, node, parent):
    
   for childId in g[node.id]:
   
        # Avoid adding an edge pointing back to the parent.
        if parent != null and childId == parent.id:
            continue
        
        child = TreeNode(childId, node, [])
        node.children.add(child)
        buildTree(g, child, node)
        
    return node       
```

# Finding tree center(s)

An interesting problem when you have an undirected tree is finding the tree's center node(s). This could coome in handy
if we wanted to select a good node to root our tree.

The center is always the middle vertex in every longest path along the tree.

Another approach to find the center is to iteratively pick off each leaf node layer like we were peeling an onion.

First compute the degree (number of connections) of all nodes. Each leaf node will have a degree of 1.

Remove every node with degree 1 and update the degree of the other nodes.

```
function treeCenters(g):
    n = g.numberOfNodes()
    degree = [0, 0, ..., 0]
    leaves = []
    for(i = 0; i<n; i++):
        degree[i] = g[i].size()
        if degree[i] == 0 or degree[i] == 1:
            leaves.add(i)
            degree[i] = 0
            
    count = leaves.size()
        
    while count < n:
        new_leaves = []
        for(node : leaves):
            for(neighbor : g[node]):
                degree[neighbor] = degree[neighbor] - 1
                
                if degree[neighbor] == 1:
                    new_leaves.add(neighbor)
            
            degree[node] = 0
        
        count += new_leaves.size()
        leaves = new_leaves
    
    return leaves # center(s)
```

# Identifying isomorphic trees

The question of asking whether two graphs G1 and G2 are isomorphic is asking whether they are structurally the same.

Determining if two graphs are isomorphic os not only not obvious to the human eye, but also a difficult problem for
computers.

It is still an open question as to whether the graph isomorphism problem is NP complete. However, many polynomial time
isomorphism algorithms exist for graph subclasses such as trees.

## Tree encoding

1. Leaf nodes are assigned Knuth tuples '()' to begin with.
2. Every time you move up a layer the labels of the previous subtrees get sorted lexicographically and wrapped in
   brackets.
3. You cannot process a node until you have processed all its children.

# Classical graph theory algorithms

## Topological sort algorithm

Many real world situations can be modeled as a graph with directed edges where some events must occure before others.

Examples:

- School class prerequisites
- Program dependencies
- Event scheduling
- Assembly instructions

A topological ordering is an ordering of the nodes in a directed graph where for each directed edge from node A to node
B, node A appears before node B in the ordering.

The topological sort algorithm can find a topological ordering in O(V+E) time!

Note: Topological orderings are NOT unique.

Note: Not every graph can have a topological odering. A graph which contains a cycle cannot have a valid ordering.

```
# Assumption: graph is stored as adjacency list
function topsort(graph):

   N = graph.numberOfNodes()
   V = [false, false, ...] # Length N
   ordering = [0, ..., 0] # Length N
   i = N-1 # Index for ordering array
   
   for(at = 0; at < N; at++):
      if V[at] == false:
         i = dfs(i, at, V, ordering, graph)
         
   return ordering
   
# Execute Depth First Search (DFS)
function dfs(i, at, V, ordering, graph):

   V[at] = true
   
   edges = graph.getEdgesOutFromNode(at)
   for edge in edges:
      if V[edge.to] == false:
         dfs(i, edge.to, V, ordering, graph)
         
   ordering[i] = at
   return i-1
```

# Shortest and longes paths on a Directed Acyclic Graph (DAG)
