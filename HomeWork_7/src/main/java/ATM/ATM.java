package ATM;

import Memento.ATMCellsStateCaretaker;
import Memento.ATMMemento;
import Observer.Observer;

import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ATM implements Observer {

    private static final String FINAL = "final";
    private static final String INITIAL = "initial";
    private static final String CHECK = "check";
    private static final String RELOAD = "reload";

    private int sum;
    private String state = "";
    private List<Cell> cells;
    private ATMCellsStateCaretaker cellsStateCareTaker = new ATMCellsStateCaretaker();
    private ATMMemento atmMemento = new ATMMemento();
    private static final String WELCOME_MESSAGE = "What would you like to do?\nEnter the number:" +
            "\n1 - to contribute;" +
            "\n2 - to withdraw;" +
            "\n3 - to cancel operation.";

    public ATM(List<Cell> cells){
        Collections.sort(cells);
        this.cells = cells;
        atmMemento.setStateInitial(cells);
        atmMemento.setStateFinal(cells);
        cellsStateCareTaker.setAtmMemento(atmMemento);
    }

    public void interaction(ATM atm) {
        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if ("1".equalsIgnoreCase(input)) {
            atmMemento.setStateFinal(atm.provideContribution().input());
        } else if ("2".equalsIgnoreCase(input)) {
            atmMemento.setStateFinal(atm.provideWithdraw().input());
        } else {
            System.exit(0);
        }
    }

    @Override
    public void handleEvent(String command) {
        if (INITIAL.equalsIgnoreCase(command)) {
            showCellsState(INITIAL);
        } else if (FINAL.equalsIgnoreCase(command)){
            showCellsState(FINAL);
        } else if (RELOAD.equalsIgnoreCase(command)){
            setInitialCellsState();
        }
    }

    public void welcomeMessage(){
        System.out.println(WELCOME_MESSAGE);
    }

    public void setInitialCellsState(){
        cells = new CopyOnWriteArrayList<Cell>();
        cellsStateCareTaker.getAtmMemento().getStateInitial().
                forEach(cell -> cells.add(new Cell(cell.getNote(), cell.getAmount())));
        atmMemento.setStateFinal(cells);
    }

    public void showCellsState(String finalOrInit){

        if (FINAL.equalsIgnoreCase(finalOrInit)){
            atmMemento.getStateFinal().forEach(cell -> {
                sum += cell.getBalance();
                state += cell.show();
            });
        } else if (INITIAL.equalsIgnoreCase(finalOrInit)){
            atmMemento.getStateInitial().forEach(cell -> {
                sum += cell.getBalance();
                state += cell.show();
            });
        }

        System.out.println(String.format("ATM balance is %s money units.\nCells state:\n%s", sum, state));
        sum = 0;
        state = "";
    }

//    Factory
    private Procedure provideContribution(){
        return new MoneyReceiver(cells);
    }

    private Procedure provideWithdraw(){
        return new MoneyExtradition(cells);
    }
}
