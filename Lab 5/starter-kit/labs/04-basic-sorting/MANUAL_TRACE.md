# Manual Tracing - Input [7, 3, 9, 2, 5]

## Bubble Sort (ascending)
Pass 1:
[7,3,9,2,5] -> compare 7,3 swap -> [3,7,9,2,5]
-> compare 7,9 ok
-> compare 9,2 swap -> [3,7,2,9,5]
-> compare 9,5 swap -> [3,7,2,5,9]   (3 swaps, 4 comparisons)

Pass 2:
[3,7,2,5,9] -> 3,7 ok -> 7,2 swap -> [3,2,7,5,9] -> 7,5 swap -> [3,2,5,7,9]   (2 swaps, 3 comparisons)

Pass 3:
[3,2,5,7,9] -> 3,2 swap -> [2,3,5,7,9] -> 3,5 ok   (1 swap, 2 comparisons)

Pass 4:
[2,3,5,7,9] -> 2,3 ok   (0 swaps, 1 comparison) -> stop early

Total comparisons: 10, swaps: 6. Result: [2,3,5,7,9]

## Selection Sort (ascending)
i=0: min=2 (index 3); swap index0<->3 -> [2,3,9,7,5]   (4 comparisons, 1 swap)
i=1: min=3 (index 1); no swap                      (3 comparisons)
i=2: min=5 (index 4); swap index2<->4 -> [2,3,5,7,9]   (2 comparisons, 1 swap)
i=3: min=7 (index 3); no swap                      (1 comparison)

Total comparisons: 10, swaps: 2. Result: [2,3,5,7,9]

## Insertion Sort (ascending)
i=1, key=3: shift 7 -> [3,7,9,2,5]   (1 comparison, 2 writes)
i=2, key=9: no shift -> [3,7,9,2,5]   (1 comparison, 1 write)
i=3, key=2: shift 9,7,3 -> [2,3,7,9,5]   (3 comparisons, 4 writes)
i=4, key=5: shift 9,7 -> [2,3,5,7,9]   (3 comparisons, 3 writes)

Total comparisons: 8, writes: 10, swaps: 0. Result: [2,3,5,7,9]