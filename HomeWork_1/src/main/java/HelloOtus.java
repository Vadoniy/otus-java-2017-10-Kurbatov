import com.google.common.collect.Lists;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HelloOtus {

    private static Collection<Integer> listInt = new ArrayList<>();
    private static String hello = "Hello, OTUS!\n";

    public static void main(String[] args) {
        listInt.add(3);
        listInt.add(2);
        listInt.add(1);
        listInt.add(0);
        hello += " Here is my list: \n";

        for (int i : listInt){
            hello += " " + i;
        }
        hello += "\n And reverse the same list:\n";

        for (int i : Lists.reverse((List<Integer>)listInt)){
            hello = hello + " " + i;
        }

        JOptionPane.showMessageDialog(null, hello);
    }
}
