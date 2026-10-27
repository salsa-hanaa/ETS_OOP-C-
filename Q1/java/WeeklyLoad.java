import java.util.Locale;

public class WeeklyLoad {
    static double total(double[] a) {
        double sum = 0;
        for (double v : a) {
            sum += v;
        }
        return sum;
    }

    static double average(double[] a) {
        return total(a) / a.length;
    }

    /** Index of the largest element; the first one if several are equal. */
    static int indexOfMax(double[] a) {
        int best = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[best]) {
                best = i;
            }
        }
        return best;
    }

    static int countAbove(double[] a, double limit) {
        int count = 0;
        for (double v : a) {
            if (v > limit) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] kg = {42.5, 38.0, 51.5, 47.0, 60.5, 72.0, 35.5};

        double avg = average(kg);
        int busiest = indexOfMax(kg);
        System.out.printf(Locale.US, "total %.1f kg, average %.2f kg%n", total(kg), avg);
        System.out.println("busiest day: " + days[busiest] + " (" + kg[busiest] + " kg)");
        System.out.println("days above average: " + countAbove(kg, avg));
    }
}
