import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * Created by Vadoniy on 23.01.2018.
 */
public class Main {
    public static void main(String[] args) {
        JSONSerializer js = new JSONSerializer();
        TestObject testObject = new TestObject();

        testObject.stringTest = "Vadim Kurbatov";
        testObject.intTest = 28;
        testObject.booleanTest = true;
        testObject.arrayPrimitiveTest = new int[]{11,22,33,44,55};
        testObject.arrayStringTest = new String[]{"one","two","three","four","five"};
        testObject.listTest = new ArrayList();
        testObject.listTest.add(0);
        testObject.listTest.add("odin");
        testObject.listTest.add(true);
        testObject.floatTest = 3.14F;

        System.out.println(js.toJsonString(testObject));

        Gson gson = new Gson();
        System.out.println(gson.toJson(testObject));

        if (js.toJsonString(testObject).equals(gson.toJson(testObject))){
            System.out.println("Serialized correctly.");
        }
    }
}
