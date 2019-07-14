package ru.spb.otus.jdbc.jdbc;

public class TestClass {

    public TestClass2 getClass2() {
        return class2;
    }

    public TestClass2 class2 = new TestClass2();


    private class TestClass2 {
        public String msg = "hello";
    }


}
