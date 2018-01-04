package ATM;

import java.util.List;
import java.util.Scanner;

public class MoneyExtradition implements Procedure {

    private static String WELCOME_MESSAGE = "What sum would you like to withdraw? \"Cancel\" to interrupt session.";
    private int ATMcash;
    private List<Cell> cells;
    private String resultMessage = "Your sum:\n%s";
    private String stroke = "%s money notes of %s money units each.\n";
    private int amount = 0;
    private int withdrawSum = 0;

    MoneyExtradition(List<Cell> cells){
        this.cells = cells;
        cells.forEach(cell -> {
            ATMcash += cell.getBalance();
        });
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

//            Check, enough cash in ATM or not.
            if (ATMcash < withdrawSum){
                throw new WrongSumException("Too big sum! Probably, you're minion of fortune," +
                        "but you'd be more modester. Keep calm and try again please:");
            }

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

    private void countNotes(int withdrawValue) throws WrongSumException{
        int localNoteValue;

        if (withdrawValue%cells.get(cells.toArray().length - 1).getNoteValue() != 0
                || withdrawValue%cells.get(cells.toArray().length - 1).getBalance() != 0) {
            throw new WrongSumException(String.format("Wrong sum! Please, enter number multiple of %s.",
                    + cells.get(cells.toArray().length - 1).getBalance()));
        }

        for (Cell cell : cells){
            localNoteValue = cell.getNoteValue();
//            Count necessary amount of notes
            amount = withdrawValue/localNoteValue;
//            If there is enough of notes
            if (cell.getAmount() >= amount){ //
                cell.setAmount(cell.getAmount() - amount);
//            If not enough notes, give all of them, zeroing cell and go to next cell
            } else {
                amount = cell.getAmount();
                cell.setAmount(0);
            }
//            Count rest part of withdraw sum.
            resultMessageConstructor(amount, localNoteValue);
            withdrawValue -= localNoteValue* amount;
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
