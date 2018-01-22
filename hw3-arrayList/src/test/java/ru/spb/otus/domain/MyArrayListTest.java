package ru.spb.otus.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
        Collections.addAll(list,10,11,12);
        Assert.assertEquals(list.get(1),Integer.valueOf(10));
        Assert.assertEquals(list.get(2),Integer.valueOf(11));
        Assert.assertEquals(list.get(3),Integer.valueOf(12));
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
        list.add(10);
        list.add(5);
        list.add(15);
        Collections.sort(list);
        Assert.assertEquals(Integer.valueOf(5),list.get(0));
        Assert.assertEquals(Integer.valueOf(10),list.get(1));
        Assert.assertEquals(Integer.valueOf(15),list.get(2));
    }

}