import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadoniy on 04.03.2018.
 */
public class Main {
    private static MultiSorting sorter;

    public static void main(String[] args) {
        sorter = new MultiSorting(generateArray(3));
        sorter.show(sorter.sort());
    }

    private static int[] generateArray(int elementsAmount){
        int[] array = new int[elementsAmount];

        for (int i = 0; i<elementsAmount; i++){
            array[i] = (int)(Math.random()*100);
        }

        return array;
    }
}


    /*public static void main(String[] args) {

        System.out.println("Initial array:");
        show(array);
        System.out.println("Initial arrays length: " + array.length);


        int subLength = array.length/4;
        List<int[]> listOfArr = new ArrayList<>();
        List<Thread> listOfThr = new ArrayList<>();
        listOfArr.addAll(createArraysList(subLength));
        System.out.println("\nSubArrays before sorting:");
        listOfArr.forEach(array -> show(array));
        listOfThr.addAll(createThreadsList(listOfArr));
        startAndJoin(listOfThr);
        System.out.println("\nSubArrays after sorting:");
        listOfArr.forEach(ar -> show(ar));

        System.out.println("\nFinal array");
        show(merge(merge(listOfArr.get(0), listOfArr.get(1)),merge(listOfArr.get(2), listOfArr.get(3))));
    }*/