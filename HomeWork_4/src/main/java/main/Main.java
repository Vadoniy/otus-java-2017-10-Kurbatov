package main;

import gcTools.MonitoringGC;
import objectsTools.ObjectsCreator;

public class Main {

    private static final MonitoringGC mc = new MonitoringGC();
    private static final ObjectsCreator oc = new ObjectsCreator();

    public static void main(String[] args) {
        new Thread(oc).start();
        new Thread(mc).start();
    }
}