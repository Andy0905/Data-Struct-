# REFLECTION.md — Lab 4

1. Which invariant or edge case caused the most difficult failure? Cite the failing test.
   - Stable ties: ensuring insertion sort uses strict `>` so equal grades keep input order. Test: testStableTies.

2. How did measured operations compare with prediction?
   - Bubble on sorted input: 4 comparisons, 0 swaps (O(n)). Reverse: ~10 comparisons. Matches prediction.

3. Which agent recommendation was verified independently, and what evidence determined the decision?
   - Early-exit optimization in bubble sort; verified by instrumentation test (sorted swaps == 0, comparisons == n-1).

4. What implementation choice would change for a much larger dataset?
   - Use O(n log n) algorithm (merge/quick) instead of O(n²) basic sorts; avoid O(n) extra? Use iterative quicksort.

5. Identify one limitation in the submitted solution.
   - Counters are static/global, not thread-safe; only last run retained. Object sorting by name uses String.compareTo (case-sensitive).
