import framework.reflection.ReflectionHelper;

import java.io.IOException;
import java.lang.reflect.*;

/**
 * Created by avetall  10.01.18.
 */
public class Application {
    public static void main(String[] args) {
        TestRunner.runTest("test.domain","StubTest");
    }
}
