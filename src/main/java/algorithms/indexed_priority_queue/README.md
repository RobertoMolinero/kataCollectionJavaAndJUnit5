# Indexed Priority Queue

An Indexed Priority Queue is a traditional priority queue variant which on top of the regular PQ operations supports "quick updates" and "quick deletions" of key-value pairs.

Example: Patients in a hospital. 

Each patient is assigned a prioritization between 0 and 10 based on their condition. The condition and knowledge of the disease and thus the prioritization can change at any time. Additionally, patients can leave the waiting area at any time. New patients can join at any time.

- Mary is in labour => 9
- Akarsh has a paper cut => 1
- James has an arrow in his leg => 7
- Naida's stomach hurts => 3
- Richard has a fractured wrist => 5
- Leah's stomach hurts => 3

## Implementation

Bidirectional Hashtable Value <> Key Index

Q: Why are we mapping keys to indexes in the domain [0, 0)?

A: Typically priority queues are implemented as heaps under the hood which internally use arrays which we want to facilitate indexing into.

## APQ ADT Interface

delete(ki)
valueOf(ki)
contains(ki)
peekMinKeyIndex()
pollMinKeyIndex()
peekMinValue()
pollMinValue()
insert(ki, value)
update(ki, value)
decreaseKey(ki, value)
increaseKey(ki, value)

## Complexity

| Operation | Indexed Binary Heap PQ |
| delete(ki) | O(log(n)) |
| valueOf(ki) | O(1) |
| contains(ki) | O(1) |
| peekMinKeyIndex() | O(1) |
| pollMinKeyIndex() | O(log(n)) |
| peekMinValue() | O(1) |
| pollMinValue() | O(log(n)) |
| insert(ki, value) | O(log(n)) |
| update(ki, value) | O(log(n)) |
| decreaseKey(ki, value) | O(log(n)) |
| increaseKey(ki, value) | O(log(n)) |

## Refresher on the binary heap

Every node is indexed an can be displayed as a List.

On index i (zero based):

Left child: 2i + 1
Right child: 2i + 2

## IPQ as binary heap

| Name | Key Index | Value |
| Anna | 0 | 3 |
| Bella | 1 | 15 |
| Carly | 2 | 11 |
| Dylan | 3 | 17 |
| Emily | 4 | 7 |
| Fred | 5 | 9 |
| George | 6 | 2 |
| Henry | 7 | 1 |
| Isaac | 8 | 6 |
| James | 9 | 5 |
| Kelly | 10 | 16 |
| Laura | 11 | 4 |

## Insertion

Insert at the end and swap up.

```
# Inserts a value into the min indexed binary
# heap. The key index must not already be in 
# the heap and the value must not be null.
function insert(ki, value):
    values[ki] = value
    # 'sz' is the current size of the heap
    pm[ki] = sz
    im[sz] = ki
    swim(sz)
    sz = sz + 1 
```

Swim Pseudo Code

```
function swim(i):
    for (p=(i-1)/2; i>0 and less(i, p)):
        swap(i, p)
        i = p
        p = (i-1)/2
        
function swap(i, j):
    pm[im[j]] = i     
    pm[im[i]] = j
    tmp = im[i]
    im[i] = im[j]
    im[j] = tmp
    
function less(i, j):
    return values[im[i]] < values[im[j]]    
```

## Polling and Removing

Polling means removing the root node.

Removing the root node:

- Change the position of the root node with the last node
- Remove the last node
- Move the swapped purple node down

Removal Pseudo Code

```
# Deletes the node with the key index ki
# in the heap. The key index must exist
# and be present in the heap.
function remove(ki):
    i = pm[ki]
    swap(i, sz)
    sz = sz - 1
    sink(i)
    swim(i)
    values[ki] = null
    pm[ki] = -1
    im[sz] = -1
```

Sink Pseudo Code

```
# Sinks the node at index i by swapping
# itself with the smallest of the left
# or the right child node.
function sink(i):
    while true:
        left = 2*i + 1
        right = 2*i + 2
        smallest = left
        
        if right < sz and less(right, left):
            smallest = right
            
        if left >= sz or less(i, smallest):
            break
            
        swap(smallest, i)
        i = smallest
```

## Updates

Three steps:

- Find
- Update
- Sink/Swim

Update Pseudo Code

```
# Updates the value of a key in the binary
# heap. The key index must exist and the
# value must not be null.
function update(ki, value):
    i = pm[ki]
    values[ki] = value
    sink(i)
    swim(i)
```

## Increase and Decrease

In many applications (e.g. Dijkstra's and Prims algorithm) it is often useful to only update a given key to make its value either always smaller (or larger). In the event that a worse value is given the value in the IPQ should not be updated.

In such situations it is useful to define a more restrictive form of update operation we call increaseKey(ki,v) and decreaseKey(ki, v).

```
# For both these functions assume ki and value
# are valid inputs and we are dealing with a
# min indexed binary heap.
function decreaseKey(ki, value):
    if less(value, values[ki]):
        values[ki] = value
        swim(pm[ki])
        
function increaseKey(ki, value):
    if less(values[ki], value):
        values[ki] = value
        sink(pm[ki])
```

























