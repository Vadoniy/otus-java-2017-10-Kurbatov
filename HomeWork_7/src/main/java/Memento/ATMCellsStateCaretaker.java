package Memento;

/**
 * Created by Vadoniy on 05.01.2018.
 */
public class ATMCellsStateCaretaker {
    private ATMMemento atmMemento;

    public void setAtmMemento(ATMMemento m){ this.atmMemento = m; }

    public ATMMemento getAtmMemento(){ return atmMemento; }
}