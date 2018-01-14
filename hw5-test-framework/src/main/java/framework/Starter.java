package framework;

/**
 * Created by avetall  11.01.18.
 * More easy way is extend current class from org.junit.Assert. But less interesting !)
 */
public class Starter {


    public static void assertTrue(boolean result){
        if (!result)
            fail("Fail assertTrue check ! \n"+
                    "expected: TRUE; actual: FALSE.");

    }

    public static void assertFalse(boolean result){
        if (result != false)
            fail("Fail assertFalse check ! \n"+
                    "expected: FALSE; actual: TRUE.");

    }

    public static void assertEquals(Object expected, Object actual){
        if (!equalObjects(expected,actual))
            fail("Fail equal objects ! \n"+
                    "expected: "+ getStringVale(expected)+" actual: "+ getStringVale(actual)+".");

    }

    private static boolean equalObjects(Object o1, Object o2){
        return (o1 == null)? o2 == null : o1.equals(o2);
    }

    /**
     * Works only for simple object's such as: String, Date, Integer and all who extend Number, etc
     * @param o current object
     * @return String value from toString method
     */
    private static String getStringVale(Object o){
        return o.toString();
    }

    private static void fail(String message) {
        if (message == null)
            throw new AssertionError();
         else
            throw new AssertionError(message);

    }

}
