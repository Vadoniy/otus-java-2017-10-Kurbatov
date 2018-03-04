import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

/**
 * Created by Vadoniy on 04.03.2018.
 */

@RunWith(JUnit4.class)
public class MultiSortingTest {

    private static int[] testArray;
    private static MultiSorting multiSort;
    private static int[] multiSorted;

    @Before
    public void prepare(){
        testArray = generateArray(10);
        multiSort = new MultiSorting(testArray);
        multiSorted = multiSort.sort();
        Arrays.sort(testArray);
    }

    @Test
    public void compareSortings(){
        Assert.assertArrayEquals(testArray, multiSorted);
    }

    @Before
    public void clean(){
        multiSort = null;
        multiSorted = null;
        System.gc();
    }

    private static int[] generateArray(int elementsAmount){
        int[] array = new int[elementsAmount];

        for (int i = 0; i<elementsAmount; i++){
            array[i] = (int)(Math.random()*100);
        }

        return array;
    }
}
