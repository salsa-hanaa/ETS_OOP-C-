class Machine {
    String name() {
        return "machine";
    }

    int minutes(double kg) {
        return 30;
    }

    String describe() {
        return name() + " " + minutes(5) + " min";   // both calls are dispatched dynamically
    }
}

class Washer extends Machine {
    @Override
    String name() {
        return "washer";
    }

    @Override
    int minutes(double kg) {
        return 40 + (int) kg * 2;
    }
}

class Dryer extends Machine {
    private int heat = 2;

    @Override
    String name() {
        return "dryer";
    }

    void setHeat(int heat) {                   // only dryers have this
        this.heat = heat;
    }

    @Override
    int minutes(double kg) {
        return 60 - heat * 5;
    }
}

public class Dispatch {
    static String kind(Machine m) {
        return "a machine";
    }

    static String kind(Dryer d) {
        return "a dryer";
    }

    public static void main(String[] args) {
        Machine[] room = {new Machine(), new Washer(), new Dryer()};
        for (Machine m : room) {
            System.out.println(m.describe() + " / " + kind(m));
        }

        Dryer d = new Dryer();
        Machine m = d;
        System.out.println(kind(d) + " / " + kind(m) + " / " + m.name());

        if (m instanceof Dryer) {
            ((Dryer) m).setHeat(4);
        }
        System.out.println(m.describe());

        Machine w = room[1];
        Dryer wrong = (Dryer) w;
    }
}
