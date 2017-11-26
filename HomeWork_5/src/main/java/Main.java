import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by Vadoniy on 26.11.2017.
 */
public class Main {

    public static void main(String[] args) {
        testAnnotations(testClass.class);
    }

    private static void testAnnotations(Class c){
        Method[] methods = c.getMethods();
        for (Method mth : methods){
            if (ReflectionHelper.checkAnnotationTest(mth, Before.class)){
                System.out.println(Before.beforeTest);
                continue;
            }
            if (ReflectionHelper.checkAnnotationTest(mth, After.class)){
                System.out.println(After.afterTest);
                continue;
            }
            if (ReflectionHelper.checkAnnotationTest(mth, Test.class)){
                System.out.println(Test.test);
                continue;
            }
        }
    }
}
