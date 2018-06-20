package gcTools;

import org.apache.log4j.Logger;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class MonitoringGC implements Runnable {

    private static final int PER_MINUTE = 60000;

    static Logger log = Logger.getLogger(MonitoringGC.class);

    private static String gcName;
    private static long gcPerMinuteYoung;
    private static long gcTimePerMinuteYoung;

    private static long gcPerMinuteOld;
    private static long gcTimePerMinuteOld;

    private static long gcPreviousMinuteYoung;
    private static long gcTimePreviousMinuteYoung;

    private static long gcPreviousMinuteOld;
    private static long gcTimePreviousMinuteOld;

    @Override
    public void run() {
        log.info("_INFO_");

        while (true){
            try{
                Thread.sleep(PER_MINUTE);
                collectStats();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void collectStats(){
        for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {

            gcName = mBean.getName();

            if(gcName.contains("Young")){
                gcPerMinuteYoung = mBean.getCollectionCount() - gcPreviousMinuteYoung;
                gcPreviousMinuteYoung = gcPerMinuteYoung;
                gcTimePerMinuteYoung = mBean.getCollectionTime() - gcTimePreviousMinuteYoung;
                gcTimePreviousMinuteYoung = gcTimePerMinuteYoung;

                logInfo(gcName, gcPerMinuteYoung, gcTimePerMinuteYoung);

                gcPerMinuteYoung = 0;
                gcTimePerMinuteYoung = 0;
            } else if(gcName.contains("Old")){
                gcPerMinuteOld = mBean.getCollectionCount() - gcPreviousMinuteOld;
                gcPreviousMinuteOld = gcPerMinuteOld;
                gcTimePerMinuteOld = mBean.getCollectionTime() - gcTimePreviousMinuteOld;
                gcTimePreviousMinuteOld = gcTimePerMinuteOld;

                logInfo(gcName, gcPerMinuteOld, gcTimePerMinuteOld);

                gcPerMinuteOld = 0;
                gcTimePerMinuteOld = 0;
            }
        }
    }

    private static void logInfo(String gcName, long gcPerMinute, long gcTimePerMinute){
        log.info("GC name: " + gcName);
        log.info("GC per minute: " + gcPerMinute + " times.");
        log.info("GC time per minute: " + gcTimePerMinute + " milliseconds.\n");
    }
}