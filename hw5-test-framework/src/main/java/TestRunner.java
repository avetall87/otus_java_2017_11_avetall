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

    public static void runTest(String path){
        try {
            ReflectionHelper
                    .getClassesByName(path)
                    .forEach(TestRunner::runTestInClass);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void runTest(Class clazz){
        runTestInClass(clazz);
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
        String beforeMethodName = context.getBeforeMethodName();
        String afterMethodName = context.getAfterMethodName();

        context.getTestMethodsName().forEach(methodName->{
                Object testClass = ReflectionHelper.instantiate(clazz, null);
                    callMethod(testClass,beforeMethodName, null);
                    callMethod(testClass,methodName,null);
                    callMethod(testClass,afterMethodName,null);
        });
    }

    private static void callMethod(Object object, String name, Object[] args){
        if(object != null && (name != null && !name.equalsIgnoreCase("")))
            ReflectionHelper.callMethod(object,name,args);
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
