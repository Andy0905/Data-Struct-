# AI-Use Declaration - Lab 2

## Instructor-approved model
OpenCode agentic assistant (Beam model) used as a Java tutor / code generator
under the guided workflow described in the lab sheet.

## Prompts used
1. "Act as a Java tutor. Help me design pseudocode for Linear Search in Real
   Applications. Do not write full code until I explain the algorithm back to you."
2. "Implement LibraryFinder with findFirst(String[] items, String target) and
   countMatches(String[] items, String target) using linear search; print every
   inspected index. Include edge-case handling and int overloads."
3. "Generate a manual trace for searching 'Java' and 'Python' in
   [C, Java, DSA, Java, SQL], counting every comparison."
4. "List best/worst/average time complexity for findFirst and countMatches."

## AI suggestions adopted
- for-loop from index 0 to length-1, comparing with String.equals().
- findFirst returns on first match; countMatches scans all elements.
- Printing every inspected index for traceability.
- Null/empty-array guards and case-sensitive comparison note.

## AI suggestions rejected / changed by student
- Rejected using Arrays.asList().contains() (hides the linear scan).
- Changed output to print each inspected index on its own line.
- Added int[] overloads to cover the "numbers" learning outcome.
- Verified comparison counts manually against MANUAL_TRACE.md.

## Verification performed
- Compiled with javac (OpenJDK 21) - no errors.
- Ran Lab02Main; all test cases PASS (see run_output.txt and TEST_EVIDENCE.md).
- Manual trace hand-checked: 'Java' first index 1 (2 comparisons); count 2;
  'Python' not found (5 comparisons).
- Viva answer prepared: linear search works on unsorted data by checking
  every element in order; avoid it for large/sorted data (use binary search).

## Academic integrity statement
AI assisted learning and drafting, but every line of code was read, compiled, run,
and is explainable by the student. The student can defend the implementation,
complexity claims, and trace in the individual viva.
