import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadoniy on 09.03.2018.
 */
public class MergeThread extends Thread {
    private List<int[]> listOfArrays = new ArrayList<>();

    public MergeThread(List<int[]> listOfArrays){
        this.listOfArrays = listOfArrays;
    }

    public void run(){
        int[] sorted = merge(merge(listOfArrays.get(0), listOfArrays.get(1)), merge(listOfArrays.get(2), listOfArrays.get(3)));
        List<int[]> resultList = new ArrayList<>();
        int srcPos = 0;
        for (int i = 0; i<listOfArrays.toArray().length; i++){
            System.arraycopy(sorted, srcPos, listOfArrays.get(i), 0, listOfArrays.get(i).length);
            srcPos += listOfArrays.get(i).length;
        }
        listOfArrays = resultList;
    }

    private static int[] merge(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        int commonLength = length1 + length2;
        int[] sortedArray = new int[commonLength];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < commonLength) {
            if (j < length1 && k < length2) {

                if (array1[j] < array2[k]) {
                    sortedArray[i] = array1[j];
                    j++;
                } else {
                    sortedArray[i] = array2[k];
                    k++;
                }
            } else if (j < length1) {
                sortedArray[i] = array1[j];
                j++;
            } else {
                sortedArray[i] = array2[k];
                k++;
            }
            i++;
        }
        return sortedArray;
    }
}
