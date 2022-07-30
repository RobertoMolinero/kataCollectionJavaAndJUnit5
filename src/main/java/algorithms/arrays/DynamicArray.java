package algorithms.arrays;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray<T> implements Iterable<T> {

    public static final int DEFAULT_CAPACITY = 16;

    private T[] array;
    private int length = 0;
    private int capacity;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format("Illegal capacity: %d", capacity));
        }

        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public DynamicArray(T[] array) {
        this.capacity = Math.max(DEFAULT_CAPACITY, array.length);
        this.length = array.length;
        this.array = (T[]) new Object[capacity];
        System.arraycopy(array, 0, this.array, 0, array.length);
    }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return array[index];
    }

    public void set(int index, T element) {
        array[index] = element;
    }

    public void add(T element) {
        if (length + 1 > capacity) {
            growTable();
        }

        array[length++] = element;
    }

    private void growTable() {
        capacity *= 2;

        if (capacity == 0) {
            capacity++;
        }

        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
    }

    public T removeAt(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Illegal index: %d", index));
        }

        var data = array[index];
        T[] newArray = (T[]) new Object[capacity];

        // Copy left part to element
        System.arraycopy(array, 0, newArray, 0, index);
        // Copy right part after element
        System.arraycopy(array, index + 1, newArray, index, (length - 1) - index);

        array = newArray;
        length -= 1;

        return data;
    }

    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index != -1) {
            removeAt(index);
            return true;
        }

        return false;
    }

    public int indexOf(Object object) {
        for (var i = 0; i < length; i++) {
            if (array[i].equals(object)) {
                return i;
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
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                index += 1;
                if (hasNext()) {
                    return array[index++];
                }
                throw new NoSuchElementException("Index out of range.");
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
        if (length == 0) return "[]";

        var stringBuilder = new StringBuilder(length).append("[");
        stringBuilder.append(array[0]);
        IntStream.range(1, length).mapToObj(i -> ", " + array[i]).forEach(stringBuilder::append);

        return stringBuilder.append("]").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicArray<?> that = (DynamicArray<?>) o;
        return length == that.length && capacity == that.capacity && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(length, capacity);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
