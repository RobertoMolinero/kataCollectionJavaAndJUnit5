package algorithms.stacks;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FifoStack<T> implements Stack<T>, Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    public FifoStack() {
    }

    public FifoStack(T firstElement) {
        push(firstElement);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(T element) {
        list.addFirst(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.peekFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FifoStack<?> fifoStack = (FifoStack<?>) o;
        return list.equals(fifoStack.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
