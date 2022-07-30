# Binary Search Tree

A tree is an undirected graph which satisfies any of the following definitions:

Terminology:

- parent
- child
- leaf node
- subtree

- An acyclic connected graph
- A conneced graph with N nodes an N-1 edges
- An graph in which any two vertices are connected by exactly one path

A binary search tree is a binary tree that satisfies the BST invariant: left subtree has smaller elements and right
subtree has larger elements.

## When and where are BST used

### Binary Search Tree

- Implementation of some map and set ADTs
- Red Black Trees
- AVL Trees
- Splay Trees

### Other

- Used in the implementation of binary heaps
- Syntax trees (used by compiler and calculators)
- Treap - a probalistic DS (uses a randomized BST)

## Complexity

| Operation | Average   | Worst |
|-----------|-----------|-------|
| Insert    | O(log(n)) | O(n)  |
| Delete    | O(log(n)) | O(n)  |
| Remove    | O(log(n)) | O(n)  |
| Search    | O(log(n)) | O(n)  |

## Insert Elements

Elements must be comparable!

After comparing:

- Recurse down left subtree (< case)
- Recurse down right subtree (> case)
- Handle finding a dublicate value (= case)
- Create a new node (found a null leaf)

## Remove Elements

Two steps:

1. Find the element we wish to remove
2. Replace the node we want to remove with its successor (if any) to maintain the BST invariant.

### Find phase

Four things can happen:

1. We hit a null node at which point we know the value does not exist within our BST.
2. Comparator value equal to 0 (found!).
3. Comparator value less than 0 (the value, it it exists, is in the left subtree).
4. Comparator value greater than 0 (the value, it it exists, is in the right subtree).

### Replace phase

Four things can happen:

1. Node to remove is a leaf node. -> just remove the node
2. Node to remove has a right subtree. -> successor of the node will be the root node of the left subtree.
3. Node to remove has a left subtree. -> successor of the node will be the root node of the right subtree.
4. Node to remove has a left subtree and a right subtree. -> successor of the node will be the largest value in the left
   subtree OR the smallest value in right subtree.

## Binary Tree Traversals

### Preorder

```
preorder(node):
    if node == null: return
    print(node.value)
    preorder(node.left)
    preorder(node.right)
```

### Inorder

```
inorder(node):
    if node == null: return
    inorder(node.left)
    print(node.value)
    inorder(node.right)
```

Result: all values are visited in increasing order!

### Postorder

```
postorder(node):
    if node == null: return
    postorder(node.left)
    postorder(node.right)
    print(node.value)
```

### Level Order

Breadth First Search from the root node down to the leaf nodes.

FIFO Queue: At each iteration add the left and the right child of the current node to the queue.
