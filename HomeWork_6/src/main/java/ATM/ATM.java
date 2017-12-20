package ATM;

import java.util.Scanner;

public class ATM {
    private static final String WELCOME_MESSAGE = "What would you like to do?\nEnter the number:" +
            "\n1 - to contribute;" +
            "\n2 - to withdraw;" +
            "\n3 - to cancel operation.";

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ATM atm1 = new ATM();

        if ("1".equalsIgnoreCase(input)) {
            atm1.provideContribution().input();
        } else if ("2".equalsIgnoreCase(input)) {
            atm1.provideWithdraw().input();
        } else {
            System.exit(0);
        }
    }

//    Factory
    private Procedure provideContribution(){
        return new MoneyReceiver();
    }

    private Procedure provideWithdraw(){
        return new MoneyExtradition();
    }
}
