package ATM;

import java.util.*;

public class ATM {
    private List<Cell> cells;
    private static final String WELCOME_MESSAGE = "What would you like to do?\nEnter the number:" +
            "\n1 - to contribute;" +
            "\n2 - to withdraw;" +
            "\n3 - to cancel operation.";

    public ATM(List<Cell> cells){
        Collections.sort(cells);
        this.cells = cells;
    }

    public void interaction(ATM atm) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if ("1".equalsIgnoreCase(input)) {
            atm.provideContribution().input();
        } else if ("2".equalsIgnoreCase(input)) {
            atm.provideWithdraw().input();
        } else {
            System.exit(0);
        }
    }

    public void welcomeMessage(){
        System.out.println(WELCOME_MESSAGE);
    }

//    Factory
    private Procedure provideContribution(){
        return new MoneyReceiver();
    }

    private Procedure provideWithdraw(){
        return new MoneyExtradition(cells);
    }
}
