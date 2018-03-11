/**
 * Created by Vadoniy on 04.03.2018.
 */
public class Main {
    private static MultiSorting sorter;

    public static void main(String[] args) {
        sorter = new MultiSorting(generateArray(43));
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