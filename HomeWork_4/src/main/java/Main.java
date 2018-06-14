public class Main {

    static MonitoringGC mc = new MonitoringGC();
    static ObjectsCreator oc = new ObjectsCreator();

    public static void main(String[] args) {
        new Thread(oc).start();
        new Thread(mc).start();
    }
}