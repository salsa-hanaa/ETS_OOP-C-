# Q2 — Custom checked exception: washer overload

**Marks.** 20 · **Chapter.** 5 · **Difficulty.** hard

> Complete `Washer`, `OverloadException`, `LoadingBay` in `java/` and
> `loading_bay.py` in `python/`. The `main` driver is given — leave it.

## The question

A custom checked exception `OverloadException` carries machine id, requested kg
and capacity kg. `Washer.load` throws `IllegalArgumentException` for non-positive
weights and `OverloadException` when the bag is too heavy. `LoadingBay.place`
tries each washer in turn, catching `OverloadException` and moving on.

## TODO

| Where | What | Hint |
|:--|:--|:--|
| `OverloadException` | checked exception with 3 fields | `extends Exception`, store id/requested/capacity, `getExcessKg()` |
| `Washer.load` | validate + throw | `kg <= 0` → `IllegalArgumentException`; `kg > capacity` → `OverloadException(id, kg, capacity)` |
| `LoadingBay.place` | try each washer, catch overload | `try { w.load(kg); return w.getId(); } catch (OverloadException e) { ... }` |

## Run

```bash
javac -d classes java/*.java && java -cp classes LoadingBay
python3 python/loading_bay.py
```
