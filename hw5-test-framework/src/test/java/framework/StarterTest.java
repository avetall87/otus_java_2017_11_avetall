package framework;

import org.junit.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by avetall  14.01.18.
 */
public class StarterTest {


    private Boolean isTrue;
    private Boolean isFalse;

    @Before
    public void setUp() {
        isTrue = true;
        isFalse = false;
    }

    @Test
    public void PositiveAssertTrueTest() {
        Starter.assertTrue(isTrue);
    }

    @Test
    public void PositiveAssertFalseTest() {
        Starter.assertFalse(isFalse);
    }

    @Test(expected = Throwable.class)
    public void NegativeAssertTrueTest() {
        Starter.assertTrue(isFalse);
    }

    @Test(expected = Throwable.class)
    public void NegativeAssertFalseTest() {
        Starter.assertFalse(isTrue);
    }

    @Test
    public void PositiveAssertEqualsSimpleValueTest() {
        Starter.assertEquals(1,1);
    }

    @Test
    public void PositiveAssertEqualsObjectsTest() {
        Starter.assertEquals(Collections.singletonList(123), Collections.singletonList(123));
    }

    @Test(expected = Throwable.class)
    public void NegativeAssertEqualsTest() {
        Starter.assertEquals(1,2);
    }

    @Test(expected = Throwable.class)
    public void NegativeAssertEqualsObjectsTest() {
        Starter.assertEquals(Collections.singletonList(123), Collections.singletonList(321));
    }

}