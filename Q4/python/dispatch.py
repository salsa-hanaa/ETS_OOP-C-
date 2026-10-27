class Machine:
    def name(self):
        return "machine"

    def minutes(self, kg):
        return 30

    def describe(self):
        return f"{self.name()} {self.minutes(5)} min"   # both calls are dispatched dynamically


class Washer(Machine):
    def name(self):
        return "washer"

    def minutes(self, kg):
        return 40 + int(kg) * 2


class Dryer(Machine):
    def __init__(self):
        self.heat = 2

    def name(self):
        return "dryer"

    def set_heat(self, heat):          # only dryers have this
        self.heat = heat

    def minutes(self, kg):
        return 60 - self.heat * 5


def kind(m):
    # Python has no overloading: one function, which may look at the run-time type.
    return "a dryer" if isinstance(m, Dryer) else "a machine"


room = [Machine(), Washer(), Dryer()]
for m in room:
    print(m.describe(), "/", kind(m))

d = Dryer()
m = d
print(kind(d), "/", kind(m), "/", m.name())

if isinstance(m, Dryer):
    m.set_heat(4)
print(m.describe())

w = room[1]
w.set_heat(4)                          # no compiler to stop this line
