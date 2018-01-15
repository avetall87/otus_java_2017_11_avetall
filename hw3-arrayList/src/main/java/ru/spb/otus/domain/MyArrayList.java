package ru.spb.otus.domain;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by avetall  05.12.17.
 */
public class MyArrayList<E> implements List<E> {

    // base properties
    private float loadFactor = .75f;
    private int size;
    private int capacity = 10;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    private Object arr[];


    public MyArrayList() {
        createArray();
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        createArray();
    }

    public MyArrayList(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        createArray();
    }

    private void createArray(){
        this.arr = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<E> iterator() {
        throw new RuntimeException("Not supported yet !");
    }

    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new RuntimeException("Not supported yet !");
    }

    public boolean add(E e) {
        ensureCapacityInternal(size+1);
        arr[size] = e;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        Object[] newArray = c.toArray();
        int numNew = newArray.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(newArray, 0, arr, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new RuntimeException("Not supported yet !");
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("Not supported yet !");
    }

    public void clear() {
        arr = null;
        arr = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) arr[index];
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        rangeCheck(index);
        return (E) (arr[index] = element);
    }

    public void add(int index, E element) {
        rangeCheck(index);
        arr[index] = element;

    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        rangeCheck(index);
        return (E) (arr[index] = null);
    }

    public int indexOf(Object o) {
        throw new RuntimeException("Not supported yet !");
    }

    public int lastIndexOf(Object o) {
        throw new RuntimeException("Not supported yet !");
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
//        throw new RuntimeException("Not supported yet !");
    }

    /**
     * An optimized version of AbstractList.ListItr
     */
    private class ListItr extends MyArrayList.Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.arr;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            try {
                int i = cursor;
                MyArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.arr;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) arr[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            final int size = MyArrayList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = MyArrayList.this.arr;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            lastRet = i - 1;
        }

    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new RuntimeException("Not supported yet !");
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(arr, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - arr.length > 0)
            grow();
    }

    private int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            return Math.max(capacity, minCapacity);
        }
        return minCapacity;
    }

    private void grow(){
        int oldArrCapacity = size;
        int newArrCapacity = oldArrCapacity<<1;

        if(arr.length == MAX_ARRAY_SIZE)
            throw new OutOfMemoryError();

        if (newArrCapacity - MAX_ARRAY_SIZE > 0){
            arr = Arrays.copyOf(arr,MAX_ARRAY_SIZE);
        }else {
            arr = Arrays.copyOf(arr,oldArrCapacity<<1);
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
