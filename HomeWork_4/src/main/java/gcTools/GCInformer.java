package gcTools;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

/**
 * Created by Vadoniy on 24.06.2018.
 */
public class GCInformer implements NotificationListener {

    private static String gcType = "";
    private static long duration;
    private static String gcName;

    @Override
    public void handleNotification(Notification notification, Object handback) {

        if(notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)){
            GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData)notification.getUserData());

            if (gcInfo.getGcAction().contains("minor")){
                gcType = "minor";
            } else if (gcInfo.getGcAction().contains("major")){
                gcType = "major";
            }

            duration = gcInfo.getGcInfo().getDuration();
            gcName = gcInfo.getGcName();
        }
    }

    public String getGCType(){
        return gcType;
    }

    public long gcTime(){
        return duration;
    }

    public String getGcName(){
        return gcName != null ? gcName : "";
    }
}
