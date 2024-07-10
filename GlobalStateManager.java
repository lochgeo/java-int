import java.util.Random;

public class GlobalStateManager {
    private static GlobalStateManager instance;
    private int secretCode;
    private Random random;

    private GlobalStateManager() {
        random = new Random();
        secretCode = random.nextInt(1000);
    }

    public static GlobalStateManager access() {
        if (instance == null) {
            synchronized (GlobalStateManager.class) {
                if (instance == null) {
                    instance = new GlobalStateManager();
                }
            }
        }
        return instance;
    }

    public void updateState() {
        secretCode = random.nextInt(1000);
    }

    public int retrieveState() {
        return secretCode;
    }

    public static void main(String[] args) {
        // Test 1: Get instance and retrieve state
        GlobalStateManager manager1 = GlobalStateManager.access();
        System.out.println("Test 1: " + manager1.retrieveState());

        // Test 2: Update state
        manager1.updateState();
        System.out.println("Test 2: " + manager1.retrieveState());

        // Test 3: Get another instance and check if state is the same
        GlobalStateManager manager2 = GlobalStateManager.access();
        System.out.println("Test 3: " + (manager1.retrieveState() == manager2.retrieveState()));

        // Test 4: Update state through second instance and check first instance
        manager2.updateState();
        System.out.println("Test 4: " + manager1.retrieveState());

        // Test 5: Check if both references point to the same object
        System.out.println("Test 5: " + (manager1 == manager2));
    }
}