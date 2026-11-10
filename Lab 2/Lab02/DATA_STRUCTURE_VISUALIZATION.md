# Data Structure Visualization - Lab 2

## Backing store: unsorted String[] of book titles
```
Index:    0       1       2       3       4
Value:  ["C", "Java", "DSA", "Java", "SQL"]
          ^ linear order as stored on the shelf (no sorting required)
```

## Linear search for "Java" (findFirst)
```
Step 0: inspect index 0 -> "C"     (not equal)  continue
Step 1: inspect index 1 -> "Java"  (equal)      STOP, return 1
```
Comparisons made: 2 (best-ish; target at index 1).

## Linear search for "Python" (findFirst)
```
Step 0: "C"     no
Step 1: "Java"  no
Step 2: "DSA"   no
Step 3: "Java"  no
Step 4: "SQL"   no
End of list -> return -1
```
Comparisons made: 5 (worst case: missing).

## countMatches for "Java"
```
index0 "C"    no
index1 "Java" yes  -> count=1
index2 "DSA"  no
index3 "Java" yes  -> count=2
index4 "SQL"  no
total = 2
```

## Why it works on unsorted data
Linear search never assumes ordering; it compares the target against
every element in storage order until a match or the end of the array.

## Complexity
- Time: O(n) (each element inspected at most once).
- Space: O(1) (only index and counter variables).
- Best case O(1); worst case O(n).
