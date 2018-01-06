package ATMDepartment;

import ATM.*;
import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vadoniy on 06.01.2018.
 */
public class ATMDepartment implements Observable {
    private List<Observer> atmList = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        atmList.add(observer);
    }

    @Override
    public void notifyObserver(String command) {
        atmList.forEach(observer -> observer.handleEvent(command));
    }

/*    @Override
    public void removeObserver(Observer observer) {
        atmList.remove(observer);
    }*/

    @Override
    public void removeObserver(Observer observer) {
        atmList.remove(observer);
    }

    public ATM workWithATM(int index) {
        return (ATM)atmList.get(index);
    }

    public List<Cell> fillCells() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(Notes.FiveThousand,10));
        cells.add(new Cell(Notes.OneThousand,50));
        cells.add(new Cell(Notes.FiveHundred,100));
        cells.add(new Cell(Notes.OneHundred,100));
        cells.add(new Cell(Notes.HalfHundred,100));
        cells.add(new Cell(Notes.Ten,100));
        Collections.sort(cells);
        return cells;
    }

    public List<Cell> fillCells(int ft, int ot, int fh, int oh, int hh, int ten) {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(Notes.FiveThousand,ft));
        cells.add(new Cell(Notes.OneThousand,ot));
        cells.add(new Cell(Notes.FiveHundred,fh));
        cells.add(new Cell(Notes.OneHundred,oh));
        cells.add(new Cell(Notes.HalfHundred,hh));
        cells.add(new Cell(Notes.Ten,ten));
        Collections.sort(cells);
        return cells;
    }
}