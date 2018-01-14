package test.domain;

import domain.Stub;
import framework.reflection.anotation.After;
import framework.reflection.anotation.Before;
import framework.reflection.anotation.Test;

import static framework.Starter.assertEquals;
import static framework.Starter.assertFalse;
import static framework.Starter.assertTrue;

/**
 * Created by avetall  11.01.18.
 */
public class StubTest {

    private Stub stub;

    @Before
    public void setUp(){
        stub = new Stub();
        System.out.println("setUp method was finished");
    }

    @Test
    public void sum() {
        assertEquals(stub.sum(1,10),11);
        System.out.println("test 1 - was successfully completed");
    }

    @Test
    public void equalTrue() {
        assertTrue(stub.sum(1,10) == 11);
        System.out.println("test 2 - was successfully completed");
    }

    @Test
    public void equalFalse() {
        assertFalse(stub.sum(1,11) == 11);
        System.out.println("test 3 - was successfully completed");
    }

    @After
    public void tearDown(){
        System.out.println("tearDown method was finished !");
    }
}
