# Q4 — Your answer

## 1. Predicted output

```text
machine 30 min / a machine
washer 50 min / a machine
dryer 50 min / a machine
a dryer / a machine / dryer
dryer 40 min
Exception in thread "main" java.lang.ClassCastException: class Washer cannot be cast to class Dryer (Washer and Dryer are in unnamed module of loader 'app')
	at Dispatch.main(Dispatch.java:70)
```

Notes on the trickier lines:

- `new Machine()`, `new Washer()`, `new Dryer()` in the `room` loop: each
  `describe()` dynamically dispatches to its own `name()`/`minutes()`, but
  every element of the array is loaded through the overload
  `kind(Machine m)` because the compile-time type of the loop variable `m`
  is `Machine` — so all three print `a machine` even for the `Washer` and
  `Dryer` instances.
- `kind(d) + " / " + kind(m) + " / " + m.name()`: `d` is declared as
  `Dryer`, so `kind(d)` resolves to `kind(Dryer d)` → `a dryer`. `m` is
  declared as `Machine` (even though it holds the same `Dryer` object), so
  `kind(m)` resolves to `kind(Machine m)` → `a machine`. `m.name()` is an
  instance method call, which is always dispatched on the run-time type →
  `dryer`.
- After `((Dryer) m).setHeat(4)`, `m.describe()` calls the real `Dryer`
  object's `minutes(5)` → `60 - 4 * 5 = 40`, so `dryer 40 min`.
- `Machine w = room[1];` holds the `Washer` created earlier. `Dryer wrong =
  (Dryer) w;` is a cast that only the compiler trusts — at run time the JVM
  checks the actual object's type and finds it is a `Washer`, not a
  `Dryer`, so it throws `ClassCastException` and the program terminates
  before `wrong` is ever used.

## 2. Static overloading vs dynamic dispatch

Method **overloading** (`kind(Machine)` vs `kind(Dryer)`) is resolved by
the **compiler**, at compile time, purely from the *declared* (static)
type of the argument expression — it picks one of the two `kind` methods
and bakes that choice into the bytecode before the program ever runs.  It
never looks at what object is actually sitting in the variable at run
time, which is why `kind(m)` prints `a machine` even when `m` refers to a
`Dryer`.

Method **overriding** (`name()`, `minutes()`) is resolved by the **JVM**,
at run time, using the actual (dynamic) type of the object the reference
points to. Every instance method call in Java (other than `private`,
`static`, or `final`/constructor calls) goes through this dynamic
dispatch, which is exactly why `m.name()` and `m.minutes()` always report
the real subclass's behaviour regardless of the type of the variable used
to reach them. In short: overloading is a *compile-time, type-based*
choice between separate methods; overriding is a *run-time, object-based*
choice of which version of the *same* method to run.

## 3. The bad cast

In Java, `Dryer wrong = (Dryer) w;` compiles fine because the compiler
only checks that `Dryer` and the declared type of `w` (`Machine`) are
related by inheritance. At run time the JVM inserts a type check: it looks
at `w`'s actual object (a `Washer`), sees it is not a `Dryer` or a
subclass of `Dryer`, and throws a `ClassCastException` instead of letting
`wrong` be assigned. The program then terminates (or, if caught, control
passes to the `catch` block) without `wrong` ever holding a value.

Python has no compile-time type checking and no cast operator at all, so
there is no equivalent failure point when a variable is merely used —
`w.set_heat(4)` is just an ordinary attribute/method lookup on whatever
object `w` happens to reference. Since `w` is actually a `Washer`, and
`Washer` never defines `set_heat`, Python raises an `AttributeError`
("'Washer' object has no attribute 'set_heat'") at the point the method is
*called*, not at any assignment. The difference is: Java's cast fails
immediately and explicitly (and would fail even if `setHeat` were never
called), whereas Python's failure is delayed until the missing attribute
is actually accessed, and only because this particular object lacks it —
Python would happily accept a `Dryer` assigned to `w` with no complaint at
all.
