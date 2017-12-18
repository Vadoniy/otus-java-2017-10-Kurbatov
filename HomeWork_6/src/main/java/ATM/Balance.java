package ATM;

public class Balance {
    private static int balance = 0;
    private static String currentBalance = "Your current balance is %s money units.";

    Balance(int b){
        this.balance = b;
        shoMeTheMoney();
    }

    private static void shoMeTheMoney(){
        System.out.println(String.format(currentBalance, balance));
    }
}
