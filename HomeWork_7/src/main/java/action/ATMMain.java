package action;

import ATM.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vadoniy on 27.12.2017.
 */
public class ATMMain {

    public static void main(String[] args) {
        ATM atm = new ATM(fillCells());
        atm.interaction(atm);
        atm.showCellsState("initial");
        atm.setInitialCellsState();
        atm.showCellsState("final");
        atm.showCellsState("initial");
    }

    private static List<Cell> fillCells() {
        List<Cell> cells = new ArrayList<>();
//        for (Notes note : Notes.values()) {
//            cells.add(new Cell(note, 50));
//        }
        cells.add(new Cell(Notes.OneThousand,5));
        cells.add(new Cell(Notes.OneHundred,50));
        cells.add(new Cell(Notes.Ten,5));
        Collections.sort(cells);
        return cells;
    }
}
