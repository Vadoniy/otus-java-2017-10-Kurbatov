import annotations.After;
import annotations.Before;
import annotations.Test;

/**
 * Created by Vadoniy on 26.11.2017.
 */
public class TestClass {

    @Before
    public void testBeforeAnnotation(){
        System.out.println("Test Annotation Before");
    }

    @After
    public void testAfterAnnotation(){
        System.out.println("Test Annotation After");
    }

    @Test
    public void testTestAnnotation(){
        System.out.println("Test Annotation Test " + 0);
    }

    @Test
    public void testTestAnnotation1(){
        System.out.println("Test Annotation Test " + 1);
    }

    @Test
    public void testTestAnnotation2(){
        System.out.println("Test Annotation Test " + 2);
    }
}
