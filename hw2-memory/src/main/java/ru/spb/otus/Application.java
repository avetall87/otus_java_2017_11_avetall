package ru.spb.otus;

import ru.spb.otus.domain.ExperimentalClass;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetall  05.12.17.
 */
public class Application {

    public static void main(String... args) throws InterruptedException {
//        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        List<String> list = new ArrayList();
       System.out.println("toal: "+getMemory() + " byte");
        String s = "";
        System.out.println("Empty string: "+getMemory());
    }

    private static Long getMemory(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

}
