package ATM;


import java.util.*;

public class MoneyReceiver implements Procedure{
    private static final String WELCOME_MESSAGE = "Please, contribute notes into reciever." +
            "\nEnter \"Finish\" to stop procedure, \"Cancel\" to interrupt and take money back" +
            " or \"Show\" to see you current balance.";

    private int sum = 0;
    private List<Cell> cells = new ArrayList<Cell>();
    private HashMap<Notes, Integer> cellsMap = new HashMap<Notes, Integer>();

    MoneyReceiver(List<Cell> c){
        showWelcomeMessage();
        c.forEach(cell -> {
            cellsMap.put(cell.getNote(), cell.getAmount());
        });
    }

    public List<Cell> input(){
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
                NotesChecker nc = new NotesChecker();
                if (nc.checkNote(Integer.parseInt(input))){

                    if (Objects.isNull(cellsMap.get(Notes.getNote(input)))){
                        cellsMap.put(Notes.getNote(input),0);
                    }
                    int a = cellsMap.get(Notes.getNote(input));
//                    Добавить добавление купюр, которых доселе не было в банкомате
//                    cellsMap.put(Notes.getNote(input),0);
                    cellsMap.put(Notes.getNote(input), ++a);

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
        cellsMap.forEach((k, v) -> {
            cells.add(new Cell(k,v));
        });
        Collections.sort(cells);
        return cells;
    }

    private Balance showBalance(int sum){
        return new Balance(sum);
    }

    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
