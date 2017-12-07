import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Vadim.Kurbatov on 28.11.2017.
 */
public class MyTestFramework {

    private static ArrayList<Method> methodsTest = new ArrayList<Method>();
    private static ArrayList<Method> methodsBefore = new ArrayList<Method>();
    private static ArrayList<Method> methodsAfter = new ArrayList<Method>();

    public static <T> void testAnnotations(Class<T> c) {
        Method[] methods = c.getMethods();
        for (Method mth : methods) {

            if (ReflectionHelper.checkAnnotationTest(mth, Before.class)) {
                methodsBefore.add(mth);
                continue;
            }
            if (ReflectionHelper.checkAnnotationTest(mth, Test.class)) {
                methodsTest.add(mth);
                continue;
            }
            if (ReflectionHelper.checkAnnotationTest(mth, After.class)) {
                methodsAfter.add(mth);
                continue;
            }
        }
        startTest(c);
    }

    private static <T> void startTest(Class<T> c){
        for (Method mthTest : methodsTest) {
            T testRun = ReflectionHelper.instantiate(c);

            for (Method mthBefore : methodsBefore) {
                ReflectionHelper.callMethod(testRun, mthBefore.getName(), mthBefore.getTypeParameters());
            }
            ReflectionHelper.callMethod(testRun, mthTest.getName(), mthTest.getTypeParameters());
            for (Method mthAfter : methodsAfter) {
                ReflectionHelper.callMethod(testRun, mthAfter.getName(), mthAfter.getTypeParameters());
            }
        }
    }
}
