/** A checked exception: a machine was asked to take more than it can hold. */
public class OverloadException extends Exception {
    private final String machineId;
    private final double requestedKg;
    private final double capacityKg;

    public OverloadException(String machineId, double requestedKg, double capacityKg) {
        super(machineId + " cannot take " + requestedKg + " kg (capacity " + capacityKg + " kg)");
        this.machineId = machineId;
        this.requestedKg = requestedKg;
        this.capacityKg = capacityKg;
    }

    public String getMachineId() {
        return machineId;
    }

    public double getExcessKg() {
        return requestedKg - capacityKg;
    }
}
