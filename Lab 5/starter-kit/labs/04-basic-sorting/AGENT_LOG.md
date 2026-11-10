# AGENT_LOG.md - Lab 4: Basic Sorting

## Student plan (recorded before Build)

### Expected behaviour
- `bubbleSort`, `selectionSort`, `insertionSort` sort an `int[]` in ascending order in place.
- Each returns a `Metrics` object counting comparisons, array writes, and swaps.
- Empty and single-element arrays are unchanged and yield zero comparisons.
- `insertionSortStudentsByGrade` is stable: equal grades retain input order.
- Null input throws `IllegalArgumentException`.

### Edge cases considered
- Empty array, single element, already sorted, reverse sorted, all duplicates, ties on student grades, null input.

### Pseudocode
```
bubbleSort(arr):
  for i in 0..n-2:
    swapped = false
    for j in 0..n-2-i:
      compare arr[j], arr[j+1]; if arr[j] > arr[j+1]: swap; swapped=true
    if not swapped: break

selectionSort(arr):
  for i in 0..n-2:
    minIndex = i
    for j in i+1..n-1:
      if arr[j] < arr[minIndex]: minIndex = j
    if minIndex != i: swap(arr[i], arr[minIndex])

insertionSort(arr):
  for i in 1..n-1:
    key = arr[i]; j = i-1
    while j >= 0 and arr[j] > key:
      arr[j+1] = arr[j]; j--
    arr[j+1] = key
```

### Predicted complexity
| Algorithm | Best | Average | Worst | Aux space |
|---|---|---|---|---|
| Bubble (optimised) | O(n) | O(n^2) | O(n^2) | O(1) |
| Selection | O(n^2) | O(n^2) | O(n^2) | O(1) |
| Insertion | O(n) | O(n^2) | O(n^2) | O(1) |

## Agent sessions

| # | Prompt / mode | What changed | Verification evidence |
|---|---|---|---|
| 1 | Plan mode: record behaviour, edge cases, pseudocode, complexity | Created plan above in AGENT_LOG.md | Plan section committed before any src edit |
| 2 | Plan agent: challenge assumptions | Accepted adding null guard and empty/single cases; rejected suggestion to use selection sort for stable ties | Notes in test plan |
| 3 | Test-first: added stable-ties assertion | Added `testStableTies` expecting Anna before Cara for equal grade 80 | Initially failed because method absent; recorded failure |
| 4 | Build mode: implement src/BasicSorts.java | Added all required methods plus Metrics and Student | Diff reviewed line by line |
| 5 | Verification: compile and run | `javac` + `java -ea` | 34 tests passed, 0 failed |
| 6 | Audit: @test-reviewer, @complexity-auditor | Accepted adding null tests; rejected counting swaps instead of writes (writes better justify best/worst) | Recorded below |

## Agent recommendations

### Accepted
- Add explicit null-input tests and `IllegalArgumentException`. Reason: invalid-operation coverage required by rubric.
- Count array writes, not only swaps, to explain insertion sort's sensitivity to input order.

### Rejected
- Use selection sort for the stable-ties case. Reason: selection sort is not stable; would place Cara before Anna, failing `testStableTies`. Used insertion sort instead.
- Replace manual loops with `Arrays.sort`. Reason: violates "no library sort methods".

## Independently evaluated agent limitation
The agent initially proposed selection sort for sorting students by grade, which would have broken the stability requirement. I verified this by tracing equal-key input and rejected the suggestion; the stable-ties test confirms insertion sort preserves order.