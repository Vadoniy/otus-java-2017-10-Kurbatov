import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadoniy on 09.03.2018.
 */
public class StartThread extends Thread {
    private List<Thread> threads = new ArrayList<>();

    public StartThread(List<Thread> threads){
        this.threads = threads;
    }

    public void run(){
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            if (thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
