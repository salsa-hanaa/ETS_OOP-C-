import math

RATE = 7000                            # Wash & Fold, rupiah per kilogram
MINIMUM_KG = 3.0


def price(kg, express, member):
    """Price of one Wash & Fold bag: minimum 3 kg, rounded up to 0.5 kg, express +50%, members -10%."""
    if kg <= 0:
        raise ValueError(f"weight must be positive: {kg}")
    charged = math.ceil(max(kg, MINIMUM_KG) * 2) / 2
    total = round(charged * RATE)
    if express:
        total = total * 3 // 2
    if member:
        total -= total // 10
    return total
