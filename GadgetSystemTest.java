interface Gadget {
    void activate();
}

class AlphaGadget implements Gadget {
    public void activate() {
        System.out.println("Alpha gadget activated");
    }
}

class BetaGadget implements Gadget {
    public void activate() {
        System.out.println("Beta gadget activated");
    }
}

class GammaGadget implements Gadget {
    public void activate() {
        System.out.println("Gamma gadget activated");
    }
}

class GadgetProducer {
    public Gadget assembleGadget(String code) {
        switch (code.toLowerCase()) {
            case "alpha":
                return new AlphaGadget();
            case "beta":
                return new BetaGadget();
            case "gamma":
                return new GammaGadget();
            default:
                throw new IllegalArgumentException("Invalid gadget code");
        }
    }
}

public class GadgetSystemTest {
    public static void main(String[] args) {
        GadgetProducer producer = new GadgetProducer();

        // Test 1: Assemble an alpha gadget
        Gadget alpha = producer.assembleGadget("alpha");
        alpha.activate();

        // Test 2: Assemble a beta gadget
        Gadget beta = producer.assembleGadget("beta");
        beta.activate();

        // Test 3: Assemble a gamma gadget
        Gadget gamma = producer.assembleGadget("gamma");
        gamma.activate();

        // Test 4: Try to assemble an unknown gadget
        try {
            Gadget unknown = producer.assembleGadget("delta");
            unknown.activate();
        } catch (IllegalArgumentException e) {
            System.out.println("Test 4: " + e.getMessage());
        }
    }
}