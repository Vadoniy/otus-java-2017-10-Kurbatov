package ATM;

import java.util.Scanner;

public class MoneyExtradition implements Procedure {

    private static String WELCOME_MESSAGE = "What sum would you like to withdraw? \"Cancel\" to interrupt session.";
    private String resultMessage = "Your sum:\n%s";
    private String stroke = "%s money notes of %s money units each.\n";
    private int ammount = 0;
    private int withdrawSum = 0;

    MoneyExtradition(){
        showWelcomeMessage();
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if("cancel".equalsIgnoreCase(input)){
            System.out.println("Session was canceled.");
            System.exit(0);
        }
        try {
            withdrawSum += Integer.parseInt(input);
            countNotes(withdrawSum);
        } catch (NumberFormatException e){
            System.err.println("WRONG INPUT!!! Please, enter sum to withdraw or " +
                    "\"cancel\" to interrupt session!");
            input();
        } catch (WrongSumException w) {
            w.printStackTrace();
            withdrawSum = 0;
            input();
        }
    }

    private void countNotes(int value) throws WrongSumException{
        int testValue;

        if (value%10 != 0) {
            throw new WrongSumException("Wrong sum! Please, enter number multiple of 10.", value);
        }

        for (Notes note : Notes.values()) {
            testValue = note.getValue();
            ammount = value/testValue;
            resultMessageConstructor(ammount, testValue);
            value -= testValue*ammount;
        }

        System.out.println(resultMessage.substring(0, resultMessage.length()-2));
    }
    
    private void resultMessageConstructor(int number, int testValue){
        if (number == 0){
            return;
        }
        stroke = String.format(stroke, number, testValue);
        resultMessage = String.format(resultMessage,stroke + "%s");
        stroke = "%s money notes of %s money units each.\n";
    }

    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
