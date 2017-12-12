package ru.spb.otus.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by avetall  10.12.17.
 */
public class MyArrayListTest {

    MyArrayList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new MyArrayList<>();
    }

    @Test
    public void size() {
        list.add(1);
        Assert.assertEquals(1,list.size());
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void contains() {
    }

    @Test
    public void iterator() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void toArray1() {
    }

    @Test
    public void add() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void containsAll() {
    }

    @Test
    public void addAll() {

        list.add(10);

        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);

        list.addAll(myArrayList);

        Assert.assertTrue(myArrayList.size() == 3);
    }

    @Test
    public void addAll1() {
    }

    @Test
    public void removeAll() {
    }

    @Test
    public void retainAll() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void get() {
    }

    @Test
    public void set() {
    }

    @Test
    public void add1() {
    }

    @Test
    public void remove1() {
    }

    @Test
    public void indexOf() {
    }

    @Test
    public void lastIndexOf() {
    }

    @Test
    public void listIterator() {
    }

    @Test
    public void listIterator1() {
    }

    @Test
    public void subList() {
    }
}