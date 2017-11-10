import java.util.Comparator;

public class MyComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        if (Integer.parseInt(o1.toString()) < Integer.parseInt(o2.toString()))
            return -1;
        else if (Integer.parseInt(o1.toString()) > Integer.parseInt(o2.toString()))
            return 1;
        else
            return 0;
    }
}
