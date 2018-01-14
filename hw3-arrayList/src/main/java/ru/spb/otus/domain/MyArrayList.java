package ru.spb.otus.domain;

import java.util.*;

/**
 * Created by avetall  05.12.17.
 */
public class MyArrayList<T> implements List<T> {

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

    public Iterator<T> iterator() {
        throw new RuntimeException("Not supported yet !");
    }

    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new RuntimeException("Not supported yet !");
    }

    public boolean add(T t) {
        ensureCapacityInternal(size+1);
        arr[size] = t;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        Object[] newArray = c.toArray();
        int numNew = newArray.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(newArray, 0, arr, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
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
    public T get(int index) {
        rangeCheck(index);
        return (T) arr[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        rangeCheck(index);
        return (T) (arr[index] = element);
    }

    public void add(int index, T element) {
        rangeCheck(index);
        arr[index] = element;

    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        rangeCheck(index);
        return (T) (arr[index] = null);
    }

    public int indexOf(Object o) {
        throw new RuntimeException("Not supported yet !");
    }

    public int lastIndexOf(Object o) {
        throw new RuntimeException("Not supported yet !");
    }

    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public ListIterator<T> listIterator(int index) {
        throw new RuntimeException("Not supported yet !");
    }

    public List<T> subList(int fromIndex, int toIndex) {
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
