# Test Evidence - Lab 1

| Test case | Input / action | Expected | Actual result |
|---|---|---|---|
| Manual trace insert | insertAt(1,88) on [72,90,84,0,0] | [72,88,90,84,0], shifts=2 | [72,88,90,84,0], shifts=2 - PASS |
| Access first index | getScore(0) | 72 | 72 - PASS |
| Access last index | getScore(size-1) | 84 | 84 - PASS |
| Insert at index 0 | insertAt(0,5) on [10,20,30,0,0] | [5,10,20,30,0], shifts=3 | [5,10,20,30,0], shifts=3 - PASS |
| Insert in middle | insertAt(2,15) | [...,15,...], shifts=2 | [5,10,15,20,30], shifts=2 - PASS |
| Update score | updateScore(2,99) | slot2 becomes 99, O(1) | 15 -> 99 - PASS |
| Delete final active | deleteAt(size-1) | size decreases, slot cleared to 0 | removed 30, [5,10,99,20,0] - PASS |
| Reject invalid index (insert) | insertAt(10,7) | rejected, no change | REJECTED - invalid index - PASS |
| Reject invalid index (delete) | deleteAt(-1) | rejected, no change | REJECTED - invalid index - PASS |
| Reject invalid index (update) | updateScore(100,1) | rejected, no change | REJECTED - invalid index - PASS |
| Reject invalid index (access) | getScore(50) | rejected | REJECTED - invalid index - PASS |
| Reject full array | insertAt(1,9) when full (cap=3) | rejected, no change | REJECTED - array is full - PASS |

## Edge cases covered
- Insert at index 0 (worst case, O(n) shifts).
- Insert at end (index == size, 0 shifts).
- Delete first element.
- Delete last element (0 shifts).
- Full array rejection.
- Invalid (negative / out-of-range) index rejection for all operations.
- Empty logical size (no valid indexes) handled by validIndex().

## Console evidence
See `run_output.txt` for the full successful run log.
