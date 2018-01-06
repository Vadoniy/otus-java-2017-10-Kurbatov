package ATM;

public class NotesChecker {
    protected boolean checkNote(int noteValue){
        for (Notes note : Notes.values()){
            if (note.getValue() == noteValue){
                return true;
            }
        }
        return false;
    }
}
