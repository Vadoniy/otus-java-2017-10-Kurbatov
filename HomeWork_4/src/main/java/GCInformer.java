import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.MemoryUsage;
import java.util.Map;

public class GCInformer implements NotificationListener {

    private Map<String, MemoryUsage> beforeGC;
    private Map<String, MemoryUsage> afterGC;
    private static int minorGcAmmount;
    private static int majorGcAmmount;
    private static long durationOfGc;
    private String infoMessage = "\nAction of GC: %s\nCause of GC: %s\nName of GC: %s\nMemory before GC: %s\n" +
            "Memory after GC: %s\nAmmount of minor GC: %s\nAmmount of major GC: %s\nDuration of GC: %s\n";

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo gcInfo =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            beforeGC    =   gcInfo.getGcInfo().getMemoryUsageBeforeGc();
            afterGC     =   gcInfo.getGcInfo().getMemoryUsageAfterGc();
            durationOfGc += gcInfo.getGcInfo().getDuration();

            if (gcInfo.getGcAction().contains("minor")){
                minorGcAmmount++;
            } else if (gcInfo.getGcAction().contains("major")) {
                majorGcAmmount++;
            }

            System.out.println(String.format(infoMessage, gcInfo.getGcAction(), gcInfo.getGcCause(),
                    gcInfo.getGcName(), beforeGC/*.get("Par Eden Space").getUsed()*/,
                    afterGC/*.get("Par Eden Space").getUsed()*/, minorGcAmmount, majorGcAmmount, durationOfGc));
        }
    }
}
