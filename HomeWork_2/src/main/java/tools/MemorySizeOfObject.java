package tools;

import java.util.function.Supplier;

public class MemorySizeOfObject {

    private static int ammountOfObjets = 1_000_000;

    public static long getMemory(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long countMemory(Supplier<Object> supplier) throws InterruptedException {
        long beforeObject;
        long afterObject;
        System.gc();
        Thread.sleep(1000);
        beforeObject = getMemory();
        Object[] array = new Object[ammountOfObjets];

        for (int i = 0; i < ammountOfObjets; i++){
            array[i] = supplier.get();
        }

        afterObject = getMemory();
        System.gc();
        Thread.sleep(1000);
        return (afterObject - beforeObject)/ammountOfObjets;
    }
}
