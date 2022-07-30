package algorithms.linked_lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {

    public static final String EMPTY_LIST = "Empty list";
    public static final String INDEX_OUT_OF_BOUNDS = "Index out of bounds";

    int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    public void clear() {
        Node<T> traverse = head;

        while (traverse != null) {
            Node<T> next = traverse.next;
            traverse.previous = null;
            traverse.next = null;
            traverse.data = null;
            traverse = next;
        }

        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T element) {
        addLast(element);
    }

    public void addFirst(T element) {

        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            head.previous = new Node<>(element, null, head);
            head = head.previous;
        }

        size++;
    }

    public void addLast(T element) {

        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            tail.next = new Node<>(element, tail, null);
            tail = tail.next;
        }

        size++;
    }

    public T peekFirst() {
        if (isEmpty()) throw new NoSuchElementException(EMPTY_LIST);
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new NoSuchElementException(EMPTY_LIST);
        return tail.data;
    }

    public T removeFirst() {

        if (isEmpty()) throw new NoSuchElementException(EMPTY_LIST);

        var data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.previous = null;
        }

        return data;
    }

    public T removeLast() {

        if (isEmpty()) throw new NoSuchElementException(EMPTY_LIST);

        var data = tail.data;
        tail = tail.previous;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    private T remove(Node<T> node) {

        if (node.previous == null) return removeFirst();
        if (node.next == null) return removeLast();

        node.next.previous = node.previous;
        node.previous.next = node.next;

        var data = node.data;

        node.data = null;
        node.previous = node.next = null;

        --size;

        return data;
    }

    public T removeAt(int index) {

        if (index < 0 || index >= size) throw new IllegalArgumentException(INDEX_OUT_OF_BOUNDS);

        int i;
        Node<T> traverse;

        if (index < size / 2) {
            for (i = 0, traverse = head; i != index; i++) {
                traverse = traverse.next;
            }
        } else {
            for (i = size - 1, traverse = tail; i != index; i--) {
                traverse = traverse.previous;
            }
        }

        return remove(traverse);
    }

    public boolean remove(Object object) {

        if (object == null) {
            for (Node<T> traverseForNull = head; traverseForNull != null; traverseForNull = traverseForNull.next) {
                if (traverseForNull.data == null) {
                    remove(traverseForNull);
                    return true;
                }
            }
        } else {
            for (Node<T> traverseForNotNull = head; traverseForNotNull != null; traverseForNotNull = traverseForNotNull.next) {
                if (object.equals(traverseForNotNull.data)) {
                    remove(traverseForNotNull);
                    return true;
                }
            }
        }

        return false;
    }

    public int indexOf(Object object) {

        var index = 0;
        Node<T> traverse;

        if (object == null) {
            for (traverse = head; traverse != null; traverse = traverse.next, index++) {
                if (traverse.data == null) {
                    return index;
                }
            }
        } else {
            for (traverse = head; traverse != null; traverse = traverse.next, index++) {
                if (object.equals(traverse.data)) {
                    return index;
                }
            }
        }

        return -1;
    }

    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> traverse = head;

            @Override
            public boolean hasNext() {
                return traverse != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();

                var data = traverse.data;
                traverse = traverse.next;
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        Node<T> traverse = head;
        while (traverse != null) {
            stringBuilder.append(traverse.data).append(", ");
            traverse = traverse.next;
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoublyLinkedList<?> that = (DoublyLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, head, tail);
    }

    private static class Node<T> {
        T data;
        Node<T> previous;
        Node<T> next;

        public Node(T data, Node<T> previous, Node<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data) && Objects.equals(previous, node.previous) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, previous, next);
        }
    }
}
