/*
-agentlib:jdwp=transport=dt_socket,address=14000,server=y,suspend=n
-Xms512m
-Xmx512m
-XX:NewRatio=3
-XX:MaxMetaspaceSize=256m
-XX:+UseConcMarkSweepGC
-XX:+CMSParallelRemarkEnabled
-XX:+UseCMSInitiatingOccupancyOnly
-XX:CMSInitiatingOccupancyFraction=70
-XX:+ScavengeBeforeFullGC
-XX:+CMSScavengeBeforeRemark
-XX:+UseParNewGC
-verbose:gc
-Xloggc:C:/Users/vadon/IdeaProjects/OTUS/otus-java-2017-10-Kurbatov/HomeWork_4/logs/gc_MarkSweepGC.log
-XX:+PrintGC
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=10
-XX:GCLogFileSize=1M
-Dcom.sun.management.jmxremote.port=15000
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./dumps/

        jps -- list vms
        jstack <pid> >> threaddumps.log -- get dump from pid
        jinfo -- list VM parameters
        jhat / jvisualvm -- analyze heap dump*/

public class Main {

    private static int size = 7_500_000;

    public static void main(String[] args) {
        Object[] array = new Object[size];
//        int i = 0;
        try {
            while (true) {
                MonitoringGC.startGCMonitor();
                for (int i = 0; i < size; i++) {
                    array[i] = new String("Element " + i);
                    if (i % 100 == 0) {
                        array[i] = null;
                        Thread.sleep(1);
                    }
                }

                System.out.println("Array with " + array.length + " elements was created.");
                MonitoringGC.stopGCMonitor();
            }
        } catch (IndexOutOfBoundsException e){
            e.getStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}