import framework.reflection.ReflectionHelper;
import framework.reflection.anotation.After;
import framework.reflection.anotation.Before;
import framework.reflection.anotation.Test;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by avetall  14.01.18.
 */
public class TestRunner {

    private TestRunner() {
    }

    public static void runTest(String packageName, String className){
        try {
            ReflectionHelper
                    .getClassesByName(packageName, className)
                    .forEach(TestRunner::runTestInClass);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void runTestInClass(Class clazz){
        List<Method> methods = Arrays.asList(clazz.getMethods());
        TestClassContext classContext = new TestClassContext();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation instanceof Before){
                    classContext.setBeforeMethodName(method.getName());
                    break;
                }
                if(annotation instanceof Test){
                    classContext.getTestMethodsName().add(method.getName());
                }
                if(annotation instanceof After){
                    classContext.setAfterMethodName(method.getName());
                    break;
                }
            }
        }
        invokeTest(classContext, clazz);
    }

    private static void invokeTest(TestClassContext context, Class clazz){
        Object testClass = ReflectionHelper.instantiate(clazz, null);

        ReflectionHelper.callMethod(testClass,context.getBeforeMethodName(), null);

        context.getTestMethodsName().forEach(methodName->{
            ReflectionHelper.callMethod(testClass,methodName,null);
        });

        ReflectionHelper.callMethod(testClass,context.getAfterMethodName(),null);
    }

    @Getter
    @Setter
    private static class TestClassContext{
        private String beforeMethodName;
        private String afterMethodName;
        private List<String> testMethodsName;

        public TestClassContext() {
            testMethodsName = new ArrayList<>();
        }

    }

}
