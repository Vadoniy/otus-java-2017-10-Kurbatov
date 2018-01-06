package Observer;

/**
 * Created by Vadoniy on 06.01.2018.
 */
public interface Observable {
    void addObserver(Observer observer);
//    void removeObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(String command);
}
