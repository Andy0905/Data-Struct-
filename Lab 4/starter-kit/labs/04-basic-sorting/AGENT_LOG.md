# AGENT_LOG.md — Lab 4: Basic Sorting

## Student plan (before Build)

### Expected behavior
- `bubbleSort`, `selectionSort`, `insertionSort` on `int[]` sort ascending, in place.
- Student arrays sortable by grade and by name; insertion sort preserves input order for equal grades.
- Counters: comparisons, swaps, writes reset per run and reflect measured operations.

### Edge cases
- Empty array, single element.
- Already sorted input (best cases).
- Reverse sorted input (worst cases).
- Duplicates / stable ties.

### Pseudocode
```
bubbleSort(array):
  for i = 0 to n-2:
    swapped = false
    for j = 0 to n-2-i:
      compare array[j], array[j+1]
      if array[j] > array[j+1]: swap; swapped = true
    if not swapped: break

selectionSort(array):
  for i = 0 to n-2:
    minIndex = i
    for j = i+1 to n-1:
      if array[j] < array[minIndex]: minIndex = j
    if minIndex != i: swap(array[i], array[minIndex])

insertionSort(array):
  for i = 1 to n-1:
    key = array[i]; j = i-1
    while j >= 0 and array[j] > key:
      array[j+1] = array[j]; j--
    array[j+1] = key
```

### Predicted complexity
| Algorithm | Best | Average | Worst | Stable |
|---|---|---|---|---|
| Bubble | O(n) | O(n²) | O(n²) | Yes |
| Selection | O(n²) | O(n²) | O(n²) | No |
| Insertion | O(n) | O(n²) | O(n²) | Yes |

### Agent checkpoint notes
- Plan reviewed before Build; src/ edited only after plan recorded.
- Test-first: added student-authored assertion in LabTestRunner (single element).

## Manual trace — [7, 3, 9, 2, 5]

### Bubble sort (ascending)
Pass 1:
- compare(7,3) swap -> [3,7,9,2,5]
- compare(7,9) ok -> [3,7,9,2,5]
- compare(9,2) swap -> [3,7,2,9,5]
- compare(9,5) swap -> [3,7,2,5,9]
Pass 2:
- compare(3,7) ok
- compare(7,2) swap -> [3,2,7,5,9]
- compare(7,5) swap -> [3,2,5,7,9]
Pass 3:
- compare(3,2) swap -> [2,3,5,7,9]
- compare(3,5) ok
Pass 4: no swaps -> done
Total comparisons = 4+3+2+1 = 10.

### Selection sort
- i=0: min=2 (index3); swap index0 -> [2,3,9,7,5] (4 comparisons)
- i=1: min=3 already (3 comp)
- i=2: min=5 (index4); swap -> [2,3,5,7,9] (2 comp)
- i=3: min=7 (1 comp)
Total comparisons = 4+3+2+1 = 10; swaps = 2.

### Insertion sort
- i=1 key=3: shift 7 -> [3,7,9,2,5] (1 comp, 1 shift)
- i=2 key=9: no shift (1 comp)
- i=3 key=2: shift 9,7,3 -> [2,3,7,9,5] (3 comp, 3 shifts)
- i=4 key=5: shift 9,7 -> [2,3,5,7,9] (2 comp, 2 shifts)
Total comparisons = 1+1+3+2 = 7; shifts = 6.

## Challenge question
Comparison-based sorts are limited to O(n log n) in the worst case because each comparison yields one bit of information; distinguishing among n! permutations requires log2(n!) ~ n log n comparisons (decision-tree lower bound). Linear-time sorts (counting/radix) avoid comparisons by exploiting key structure.

## Agent evidence log
| # | Prompt / mode | What changed | Verification |
|---|---|---|---|
| 1 | Plan | Recorded behavior, edge cases, pseudocode, complexity | AGENT_LOG.md |
| 2 | Test | Added LabTestRunner assertions | compile + run |
| 3 | Build | Implemented src/BasicSorts.java, Student.java | tests pass |
| 4 | Audit | Reviewed recommendations | accepted in-place O(1) space |

## One independently evaluated agent limitation
The agent may suggest unstable selection sort for equal keys; verified by stable-ties test and chose insertion sort for stable requirement.
