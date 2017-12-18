package ATM;


import java.util.Scanner;

public class MoneyReceiver implements Procedure{
    private static final String WELCOME_MESSAGE = "Please, contribute notes into reciever." +
            "\nEnter \"Finish\" to stop procedure, \"Cancel\" to interrupt and take money back" +
            " or \"Show\" to see you current balance.";

    private static int sum = 0;

    MoneyReceiver(){
        showWelcomeMessage();
    }

    public void input(){
        String input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if ("finish".equalsIgnoreCase(input)){
                System.out.println("You have finished contributing.");
                break;
            } else if("cancel".equalsIgnoreCase(input)){
                System.out.println("Input was canceled, take your money back please: " + sum);
                System.exit(0);
            } else if ("show".equalsIgnoreCase(input)){
                showBalance(sum);
                continue;
            }
            try {
                if (NotesChecker.checkNote(Integer.parseInt(input))){
                    sum += Integer.parseInt(input);
                    System.out.println("Go on or finish/cancel/show.");
                } else {
                    System.err.println("Wrong note, please, try another.");
                }
            } catch (NumberFormatException e){
                System.err.println("WRONG INPUT!!! Please, enter \"finish\" to stop contributing or" +
                        " \"cancel\" to interrupt! Else you can go on to give me your money...");
            }
        }
        System.out.println(sum + " of money units were contributed on your deposit.");
    }

    private Balance showBalance(int sum){
        return new Balance(sum);
    }

    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
