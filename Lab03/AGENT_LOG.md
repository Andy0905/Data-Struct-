# Agent Evidence Log - Lab 3 (AGENT_LOG.md)

## Student plan (recorded before Build)
- Behavior: doubly linked list of ints with head/tail pointers, size counter,
  insert at head/tail O(1), delete first matching value returning boolean,
  forward/backward snapshots as int[], invariant validation.
- Edge cases: empty list, single node, head deletion, tail deletion, missing
  value, duplicates, repeated delete to empty.
- Pseudocode: see ALGORITHM_FLOWCHART.md.
- Predicted complexity: add head/tail O(1); delete O(1) best / O(n) worst;
  traverse and invariant check O(n); space O(n).

| # | Prompt / agent mode | What changed | Verification evidence |
|---|---|---|---|
| 1 | Plan: define expected behavior, edge cases, pseudocode, complexity | AGENT_LOG plan section filled | This file |
| 2 | Plan review (challenge assumptions) | Confirmed need for tail pointer and cycle guard | checkInvariants design |
| 3 | Test-first: review runner, add assertions | test/LabTestRunner.java created | 10 test cases |
| 4 | Build: implement src/DoublyIntLinkedList.java | All required methods + aliases | compile OK |
| 5 | Audit: compile & run with -ea | run_output.txt captured | 10/10 PASS |
| 6 | Audit: complexity check | timings recorded for n=100/1000/10000 | run_output.txt |

## Independently evaluated agent limitation
The agent initially suggested a singly linked list; I rejected it because the lab
requires bidirectional traversal and prev-link invariant validation. I also
verified the deletion pointer-rewiring order by hand (MANUAL_TRACE.md) rather
than trusting the generated code.
