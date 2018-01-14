package ru.spb.otus.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void copy(){
        list.add(10);
        MyArrayList<Integer> dest = new MyArrayList<>();
        dest.add(20);
        Collections.copy(dest,list);
        Assert.assertEquals(Integer.valueOf(10),dest.get(0));
    }

    @Test
    public void sort(){
        List<Object> objects = new ArrayList<>();

        list.add(10);
        list.add(5);
        list.add(15);
        Collections.sort(list);
        Assert.assertEquals(Integer.valueOf(5),list.get(0));
        Assert.assertEquals(Integer.valueOf(10),list.get(1));
        Assert.assertEquals(Integer.valueOf(15),list.get(2));
    }

}