# Q1 — Weekly load statistics

**Marks.** 15 · **Chapter.** 3 · **Difficulty.** medium

> Write array helper functions in `java/WeeklyLoad.java` and `python/weekly_load.py`.
> `alt/` shows library shortcuts (streams / built-ins) — do not edit `alt/`.

## The question

For a week of laundry loads (kg per day), compute: total, average, index of the
busiest day (first max if tied), and days above average. Write four helper
functions and call them from `main`.

## TODO

| Where | What | Hint |
|:--|:--|:--|
| `total` / `total` | sum of array | loop and accumulate |
| `average` / `average` | total / length | call total, divide by length |
| `indexOfMax` / `index_of_max` | first index of max | start best=0, update when strictly greater |
| `countAbove` / `count_above` | count > limit | loop and increment |

## Run

```bash
javac -d classes java/*.java && java -cp classes WeeklyLoad
python3 python/weekly_load.py
```

Expected:
```
total 347.0 kg, average 49.57 kg
busiest day: Sat (72.0 kg)
days above average: 3
```
