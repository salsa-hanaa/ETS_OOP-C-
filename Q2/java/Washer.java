public class Washer {
    private final String id;
    private final double capacityKg;

    public Washer(String id, double capacityKg) {
        this.id = id;
        this.capacityKg = capacityKg;
    }

    public String getId() {
        return id;
    }

    /** Loads a bag; throws OverloadException if it is heavier than the capacity. */
    public void load(double kg) throws OverloadException {
        if (kg <= 0) {
            throw new IllegalArgumentException("weight must be positive: " + kg);
        }
        if (kg > capacityKg) {
            throw new OverloadException(id, kg, capacityKg);
        }
        System.out.println(id + " loaded with " + kg + " kg");
    }
}
