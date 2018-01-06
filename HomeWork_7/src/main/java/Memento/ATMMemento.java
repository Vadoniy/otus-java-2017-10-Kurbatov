package Memento;

import ATM.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadoniy on 05.01.2018.
 */
public class ATMMemento {

    private List<Cell> stateInitial;
    private List<Cell> stateFinal;

    public void setStateInitial(List<Cell> init){
        stateInitial = new ArrayList<>();
        init.forEach(cell -> {
            stateInitial.add(new Cell(cell.getNote(), cell.getAmount()));
        });
    }

    public void setStateFinal(List<Cell> init){
        stateFinal = new ArrayList<>();
        init.forEach(cell -> {
            stateFinal.add(new Cell(cell.getNote(), cell.getAmount()));
        });
    }

    public List<Cell> getStateInitial(){ return stateInitial; }

    public List<Cell> getStateFinal(){ return stateFinal; }
}
