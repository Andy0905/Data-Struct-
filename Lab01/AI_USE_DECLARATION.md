# AI-Use Declaration - Lab 1

## Instructor-approved model
OpenCode agentic assistant (Beam model) used as a Java tutor / code generator
under the guided workflow described in the lab sheet.

## Prompts used
1. "Act as a Java tutor. Help me design pseudocode for Arrays and Complexity Basics.
   Do not write full code until I explain the algorithm back to you."
2. "Implement ScoreArrayManager with display(), updateScore(index, value),
   insertAt(index, value), deleteAt(index) using a fixed int[] and report
   operation cost (shifts). Include edge-case handling."
3. "Generate a manual trace for inserting 88 at index 1 into [72, 90, 84, 0, 0]
   marking every shifted value."
4. "List Big-O costs for access, update, insert, delete, display."

## AI suggestions adopted
- Fixed-capacity int[] with a separate `size` counter for active elements.
- Shift loops: right-shift for insert (from size down to index), left-shift for delete.
- Cost reporting as number of shifts plus Big-O class.
- Edge-case guards: invalid index and full-array rejection.

## AI suggestions rejected / changed by student
- Rejected AI's initial idea of using ArrayList (lab requires a raw int[]).
- Changed display() to print ALL backing slots (including zeros) so unused slots
  are visible during tracing.
- Added getScore() helper for the access test cases (not in original required list
  but needed for evidence).
- Verified and corrected the shift counts manually against the trace.

## Verification performed
- Compiled with javac (OpenJDK 21) - no errors.
- Ran Lab01Main; all 12 test cases PASS (see run_output.txt and TEST_EVIDENCE.md).
- Manual trace hand-checked: insert 88@1 into [72,90,84,0,0] -> [72,88,90,84,0], 2 shifts.
- Viva answer prepared: arr[index] is O(1) (direct address); insert@0 is O(n) (n shifts).

## Academic integrity statement
AI assisted learning and drafting, but every line of code was read, compiled, run,
and is explainable by the student. The student can defend the implementation,
complexity claims, and trace in the individual viva.
