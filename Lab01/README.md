# Lab 1 - Arrays and Complexity Basics

Cor Jesu College - College of Engineering
Computer Engineering, CpE 211
Professor: Engr. Jamie Eduardo Rosal, MSCpE

## Contents
- `ScoreArrayManager.java` - fixed-size int[] manager (required methods).
- `Lab01Main.java` - demo / test driver.
- `MANUAL_TRACE.md` - manual trace of insert 88 at index 1.
- `TEST_EVIDENCE.md` - completed test-evidence table.
- `AI_USE_DECLARATION.md` - AI-use declaration.
- `run_output.txt` - console log of a successful run.

## Required methods implemented
- `display()`
- `updateScore(int index, int value)`
- `insertAt(int index, int value)`
- `deleteAt(int index)`

Each operation reports its cost (number of shifts) and Big-O class.

## How to compile and run
```sh
javac ScoreArrayManager.java Lab01Main.java
java Lab01Main
```
(Java 21 JDK used; any Java 8+ works.)

## Complexity summary
| Operation | Time | Reason |
|---|---|---|
| arr[index] access | O(1) | Direct address: base + index * size |
| updateScore(index) | O(1) | Single write |
| insertAt(index) | O(n) | Shifts up to n elements right |
| deleteAt(index) | O(n) | Shifts up to n elements left |
| display() | O(n) | Visits every slot |

## Viva question
Why is `arr[index]` O(1), while inserting at index 0 is O(n)?
- Access computes the memory address in constant time (no traversal).
- Inserting at 0 forces every existing element to shift right by one, O(n) work.
