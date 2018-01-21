package framework.reflection;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Get from:
 * https://github.com/vitaly-chibrikov/otus_java_2017_11/blob/master/L05.1-reflection/src/main/java/ru/otus/l51/ReflectionHelper.java
 */
@SuppressWarnings("SameParameterValue")
public class ReflectionHelper {
    private ReflectionHelper() {
    }

    public static <T> T instantiate(Class<T> type, Object[] args) {
        try {
            if (args == null) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldValue(Object object, String name) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
        return null;
    }

    public static void setFieldValue(Object object, String name, Object value) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    public static Object callMethod(Object object, String name, Object[] args) {
        Method method = null;
        boolean isAccessible = true;
        try {
            if(args != null)
                method = object.getClass().getDeclaredMethod(name, toClasses(args));
            else
                method = object.getClass().getDeclaredMethod(name, null);

            isAccessible = method.isAccessible();
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
        return null;
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param path The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static List<Class> getClassesByName(String path) throws ClassNotFoundException, IOException {
        String systemPath = path.replace(".", "/");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null)
            throw new RuntimeException("Current class loader is null !");

        List<Class> result;
        if((result = getClazz(path)).size() > 0)
            return result;
        result = getClassesFromPackage(path, classLoader);

        return result;
    }

    /**
     *
     * @param path
     * @return
     */
    private static List<Class> getClazz(String path){
        List<Class> list = new ArrayList<>();
        try {
            Class clazz = Class.forName(path);
            list.add(clazz);
        } catch (ClassNotFoundException e) {
            System.out.println(path+ " is not a class !");
        }
        return list;
    }

    private static List<Class> getClassesFromPackage(String path, ClassLoader classLoader) throws IOException, ClassNotFoundException {
        Enumeration<URL> resources = getEnumerations(classLoader, path);

        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findAllClasses(directory, path));
        }

        return classes;
    }

    private static Enumeration<URL> getEnumerations(ClassLoader classLoader, String path) throws IOException {
       return classLoader.getResources(path.replace('.', '/'));
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findAllClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findAllClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    private static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args)
                .map(Object::getClass).toArray(Class<?>[]::new);
    }

}
