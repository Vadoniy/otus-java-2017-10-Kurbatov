import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class MonitoringGC {

    private static GCInformer gcInformer = new GCInformer();

    public static void startGCMonitor() {
        for(GarbageCollectorMXBean mBean: ManagementFactory.getGarbageCollectorMXBeans()) {
            ((NotificationEmitter) mBean).addNotificationListener(gcInformer, null, null);
        }
    }

    public static void stopGCMonitor() {
        for(GarbageCollectorMXBean mBean: ManagementFactory.getGarbageCollectorMXBeans()) {
            try {
                ((NotificationEmitter) mBean).removeNotificationListener(gcInformer);
            } catch(ListenerNotFoundException e) {
            }
        }
    }
}
