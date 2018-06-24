package gcTools;

import org.apache.log4j.Logger;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class MonitoringGC implements Runnable {

    private static final int PER_MINUTE = 60000;

    static Logger log = Logger.getLogger(MonitoringGC.class);

    private static String gcYoungName;
    private static String gcOldName;
    private static long gcPerMinuteYoung;
    private static long gcTimePerMinuteYoung;

    private static long gcPerMinuteOld;
    private static long gcTimePerMinuteOld;

    private GCInformer gcInformer = new GCInformer();
    private String gcType = "";

    @Override
    public void run() {
        log.info("_INFO_");

        while (true){
            try{
                collectStats();
                logInfo(gcYoungName, gcOldName, gcPerMinuteYoung, gcPerMinuteOld, gcTimePerMinuteYoung, gcTimePerMinuteOld);
                gcPerMinuteYoung = 0;
                gcPerMinuteOld = 0;
                gcTimePerMinuteYoung = 0;
                gcTimePerMinuteOld = 0;
                Thread.sleep(PER_MINUTE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void collectStats(){

        for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {

            NotificationEmitter emitter = (NotificationEmitter) mBean;

            emitter.addNotificationListener(gcInformer, null, null);

            gcType = gcInformer.getGCType();

            if(gcType.contains("minor")){
                gcYoungName = gcInformer.getGcName();
                gcPerMinuteYoung++;
                gcTimePerMinuteYoung += gcInformer.gcTime();
            } else if(gcType.contains("major")){
                gcOldName = gcInformer.getGcName();
                gcPerMinuteOld++;
                gcTimePerMinuteOld += gcInformer.gcTime();
            }
        }
    }

    private static void logInfo(String gcYoungName, String gcOldName, long gcYoungPerMinute, long gcOldPerMinute,
                                long gcYoungTimePerMinute, long gcOldTimePerMinute){
        log.info("Young GC name: " + gcYoungName);
        log.info("Young GC per minute: " + gcYoungPerMinute + " times.");
        log.info("Young GC times per minute: " + gcYoungTimePerMinute + " milliseconds.\n");

        log.info("Old GC name: " + gcOldName);
        log.info("Old GC per minute: " + gcOldPerMinute + " times.");
        log.info("Old GC times per minute: " + gcOldTimePerMinute + " milliseconds.\n");
    }
}