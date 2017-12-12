package ru.spb.otus;

import ru.spb.otus.domain.MyArrayList;

import java.util.ArrayList;

/**
 * Created by avetall  05.12.17.
 */
public class Application {
    public static void main(String[] args) {

        MyArrayList<Integer> integers = new MyArrayList<>();

        for (int i = 0; i <1000; i++) {
            integers.add(i);
        }

        System.out.println(integers.size());
    }
}
