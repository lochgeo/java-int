import java.util.concurrent.atomic.AtomicInteger;

public class ResilienceManagerTest {

    public static class FlowController {
        private final int thresholdLimit;
        private final long cooldownPeriod;
        private AtomicInteger eventCounter;
        private long lastEventTimestamp;
        private Mode currentMode;

        public enum Mode {
            NORMAL, RESTRICTED, PROBATION
        }

        public FlowController(int thresholdLimit, long cooldownPeriod) {
            this.thresholdLimit = thresholdLimit;
            this.cooldownPeriod = cooldownPeriod;
            this.eventCounter = new AtomicInteger(0);
            this.currentMode = Mode.NORMAL;
        }

        public boolean isOperationAllowed() {
            if (currentMode == Mode.NORMAL) {
                return true;
            } else if (currentMode == Mode.RESTRICTED) {
                if (System.currentTimeMillis() - lastEventTimestamp >= cooldownPeriod) {
                    currentMode = Mode.PROBATION;
                    return true;
                }
                return false;
            } else { // PROBATION
                return true;
            }
        }

        public void logPositiveOutcome() {
            eventCounter.set(0);
            currentMode = Mode.NORMAL;
        }

        public void logNegativeOutcome() {
            eventCounter.incrementAndGet();
            lastEventTimestamp = System.currentTimeMillis();
            if (eventCounter.get() >= thresholdLimit) {
                currentMode = Mode.RESTRICTED;
            }
        }

        public Mode getCurrentMode() {
            return currentMode;
        }
    }

    public static void main(String[] args) {
        FlowController controller = new FlowController(3, 5000);

        System.out.println("Test 1: " + controller.isOperationAllowed());

        controller.logNegativeOutcome();
        controller.logNegativeOutcome();
        System.out.println("Test 2: " + controller.getCurrentMode());

        controller.logNegativeOutcome();
        System.out.println("Test 3: " + controller.getCurrentMode());

        System.out.println("Test 4: " + controller.isOperationAllowed());

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 5: " + controller.isOperationAllowed());
        System.out.println("Mode after Test 5: " + controller.getCurrentMode());

        controller.logPositiveOutcome();
        System.out.println("Test 6: " + controller.getCurrentMode());
    }
}