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

        if ("1".equalsIgnoreCase(input)) {
            provideContribution().input();
        } else if ("2".equalsIgnoreCase(input)) {
            provideWithdraw().input();
        } else {
            System.exit(0);
        }
    }

//    Factory
    private static Procedure provideContribution(){
        return new MoneyReceiver();
    }

    private static Procedure provideWithdraw(){
        return new MoneyExtradition();
    }
}
