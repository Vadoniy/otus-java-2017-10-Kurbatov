import java.util.*;

/**
 * Created by Vadoniy on 02.11.2017.
 */
public class Test {

    public static void main(String[] args) {

        MyArrayList<String> myArrayList = new MyArrayList<String>(
                new String[]{"MyArrayList1", "MyArrayList2", "MyArrayList3"});
        myArrayList.addAll(myArrayList, new String[]{"Check0", "Check1", "Check2"});
        check("MyArrayList.addAll\n", myArrayList);

        List<String> anotherArrayList = new MyArrayList<String>(
                new String[]{"anotherArrayList1", "anotherArrayList2", "anotherArrayList3"});
        Collections.addAll(anotherArrayList, new String[]{"Check0", "Check1", "Check2"});
        check("Collections.addAll\n", anotherArrayList);

        List<String> anotherArrayListCopy = new MyArrayList<String>(
                new String[]{"copy0", "copy1", "copy2", "copy3", "copy4", "copy5"});
        Collections.copy(anotherArrayList, anotherArrayListCopy);
        check("Collections.copy\n", anotherArrayList);

        MyArrayList<Integer> integerArrayList = new MyArrayList<Integer>(
                new Integer[]{89, 377, 8, 55, 0, 987, 13, 21});
        Collections.sort(integerArrayList, new MyComparator());
        /*Collections.sort(integerArrayList, (o1, o2) -> {
            int n = 0;
            if (o1.intValue() < o2.intValue()){
                n = -1;
            } else if (o1.intValue() > o2.intValue()){
                n = 1;
            }
            return n;
        });*/
        check("Collections.sort\n", integerArrayList);
    }

    static public void check(String title, List list){
        String str;
        if (title.isEmpty()){
            str = "";
        } else {
            str = title;
        }
        for (Object obj: list) {
            str += obj + "    ";
        }
        str += "\n";
        System.out.println(str);
    }
}
