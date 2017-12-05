package ru.spb.otus.domain;

import java.util.*;

/**
 * Created by avetall  05.12.17.
 */
public class MyArrayList<T> implements List<T> {

    // base properties
    private float loadFactor = .75f;
    private int size;
    private int initSize = 10;

    private Object arr[];


    public MyArrayList() {
        createArray();
    }

    public MyArrayList(int initSize) {
        this.initSize = initSize;
        createArray();
    }

    public MyArrayList(int initSize, float loadFactor) {
        this.initSize = initSize;
        this.loadFactor = loadFactor;
        createArray();
    }

    private void createArray(){
        this.arr = new Object[initSize];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t) {
        if(needIncrease()){
            grow();
        }
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
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

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
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void grow(){
        int oldArrCapacity = size;
        arr = Arrays.copyOf(arr,oldArrCapacity<<1);
    }

    private boolean needIncrease(){
        return (size+1>=arr.length-1);
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
