# REFLECTION.md - Lab 4: Basic Sorting

1. Which invariant or edge case caused the most difficult failure? Cite the failing test.
   Stability of equal keys. The first version used selection sort for student grades, which
   reordered equal-grade students. The failing assertion was `stable ties: equal grades keep
   input order` (Anna must precede Cara). Switching to insertion sort with a strict `>`
   comparison fixed it.

2. How did measured operations compare with your complexity prediction?
   Bubble on sorted input used 99 comparisons for n=100 (O(n)); on reverse input 4950
   (O(n^2)). Insertion on sorted input used 99 comparisons; on reverse 4950. Selection
   always used 4950 comparisons. These match the predicted best/average/worst cases.

3. Which agent recommendation did you verify independently, and what evidence determined your decision?
   The recommendation to use selection sort for student grades. I traced a two-equal-key input
   and observed the order change, then confirmed with `testStableTies`. Rejected in favour of
   insertion sort.

4. What implementation choice would you change for a much larger dataset?
   Replace all three with a divide-and-conquer sort (merge sort or quick sort) for O(n log n)
   expected time, or use `Arrays.sort` (dual-pivot quick sort / TimSort) in production.
   The current in-place O(n^2) algorithms are only suitable for small or nearly sorted data.

5. Identify one limitation that remains in your submitted solution.
   Metrics count writes at the level of array-slot assignments, which is implementation
   specific; different but equivalent loop structures could report different write counts.
   Also no timing-based benchmark is included, only operation counts.