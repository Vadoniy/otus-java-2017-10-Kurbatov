package ATM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vadoniy on 27.12.2017.
 */
public class TestClass {

    private static Map<Notes, Integer> cellMap = new HashMap<Notes, Integer>();
    private static List<Cell> cellsList = new ArrayList<Cell>();
    String str = "%s notes %s moneyUnits per each.";

    public static void main(String[] args) {
        String str = "%s notes %s moneyUnits per each.";

        int count = 50;
        int u = 0;

        /*for (Notes n : Notes.values()){
            cellsList.add(new Cell(n, f));
            System.out.println(cellsList.get(u).show());
            u++;
        }*/

        for (Notes no : Notes.values()){
            cellMap.put(no, count);
        }
        cellMap.forEach((no, cou) -> System.out.println(String.format(str, cou, no)));
    }
}
