package algorithms.binary_search_tree;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;

    private Node root = null;

    private class Node {
        T data;
        Node left;
        Node right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private enum TreeTraversalOrder {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T element) {

        if (contains(element)) {
            return false;
        }

        root = add(root, element);
        nodeCount++;
        return true;
    }

    private Node add(Node node, T element) {

        if (node == null) {
            node = new Node(element, null, null);
        } else {
            if (element.compareTo(node.data) > 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }

        return node;
    }

    public boolean remove(T element) {

        if (contains(element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }

        return false;
    }

    private Node remove(Node node, T element) {

        if (node == null) return null;

        int compare = element.compareTo(node.data);

        if (compare < 0) {
            // Dig into the left subtree
            node.left = remove(node.left, element);
        } else if (compare > 0) {
            // Dig into the right subtree
            node.right = remove(node.right, element);
        } else {
            // Found it!
            if (node.left == null) {
                // No left subtree
                Node rightChild = node.right;
                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {
                // No right subtree
                Node leftChild = node.left;
                node.data = null;
                node = null;

                return leftChild;
            } else {
                // Both subtrees

                // Possibility 1: The leftmost node in the right subtree
                Node temp = digLeft(node.right);
                node.data = temp.data;
                node.right = remove(node.right, temp.data);

                // Possibility 2: The rightmost node in the left subtree
                // Node temp = digRight(node.left);
                // node.data = temp.data;
                // node.left = remove(node.left, temp.data);
            }
        }

        return node;
    }

    private Node digLeft(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private Node digRight(Node node) {
        Node current = node;

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node node, T element) {

        if (node == null) return false;

        int compare = element.compareTo(node.data);

        if (compare < 0) {
            return contains(node.left, element);
        } else if (compare > 0) {
            return contains(node.right, element);
        }

        return true;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public Iterator<T> traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
            default:
                return null;
        }
    }

    private Iterator<T> preOrderTraversal() {
        return null;
    }

    private Iterator<T> inOrderTraversal() {
        return null;
    }

    private Iterator<T> postOrderTraversal() {
        return null;
    }

    private Iterator<T> levelOrderTraversal() {
        return null;
    }
}
