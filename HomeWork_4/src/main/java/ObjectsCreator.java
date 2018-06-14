public class ObjectsCreator implements Runnable{

    private static int size = 7_000_000;

    private Object[] array = new Object[size];

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < size; i++) {
                array[i] = new String("Element " + i);
                if (i % 100 == 0) {
                    array[i] = null;
                }
            }
        }
    }
}
