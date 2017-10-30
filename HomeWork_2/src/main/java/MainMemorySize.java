import tools.MemorySizeOfObject;

public class MainMemorySize {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Memory volume of one empty String: " + MemorySizeOfObject.countMemory(() -> new String()));
        System.out.println("Memory volume of one Object: " + MemorySizeOfObject.countMemory(() -> new Object()));
        System.out.println("Memory volume of empty String array: " + MemorySizeOfObject.countMemory(() -> new String[0]));
        System.out.println("Memory volume of empty Object array: " + MemorySizeOfObject.countMemory(() -> new Object[0]));
        System.out.println("Memory volume of String array of 100 elements: " + MemorySizeOfObject.countMemory(() -> new String[100]));
        System.out.println("Memory volume of Object array of 100 elements: " + MemorySizeOfObject.countMemory(() -> new Object[100]));
    }
}
