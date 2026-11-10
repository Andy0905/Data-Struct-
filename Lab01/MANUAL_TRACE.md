# Manual Trace - Lab 1: Arrays and Complexity Basics

## Scenario
Insert value **88** at index **1** into the fixed array `[72, 90, 84, 0, 0]`
(capacity = 5, active size = 3).

## Before
Index:   0    1    2    3    4
Value:  [72, 90, 84,  0,  0]
Active:  ^         ^ (size = 3, slots 3-4 unused)

## Operation: insertAt(1, 88)
Shifts required (size - index = 3 - 1 = 2):
- scores[3] = scores[2]  -> 84 shifts right (84 moved: index 2 -> 3)  [SHIFTED]
- scores[2] = scores[1]  -> 90 shifts right (90 moved: index 1 -> 2)  [SHIFTED]
- scores[1] = 88

## After
Index:   0    1    2    3    4
Value:  [72, 88, 90, 84,  0]
Active:  ^              ^ (size = 4, slot 4 unused)

## Marked shifts
- 90: index 1 -> 2  (shifted right by 1)
- 84: index 2 -> 3  (shifted right by 1)
- 72: unchanged (index 0)
- 88: newly inserted at index 1

## Cost
- Shifts = 2
- Time complexity = O(n) where n = active size (3). Worst case insert at index 0 -> O(n).
- Space complexity = O(1) extra (in-place).

## Access checks
- First valid index (0) = 72  (O(1))
- Last valid index (size-1 = 3) = 84  (O(1))

## Complexity reasoning (Viva)
- `arr[index]` is O(1) because the address is computed directly:
  address = base + index * elementSize (constant-time arithmetic).
- Inserting at index 0 is O(n) because every existing element must shift
  one position to the right to make room, costing n shifts.
