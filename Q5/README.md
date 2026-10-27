# Q5 — Machine room scheduling

**Marks.** 25 · **Chapter.** 4 · **Difficulty.** hard

> Complete `Machine`, `Washer`, `Dryer`, `ScheduleException` in `java/` (with `MachineRoom`
> given) or `python/machine_room.py`. `MachineRoom.main` reads `data/jobs.txt` — leave it.
>
> `data/jobs.txt` is given input — do not edit it.

## The question

`Machine` is abstract and `Comparable` by id: `schedule(start, kg)` is final — it validates
the load (`0 < kg ≤ capacityKg`) and that the machine isn't busy, then books the cycle.
`cycleMinutes(kg)` is abstract: `Washer` = 30 + ceil(kg)·3, `Dryer` = 50 if `kg ≤ 5` else 70.
`MachineRoom` installs machines (refusing duplicate ids via `equals`), parses `HH:MM`, runs the
job file, and prints a summary sorted by id.

## TODO

| Where | What | Hint |
|:--|:--|:--|
| `Machine` constructor | store id + capacityKg | plain field assignment |
| `schedule` | reject bad kg / busy machine; book and return finish minute | `throw new ScheduleException(...)` |
| `cycleMinutes` | `Washer`: 30+ceil(kg)·3; `Dryer`: 50/70 | `Math.ceil(kg)` |
| `equals`/`hashCode`/`compareTo` | by id | `id.equals(...)` / `id.compareTo(...)` |
| Python | ABC; `__eq__`/`__hash__`/`__lt__` by id; `raise ScheduleError` | mirror the Java |

## Run

```bash
javac -d classes java/*.java && java -cp classes MachineRoom
python3 python/machine_room.py
```
