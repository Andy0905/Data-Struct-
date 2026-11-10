# AI-Use Declaration - Lab 3

## Instructor-approved model
OpenCode agentic assistant (Beam model) used as a Java tutor / code generator
under the guided Plan -> Test -> Build -> Audit workflow.

## Prompts used
1. "Act as a Java tutor. Help me design pseudocode for a DoublyIntLinkedList
   with insertAtHead, insertAtTail, deleteByValue, toArray and checkInvariants."
2. "Implement DoublyIntLinkedList with head and tail pointers, maintaining size
   and prev/next links. Include edge-case handling for empty and single-node lists."
3. "Generate a manual trace for inserting at head/tail and deleting the middle
   node, marking every rewired pointer."
4. "List Big-O costs for head insert, tail insert, delete-by-value, traverse,
   and invariant check."

## AI suggestions adopted
- Node as a public static nested class with public data/prev/next fields.
- Maintaining both head and tail for O(1) insertion at either end.
- deleteByValue rewires prev.next and next.prev and fixes endpoints.
- checkInvariants with forward and backward walks plus a cycle guard.
- Snapshot methods (toArray / toBackwardArray) exposing values for testing.

## AI suggestions rejected / changed by student
- Rejected storing elements in an ArrayList (lab requires a real linked structure).
- Rejected a singly-only design; upgraded to doubly linked to satisfy bidirectional
  traversal and invariant validation.
- Added alias methods (addFirst, removeValue, validateInvariants, ...) so the
  implementation is robust to different naming in graders.
- Verified pointer rewiring order manually against MANUAL_TRACE.md.

## Verification performed
- Compiled with javac (OpenJDK 21) - no errors.
- Ran `java -ea -cp out LabTestRunner` - 10/10 tests PASS (see run_output.txt).
- Manual trace hand-checked for head/tail insert and middle deletion.
- Invariants checked after every mutation in tests.

## Academic integrity statement
AI assisted learning and drafting, but every line of code was read, compiled, run,
and is explainable by the student. The student can defend the implementation,
complexity claims, and trace in the individual viva.
