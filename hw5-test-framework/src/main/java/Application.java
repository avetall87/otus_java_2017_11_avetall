import test.domain.StubTest;

/**
 * Created by avetall  10.01.18.
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("---------------Full file name------------------------------");
            TestRunner.runTest("test.domain.StubTest");
        System.out.println("---------------Class Type------------------------------");
            TestRunner.runTest(StubTest.class);
        System.out.println("---------------Package------------------------------");
            TestRunner.runTest("test.domain");
            TestRunner.runTest("test.domain");

    }
}
