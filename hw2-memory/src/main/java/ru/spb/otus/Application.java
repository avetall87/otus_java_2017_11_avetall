package ru.spb.otus;

import ru.spb.otus.domain.ExperimentalClass;

import java.lang.management.ManagementFactory;

/**
 * Created by avetall  05.12.17.
 */
public class Application {

    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 20_000_000;

        System.out.println("Starting the loop");
        while (true) {
            Object[] array = new Object[size];
            System.out.println("New array of size: " + array.length + " created");
            for (int i = 0; i < size; i++) {
//                array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]); //without String pool
                array[i] = new ExperimentalClass();
            }
            System.out.println("Created " + size + " objects.");
            Thread.sleep(1000); //wait for 1 sec
        }
    }
}
