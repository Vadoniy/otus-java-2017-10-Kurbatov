import java.util.Arrays;

/**
 * Created by Vadoniy on 28.02.2018.
 */
public class SortingThread extends Thread {

    private int[] array;

    public SortingThread(int[] array) {
        this.array = array;
    }

    public void run(){
        Arrays.sort(array);
//        showArray();
    }

    private void showArray() {
        String str = "";
        for (int i = 0; i<array.length; i++){
            str+=array[i] + " ";
        }
        System.out.println(str);
    }
}
