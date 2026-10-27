# Q4 — Dynamic dispatch, static overloading, casts

**Marks.** 20 · **Chapter.** 4 · **Difficulty.** hard

> `java/Dispatch.java` and `python/dispatch.py` are **given** — do not modify them.
> Predict the output in `ANSWER.md` (you may run it afterward to check).
>
> `bad/StaticType.java` shows a compile error: a `Machine` reference cannot call
> `setHeat` — do not edit `bad/`.

## The question

Overridden methods (`name`, `minutes`) dispatch on the run-time type; overloaded
`static kind(Machine)` / `kind(Dryer)` is chosen by the compile-time type.
`instanceof` narrows, a bad cast `(Dryer) washer` throws.

## TODO (in `ANSWER.md`)

1. Exact output of `Dispatch.java` / `dispatch.py`, line by line.
2. Why `kind(m)` says "a machine" even when `m` holds a `Dryer`.
3. Why the last line `Dryer wrong = (Dryer) w;` throws — and what Python does instead.

## Run (to check, after predicting)

```bash
javac -d classes java/*.java && java -cp classes Dispatch
python3 python/dispatch.py   # the last line will raise AttributeError — that's the point
```
