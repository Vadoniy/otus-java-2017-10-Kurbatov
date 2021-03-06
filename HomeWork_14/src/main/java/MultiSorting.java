import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadoniy on 28.02.2018.
 */
public class MultiSorting {

    private int[] array;

    public MultiSorting(int[] array) {
        this.array = array;
    }

    public int[] sort(){

        int subLength = array.length/4;
        List<int[]> listOfArr = new ArrayList<>();
        List<Thread> listOfThr = new ArrayList<>();
        listOfArr.addAll(createArraysList(subLength));
        listOfThr.addAll(createThreadsList(listOfArr));
        startThreads(listOfThr);
        return merge(merge(listOfArr.get(0), listOfArr.get(1)), merge(listOfArr.get(2), listOfArr.get(3)));
    }

    public void show(int[] array){
        StringBuilder sb = new StringBuilder("Elements: ");

        for (int t : array){
            sb.append(String.format(" %s;", t));
        }
        System.out.println(sb);
    }

    private static void startThreads(List<Thread> threads){
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            if (thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
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

    private List<int[]> createArraysList(int subLength){
        List<int[]> listOfArr = new ArrayList<>();
        listOfArr.add(createSubArray(array, 0, subLength));
        listOfArr.add(createSubArray(array, subLength, subLength));
        listOfArr.add(createSubArray(array, subLength*2, subLength));
        listOfArr.add(createSubArray(array, subLength*3, array.length-subLength*3));

        return listOfArr;
    }

    private static List<Thread> createThreadsList(List<int[]> arraysList){
        List<Thread> threadList = new ArrayList<>();
        arraysList.forEach(array -> threadList.add(new SortingThread(array)));
        return threadList;
    }

    private static int[] createSubArray(int[] sourceArray, int indexSrc, int subLength){
        int[] subArray = new int[subLength];
        System.arraycopy(sourceArray, indexSrc, subArray, 0, subLength);

        return subArray;
    }
}
