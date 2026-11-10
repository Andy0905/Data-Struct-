# Lab 3 - Linked Lists (Doubly Linked List)

Cor Jesu College - College of Engineering
Computer Engineering, CpE 211
Professor: Engr. Jamie Eduardo Rosal, MSCpE

## Contents
- `src/DoublyIntLinkedList.java` - doubly linked list of ints with head/tail pointers.
- `test/LabTestRunner.java` - acceptance runner (assertions, normal/boundary/invalid cases).
- `MANUAL_TRACE.md` - manual trace of pointer rewiring on insert/delete.
- `TEST_EVIDENCE.md` - completed test-evidence table.
- `ALGORITHM_FLOWCHART.md` - flowchart of insert/delete/traversal.
- `DATA_STRUCTURE_VISUALIZATION.md` - node/pointer visualization.
- `AGENT_LOG.md` - agent evidence log (plan before build).
- `REFLECTION.md` - technical reflection.
- `AI_USE_DECLARATION.md` - AI-use declaration.
- `run_output.txt` - console log of a successful run.

## Required operations implemented
- `insertAtHead(int value)` / `addFirst` / `insertAtTail(int value)` / `addLast`
- `deleteByValue(int value)` (+ aliases) returning boolean
- `toArray()` / `toForwardArray()` and `toBackwardArray()` (snapshots for testing)
- `traverseForward()` / `traverseBackward()`
- `checkInvariants()` (+ aliases) validating head, tail, next, prev links
- helpers: `size()`, `isEmpty()`, `contains()`, `indexOf()`, `clear()`

## How to compile and run
```sh
javac -d out src/*.java test/*.java
java -ea -cp out LabTestRunner
```
Or on Windows: `run-tests.bat`. (Java 17+ JDK; Java 21 used here.)

## Complexity summary
| Operation | Best | Average | Worst | Reason |
|---|---|---|---|---|
| insertAtHead | O(1) | O(1) | O(1) | rewires 2 pointers |
| insertAtTail | O(1) | O(1) | O(1) | tail pointer maintained |
| deleteByValue | O(1) (first node) | O(n) | O(n) | search dominates |
| toArray / traverse | O(n) | O(n) | O(n) | visits every node |
| checkInvariants | O(n) | O(n) | O(n) | inspects all links |

## Viva question
Why is deleting from the middle of a linked list faster than in an array?
- An array delete shifts all later elements left (O(n) data movement).
- A linked-list delete only rewires two pointers (prev.next, next.prev) in O(1)
  once the node is located; the search is what costs O(n).
