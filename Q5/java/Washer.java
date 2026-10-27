public class Washer extends Machine {
    public Washer(String id, double capacityKg) {
        super(id, capacityKg);
    }

    @Override
    protected int cycleMinutes(double kg) {
        return 30 + (int) Math.ceil(kg) * 3;
    }
}
