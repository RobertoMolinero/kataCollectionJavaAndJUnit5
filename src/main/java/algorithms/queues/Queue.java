package algorithms.queues;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Queue<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    public Queue() {
    }

    public Queue(T firstElement) {
        offer(firstElement);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T peek() {
        if(isEmpty()) {
            throw new RuntimeException("Queue empty.");
        }
        return list.peekFirst();
    }

    public T poll() {
        if(isEmpty()) {
            throw new RuntimeException("Queue empty.");
        }
        return list.removeFirst();
    }

    public void offer(T element) {
        list.addLast(element);
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
}
