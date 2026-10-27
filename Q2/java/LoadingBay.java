public class LoadingBay {
    /** Tries each washer in turn; returns the id of the one that took the bag, or null. */
    static String place(Washer[] washers, double kg) {
        for (Washer w : washers) {
            try {
                w.load(kg);
                return w.getId();
            } catch (OverloadException e) {
                System.out.println("  " + e.getMessage() + ", " + e.getExcessKg() + " kg too much");
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Washer[] washers = {new Washer("W1", 7.0), new Washer("W2", 10.0), new Washer("W3", 15.0)};
        double[] bags = {6.5, 12.0, 18.0};
        for (double kg : bags) {
            String where = place(washers, kg);
            System.out.println(kg + " kg -> " + (where == null ? "split the bag" : where));
        }
        try {
            washers[0].load(-2);
        } catch (OverloadException e) {
            System.out.println("not reached");
        }
    }
}
