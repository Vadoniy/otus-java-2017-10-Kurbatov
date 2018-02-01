import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadoniy on 24.01.2018.
 */
public class TestObject {
    private String stringTest;
    private int intTest;
    private Boolean booleanTest;
    private int[] arrayPrimitiveTest;
    private String[] arrayStringTest;
    private List listTest;
    private Float floatTest;

    public TestObject(){
        stringTest = "Vadim Kurbatov";
        intTest = 28;
        booleanTest = true;
        arrayPrimitiveTest = new int[]{11,22,33,44,55};
        arrayStringTest = new String[]{"one","two","three","four","five"};
        listTest = new ArrayList();
        listTest.add(0);
        listTest.add("odin");
        listTest.add(true);
        floatTest = 3.14F;
    }
}
