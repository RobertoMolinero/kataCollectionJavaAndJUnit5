# AVL

A Balanced Binary Search Tree (BBST) is a self-balancing binary search tree. This type of tree will adjust itself in order to maintain a low (logarithmic) height allowing for faster operations such as insertions and deletions.

## Complexity

### Binary Search Tree

| Operation  | Average    | Worst  |
|------------|------------|--------|
| Insert     | O(log(n))  | O(n)   |
| Delete     | O(log(n))  | O(n)   |
| Remove     | O(log(n))  | O(n)   |
| Search     | O(log(n))  | O(n)   |

Worst Case could be a sequence of increasing numbers.

### Balanced Binary Search Tree

| Operation  | Average    | Worst      |
|------------|------------|------------|
| Insert     | O(log(n))  | O(log(n))  |
| Delete     | O(log(n))  | O(log(n))  |
| Remove     | O(log(n))  | O(log(n))  |
| Search     | O(log(n))  | O(log(n))  |

## Tree rotations

The secret ingredient to most BBST algorithms is the clever usage of a tree invariant and tree rotations.

A tree invariant is a property/rule you impose on your tree that it must meet after every operation. To ensure that the invariant is always satisfied a series of tree rotations are normally applied.

All BBSTs are BSTs so the BST invariant holds. This means that for every node n, n.left < n and n < n.right.

```
function rightRotate(A):
    B := A.left
    A.left = B.right
    B.right = A
    return B 
```

A   ->  B   ->  D
            ->  E 
    ->  C

Algorithm:

1. Two pointers to A and B
2. Disconnect A to B
3. Connect A to Bs right child
4. Disconnect B and Bs right child
5. Connect B to A

Note: It's possible that before the rotation node A had a parent whose left/right pointer referenced it. It's very important that this link be updated to reference B. This is usually done on the recursive callback using the return value of rotateRight. 

## Tree rotations (Double connected)

```
function rightRotate(A):
    P := A.parent
    B := A.left
    A.left = B.right
    
    if B.right != null:
        B.right.parent = A
        
    B.right = A
    A.parent = B
    B.parent = P
    
    # Upate parent down Link
    if P != null:
        if P.left == A:
            P.left = B
        else:
            P.right = B
    
    return B
```

Algorithm:

1. Three pointers to P, A and B
2. A.left to B.right and B.right to A
3. B.right to A
4. A.parent to B
5. B.parent to P
6. P.left (or P.right) to B

## AVL tree insertions

The property which keeps an AVL tree balanced is called the Balance Factor (BF).

BF(node) = H(node.right) - H(node.left)

Where H(x) is the height of node x. Recall that H(x) is calculated as the number of edges between x and the furthest leaf.

The invariant in the AVL which forces it to remain balanced is the requirement that the balance factor is always either -1, 0 or +1.

Node informations:

1. The actual value we are storing in the node. (NOTE: This value must be comparable so we know how to insert it.)
2. A value storing this node's balance factor.
3. The height of this node in the tree.
4. Pointers to the lef/right child nodes.

```
function insert(value):

    if value == null:
        return false
        
    if !contains(root, value):
        root = insert(root, value)
        nodeCount = nodeCount + 1
        return true
        
    return false 
```

```
function insert(node, value):

    if node == null:
        return Node(value)
        
    cmp := compare(value, node.value)
    
    if cmp < 0:
        node.left = insert(node.left, value)
    else:
        node.right = insert(node.right, value)    
        
    # Update balance factor and height values
    update(node)
        
    # Rebalance tree
    return balance(node) 
```

```
function update(node):

    lh := -1
    rh := -1
    
    if node.left != null: lh = node.left.height
    if node.right != null: rh = node.right.height
    
    # Update this node's height
    node.height = 1 + max(lh, rh)
    
    # Update balance factor
    node.bf = rh - lh
```

```
function balance(node):
    # Left heavy subtree
    if node.bf == -2:
        if node.left.bf <= 0:
            return leftLeftCase(node)
        else
            return leftRightCase(node)
            
    # Right heavy subtree
    if node.bf == 2:
        if node.right.bf >= 0:
            return rightRightCase(node)
        else
            return rightLeftCase(node)
            
    return node
```

```
function leftLeftCase(node):
    return rightRotation(node)

function leftRightCase(node):
    node.left = leftRotation(node.left)
    return lefLeftCase(node)

function rightRightCase(node):
    return leftRotation(node)

function rightLeftCase(node):
    node.right = rightRotation(node.right)
    return rightRightCase(node)
```

```
function rightRotate(A):
    B := A.left
    A.left = B.right
    B.right = A
    
    # After rotation update balance factor and height values
    update(A)
    update(B)
    
    return B 
```

## AVL tree removals

Removing elements from a Binary Search Tree (BST) can be seen as a two-step process:

1. Find the element we wish to remove.
2. Replace the node we want to remove with its successor (if any) to maintain the BST invariant.

Find Phase:

1. We it a null node at which point we know the value does not exist within out BST.
2. Comparator value equal to 0. (Found it!)
3. Comparator value less than 0. (Continue searching in the left subtree)
4. Comparator value greater than 0. (Continue searching in the right subtree)

Remove Phase:

Four Cases!

1. Node to remove is a leaf node. > just remove
2. Node to remove has a left subtree. > set the subtree as successor
3. Node to remove has a right subtree. > set the subtree as successor
4. Node to remove has a left subtree and a right subtree. > set one subtree as successor (the largest value in the left subtree or the smallest value in the right subtree)




















