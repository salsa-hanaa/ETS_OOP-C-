import math
from abc import ABC, abstractmethod


class ScheduleError(Exception):
    """A job cannot be scheduled as asked."""


def clock(minutes):
    """Minutes after 08:00 as a clock time."""
    return f"{8 + minutes // 60:02d}:{minutes % 60:02d}"


class Machine(ABC):
    """A machine in the machine room. Two machines are equal when their ids are equal."""

    def __init__(self, machine_id, capacity_kg):
        self.machine_id = machine_id
        self.capacity_kg = capacity_kg
        self.busy_until = 0                    # minutes after opening time
        self.jobs = 0
        self.total_kg = 0.0

    @abstractmethod
    def cycle_minutes(self, kg):
        """How long one cycle takes for this load; every kind of machine decides for itself."""

    def schedule(self, start, kg):
        """Books the machine; returns the minute the job finishes."""
        if kg <= 0 or kg > self.capacity_kg:
            raise ScheduleError(f"{self.machine_id} cannot take {kg} kg")
        if start < self.busy_until:
            raise ScheduleError(f"{self.machine_id} busy until {clock(self.busy_until)}")
        self.busy_until = start + self.cycle_minutes(kg)
        self.jobs += 1
        self.total_kg += kg
        return self.busy_until

    def __eq__(self, other):
        if not isinstance(other, Machine):
            return False
        return self.machine_id == other.machine_id

    def __hash__(self):
        return hash(self.machine_id)

    def __lt__(self, other):
        return self.machine_id < other.machine_id

    def __str__(self):
        return (f"{self.machine_id} {type(self).__name__:<6} {self.jobs} job(s) "
                f"{self.total_kg:5.1f} kg, free at {clock(self.busy_until)}")


class Washer(Machine):
    def cycle_minutes(self, kg):
        return 30 + math.ceil(kg) * 3


class Dryer(Machine):
    def cycle_minutes(self, kg):
        return 50 if kg <= 5 else 70


class MachineRoom:
    def __init__(self):
        self.machines = []

    def install(self, m):
        """Installs a machine unless one with the same id is already there (uses ==)."""
        if m in self.machines:
            print(m.machine_id, "already installed")
            return
        self.machines.append(m)

    def find(self, machine_id):
        for m in self.machines:
            if m.machine_id == machine_id:
                return m
        raise ScheduleError(f"no machine {machine_id}")

    @staticmethod
    def minutes(text):
        """\"HH:MM\" as minutes after 08:00."""
        try:
            h, m = text.split(":")
            return (int(h) - 8) * 60 + int(m)
        except ValueError:
            raise ScheduleError(f"bad time {text}") from None

    def run(self, file_name):
        rejected = 0
        with open(file_name, encoding="utf-8") as f:
            next(f)                            # header
            for line in f:
                job, machine_id, start, kg = line.strip().split(",")
                try:
                    m = self.find(machine_id)
                    end = m.schedule(self.minutes(start), float(kg))
                    print(f"{job} on {machine_id} until {clock(end)}")
                except ScheduleError as e:
                    rejected += 1
                    print(f"{job} rejected: {e}")
        print(f"{rejected} job(s) rejected")
        for m in sorted(self.machines):        # by id, through __lt__
            print(m)


room = MachineRoom()
room.install(Washer("W2", 10))
room.install(Washer("W1", 8))
room.install(Dryer("D1", 12))
room.install(Washer("W1", 7))              # same id: refused
room.run("jobs.txt")
