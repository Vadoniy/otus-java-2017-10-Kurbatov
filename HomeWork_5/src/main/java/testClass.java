import annotations.After;
import annotations.Before;
import annotations.Test;

/**
 * Created by Vadoniy on 26.11.2017.
 */
public class testClass {

    @Before
    public static void testBeforeAnnotation(){
        System.out.println("Test Annotation Before");
    }

    @After
    public static void testAfterAnnotation(){
        System.out.println("Test Annotation After");
    }

    @Test
    public static void testTestAnnotation(){
        System.out.println("Test Annotation Test");
    }
}
