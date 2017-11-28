import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Vadim.Kurbatov on 28.11.2017.
 */
public class MyTestFramework {

    private static ArrayList<Method> methodsTest = new ArrayList<Method>();
    private static ArrayList<Method> methodsBefore = new ArrayList<Method>();
    private static ArrayList<Method> methodsAfter = new ArrayList<Method>();

    public static void testAnnotations(Class c) {
        Method[] methods = c.getMethods();
        for (Method mth : methods) {

            if (ReflectionHelper.checkAnnotationTest(mth, Before.class)) {
//                System.out.println(Before.beforeTest);
                methodsBefore.add(mth);
                continue;
            }
            if (ReflectionHelper.checkAnnotationTest(mth, Test.class)) {
//                System.out.println(Test.test);
                methodsTest.add(mth);
                continue;
            }
            if (ReflectionHelper.checkAnnotationTest(mth, After.class)) {
//                System.out.println(After.afterTest);
                methodsAfter.add(mth);
                continue;
            }
        }
        startTest();
    }

    private static void startTest(){
        try {
            for (Method mthTest : methodsTest){
                for (Method mthBefore : methodsBefore){
                    mthBefore.invoke(null, null);
                }
                mthTest.invoke(null, null);
                for (Method mthAfter : methodsAfter){
                    mthAfter.invoke(null, null);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
