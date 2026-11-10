# Test Evidence - Lab 3

| Test case | Input / action | Expected | Actual result |
|---|---|---|---|
| Empty list | new list | size 0, empty snapshots, invariants true | size 0, [] / [], true - PASS |
| Single node | insertAtHead(7) then deleteByValue(7) | size 1 then 0, endpoints null | size 1 then 0, endpoints null - PASS |
| Head deletion | deleteByValue(1) on [1,2,3] | [2,3], size 2, head.prev null | [2,3], size 2, head.prev null - PASS |
| Tail deletion | deleteByValue(3) on [1,2,3] | [1,2], size 2, tail.next null | [1,2], size 2, tail.next null - PASS |
| Missing value | deleteByValue(42) on [5,6] | false, list unchanged, size 2 | false, unchanged, size 2 - PASS |
| Duplicates | deleteByValue(5) on [5,5,5] | removes first only -> [5,5], size 2 | [5,5], size 2 - PASS |
| Bidirectional | [1,10,20] | forward [1,10,20], backward [20,10,1] | forward [1,10,20], backward [20,10,1] - PASS |
| Invariants after mutations | mixed insert/delete | true after every step | true after every step - PASS |
| Aliases | addFirst/addLast/remove*/check* | same behaviour | same behaviour - PASS |
| Measurement | n = 100, 1000, 10000 | O(1) head/tail, O(n) delete/inv/traverse | timings scale ~linearly - PASS |

## Edge cases covered
- Empty list: delete returns false, snapshots empty, invariants hold.
- Single node: head == tail, deleting it empties both endpoints.
- Head deletion and tail deletion update the correct endpoint.
- Missing value: no mutation, size unchanged.
- Duplicates: only the first matching node is removed.
- Repeated delete until empty keeps invariants true.
- Cycle guard in checkInvariants prevents infinite loops.

## Console evidence
See `run_output.txt` for the full successful run (10/10 tests, ALL TESTS PASSED).
