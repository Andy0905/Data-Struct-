# Complexity Analysis

## Complexity table
| Algorithm | Best | Average | Worst | Stable | In-place | Aux space |
|---|---|---|---|---|---|---|
| Bubble Sort (optimised) | O(n) | O(n^2) | O(n^2) | Yes | Yes | O(1) |
| Selection Sort | O(n^2) | O(n^2) | O(n^2) | No | Yes | O(1) |
| Insertion Sort | O(n) | O(n^2) | O(n^2) | Yes | Yes | O(1) |

## Measured evidence (n = 100)
See runner output. Bubble/insertion on sorted input: 99 comparisons (O(n)).
Reverse input: 4950 comparisons (O(n^2)). Selection: 4950 comparisons in all cases.

## Challenge question
Comparison-based sorts must distinguish among n! possible permutations. Each comparison
yields at most one bit of information, so any decision tree must have depth at least
log2(n!) = Omega(n log n). Hence no comparison-based sort can be faster than O(n log n)
in the worst case. Merge sort and quick sort reach this bound via divide-and-conquer.
Non-comparison sorts (counting, radix, bucket) can be O(n) when key assumptions hold.