package ATM;

public class Balance {
    private int balance = 0;
    private String currentBalance = "Your current balance is %s money units.";

    Balance(int b){
        this.balance = b;
        shoMeTheMoney();
    }

    private void shoMeTheMoney(){
        System.out.println(String.format(currentBalance, balance));
    }
}
