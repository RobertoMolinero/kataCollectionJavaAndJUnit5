package algorithms.avl;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AvlTree <T extends Comparable<T>> implements Iterable<T> {


    @Override
    public Iterator<T> iterator() {
        return null;
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
