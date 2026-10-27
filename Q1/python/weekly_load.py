def total(a):
    s = 0.0
    for v in a:
        s += v
    return s


def average(a):
    return total(a) / len(a)


def index_of_max(a):
    """Index of the largest element; the first one if several are equal."""
    best = 0
    for i in range(1, len(a)):
        if a[i] > a[best]:
            best = i
    return best


def count_above(a, limit):
    count = 0
    for v in a:
        if v > limit:
            count += 1
    return count


days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
kg = [42.5, 38.0, 51.5, 47.0, 60.5, 72.0, 35.5]

avg = average(kg)
busiest = index_of_max(kg)
print(f"total {total(kg):.1f} kg, average {avg:.2f} kg")
print(f"busiest day: {days[busiest]} ({kg[busiest]} kg)")
print("days above average:", count_above(kg, avg))
