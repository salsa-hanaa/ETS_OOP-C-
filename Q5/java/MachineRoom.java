import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MachineRoom {
    private final List<Machine> machines = new ArrayList<>();

    /** Installs a machine unless one with the same id is already there (uses equals). */
    void install(Machine m) {
        if (machines.contains(m)) {
            System.out.println(m.getId() + " already installed");
            return;
        }
        machines.add(m);
    }

    Machine find(String id) throws ScheduleException {
        for (Machine m : machines) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        throw new ScheduleException("no machine " + id);
    }

    /** "HH:MM" as minutes after 08:00. */
    static int minutes(String text) throws ScheduleException {
        String[] p = text.split(":");
        try {
            return (Integer.parseInt(p[0]) - 8) * 60 + Integer.parseInt(p[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ScheduleException("bad time " + text);
        }
    }

    void run(String fileName) throws IOException {
        int rejected = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            in.readLine();                     // header
            String line;
            while ((line = in.readLine()) != null) {
                String[] f = line.split(",");
                try {
                    Machine m = find(f[1]);
                    int end = m.schedule(minutes(f[2]), Double.parseDouble(f[3]));
                    System.out.println(f[0] + " on " + f[1] + " until " + Machine.clock(end));
                } catch (ScheduleException e) {
                    rejected++;
                    System.out.println(f[0] + " rejected: " + e.getMessage());
                }
            }
        }
        System.out.println(rejected + " job(s) rejected");
        Collections.sort(machines);            // by id, through compareTo
        for (Machine m : machines) {
            System.out.println(m);
        }
    }

    public static void main(String[] args) throws IOException {
        MachineRoom room = new MachineRoom();
        room.install(new Washer("W2", 10));
        room.install(new Washer("W1", 8));
        room.install(new Dryer("D1", 12));
        room.install(new Washer("W1", 7));     // same id: refused
        room.run("jobs.txt");
    }
}
