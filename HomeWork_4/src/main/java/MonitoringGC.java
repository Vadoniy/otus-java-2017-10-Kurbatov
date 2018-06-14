import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class MonitoringGC implements Runnable {

    private static String pathToFile = "./logs/GC.log";
    private static File file = new File(pathToFile);
    private static int perMinute = 5000;

    private static String gcName;
    private static long gcPerMinute;
    private static long gcTimePerMinute;

    @Override
    public void run() {
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true){
            try{
                Thread.sleep(perMinute);
                collectStats();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void collectStats(){
        try (FileWriter writer = new FileWriter(file, true)) {
            for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {
                gcName = mBean.getName();
                gcPerMinute = mBean.getCollectionCount();
                gcTimePerMinute = mBean.getCollectionTime();

                System.out.println();
                System.out.println("GC name: " + gcName);
                System.out.println("GC per minute: " + gcPerMinute);
                System.out.println("GC time per minute: " + gcTimePerMinute);
                System.out.println();


                writer.write("GC name: " + gcName + "\n");
                writer.write("GC per minute: " + gcPerMinute + "\n");
                writer.write("GC time per minute: " + gcTimePerMinute + "\n");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}