import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Vadoniy on 23.01.2018.
 */
public class Main {

    private static JSONSerializer js;
    private static TestObject testObject;
    private static Gson gson;
    private static String str1 = "String";
    private static char ch = 'c';

    @Before
    public void createObjects(){
        js = new JSONSerializer();
        testObject = new TestObject();
        gson = new Gson();

    }

    @Test
    public void testObjectsTest(){
        Assert.assertEquals(gson.toJson(testObject), js.toJsonString(testObject));
    }

    @Test
    public void intTest(){
        Assert.assertEquals(gson.toJson(123), js.toJsonString(123));
    }

    @Test
    public void floatTest(){
        Assert.assertEquals(gson.toJson(2.3), js.toJsonString(2.3));
    }

    @Test
    public void stringTest(){
        Assert.assertEquals(gson.toJson(str1), js.toJsonString(str1));
    }

    @Test
    public void charTest(){
        Assert.assertEquals(gson.toJson(ch), js.toJsonString(ch));
    }

    @Test
    public void booleanTest(){
        Assert.assertEquals(gson.toJson(false), js.toJsonString(false));
    }

    @Test
    public void nullTest(){
        Assert.assertEquals(gson.toJson(null), js.toJsonString(null));
    }
}
