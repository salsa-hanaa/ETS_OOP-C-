public class PricingCheck {
    static int passed = 0;
    static int failed = 0;

    static void check(String name, long expected, long actual) {
        if (expected == actual) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL " + name + ": expected " + expected + ", got " + actual);
        }
    }

    static void checkRejected(String name, double kg) {
        try {
            Pricing.price(kg, false, false);
            failed++;
            System.out.println("FAIL " + name + ": no exception for " + kg);
        } catch (IllegalArgumentException e) {
            passed++;
        }
    }

    public static void main(String[] args) {
        check("below the minimum", 21000, Pricing.price(1.0, false, false));
        check("exactly the minimum", 21000, Pricing.price(3.0, false, false));
        check("just over the minimum", 24500, Pricing.price(3.01, false, false));
        check("exactly a half", 24500, Pricing.price(3.5, false, false));
        check("just over a half", 28000, Pricing.price(3.51, false, false));
        check("express", 36750, Pricing.price(3.5, true, false));
        check("member", 22050, Pricing.price(3.5, false, true));
        check("express member", 33075, Pricing.price(3.5, true, true));
        checkRejected("zero weight", 0.0);
        checkRejected("negative weight", -1.0);
        System.out.println(passed + " passed, " + failed + " failed");
    }
}
