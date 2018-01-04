package ATM;

/**
 * Created by Vadoniy on 24.12.2017.
 */
public class Cell implements Comparable<Cell>{

    private Notes note;
    private int amount = 0;

    public Cell(Notes n, int a){
        this.note = n;
        this.amount = a;
    }

    public int getBalance(){
        return note.getValue()*amount;
    }

    public int getNoteValue(){
        return note.getValue();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Cell o) {
        if (note.getValue() > o.getNoteValue()){
            return -1;
        } else if (note.getValue() < o.getNoteValue()){
            return 1;
        }
        return 0;
    }
}
