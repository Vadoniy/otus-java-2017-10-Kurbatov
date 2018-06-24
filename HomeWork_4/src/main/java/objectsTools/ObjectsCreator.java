package objectsTools;

public class ObjectsCreator implements Runnable{

    private static final int SIZE = 3_500_000;

    private final Object[] array = new Object[SIZE];

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < SIZE; i++) {
                array[i] = new String("Element " + i);
                if (i % 100 == 0) {
                    array[i] = null;
                }
            }
        }
    }
}
