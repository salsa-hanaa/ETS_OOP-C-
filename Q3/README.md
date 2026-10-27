# Q3 — Price rounding: ceil to the half-kilogram

**Marks.** 20 · **Chapter.** 3 · **Difficulty.** medium

> Fix the rounding bug in `java/Pricing.java` and `python/pricing.py`.
> `badj/Pricing.java` / `badp/pricing.py` show the broken version — do not edit
> `badj/` / `badp/`. `PricingCheck.java` / `test_pricing.py` are given drivers.

## The question

A Wash & Fold bag is charged for at least 3 kg, rounded **up** to the next
0.5 kg (3.01 → 3.5, 3.5 → 3.5), then express +50%, members −10%. The buggy
version uses `Math.round(x * 2) / 2.0` which rounds to the *nearest* half
(3.51 → 3.5 instead of 4.0). Fix: use `Math.ceil(x * 2) / 2`.

## TODO

| Where | What | Hint |
|:--|:--|:--|
| `java/Pricing.java` | round up to 0.5 kg | `Math.ceil(Math.max(kg, MINIMUM_KG) * 2) / 2` — not `Math.round(...)` |
| `python/pricing.py` | round up to 0.5 kg | `math.ceil(max(kg, MINIMUM_KG) * 2) / 2` — not `round(...)` |

## Run

```bash
javac -d classes java/*.java && java -cp classes PricingCheck
python3 python/test_pricing.py
```

Expected: 10 passed, 0 failed (all boundary tests green).
