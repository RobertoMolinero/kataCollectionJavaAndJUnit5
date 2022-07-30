package algorithms.union_find;

public class UnionFind {

    // The number of elements in this union find
    private final int size;

    // Used to track the sizes of each of the components
    private final int[] sz;

    // id[i] points to the parent of i, if id[i]=i then i is a root node
    private final int[] id;

    // Tracks the number of components in the union find
    private int numOfComponents;

    public UnionFind(int size) {

        if (size <= 0) {
            throw new IllegalArgumentException("Size <= 0 ist not allowed.");
        }

        this.size = numOfComponents = size;
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {

        int root = p;

        while (root != id[root]) {
            root = id[root];
        }

        // Path compression
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return sz[find(p)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numOfComponents;
    }

    public void unify(int p, int q) {

        // Find the root of the components
        int root1 = find(p);
        int root2 = find(q);

        // Merge smaller component into the larger one.
        if (root1 == root2) return;

        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        numOfComponents--;
    }
}
