package ATM;

public class NotesChecker {
    protected boolean checkNote(int noteValue){
        for (Notes note : Notes.values()){
            if (note.getValue() == noteValue){
                return true;
            }
            /*else if (note.ordinal() == Notes.values().length - 1){
                throw new RuntimeException("Unknown note.");
            }*/
        }
        return false;
    }
}
