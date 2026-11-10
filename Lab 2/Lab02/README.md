# Lab 2 - Linear Search in Real Applications

Cor Jesu College - College of Engineering
Computer Engineering, CpE 211
Professor: Engr. Jamie Eduardo Rosal, MSCpE

## Contents
- `LibraryFinder.java` - linear search for strings (and int overload).
- `Lab02Main.java` - demo / test driver.
- `MANUAL_TRACE.md` - manual trace of searching 'Java' and 'Python'.
- `TEST_EVIDENCE.md` - completed test-evidence table.
- `AI_USE_DECLARATION.md` - AI-use declaration.
- `run_output.txt` - console log of a successful run.

## Required methods implemented
- `findFirst(String[] items, String target)` (returns first index or -1)
- `countMatches(String[] items, String target)` (returns total matches)

Each method prints every inspected index.

## How to compile and run
```sh
javac LibraryFinder.java Lab02Main.java
java Lab02Main
```
(Java 21 JDK used; any Java 8+ works.)

## Complexity summary
| Operation | Time | Space | Notes |
|---|---|---|---|
| findFirst (best) | O(1) | O(1) | target at index 0 |
| findFirst (worst) | O(n) | O(1) | target at last index or missing |
| countMatches | O(n) | O(1) | always scans all n |

## Viva question
Why does Linear Search work on unsorted data, and when should it be avoided?
- It checks elements in storage order, so no ordering is required.
- Avoid it for large or sorted collections; binary search (O(log n)) is better
  when the data is sorted.
