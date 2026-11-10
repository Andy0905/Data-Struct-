# Data Structure Visualization - Lab 1

## Backing store: fixed int[] of capacity 5
```
+------+------+------+------+------+
|  72  |  90  |  84  |   0  |   0  |   <- backing array (slots)
+------+------+------+------+------+
  idx0   idx1   idx2   idx3   idx4
  ^active region (size=3)^  ^unused (zeros)^
```

## Insert 88 at index 1 (shifts marked with *)
```
Before:  [72, 90, 84,  0,  0]   size=3
Step1:   [72, 90, 84, 84*, 0]   (84 shifted right)
Step2:   [72, 90, 90*,84,  0]   (90 shifted right)
Step3:   [72, 88, 90, 84,  0]   (88 inserted)   size=4
```
Shifted elements: 90 (idx1->2), 84 (idx2->3).

## Delete index 1 from [72,88,90,84,0]
```
Before:  [72, 88, 90, 84,  0]   size=4
Step1:   [72, 90*,84*, 0*, 0]   (90,84 shift left; last slot cleared)
After:   [72, 90, 84,  0,  0]   size=3
```

## Indexing rule
- Valid active indexes: 0 .. size-1 (zero-based).
- Insertion point may be 0 .. size (append at size).
- Capacity is fixed; insert beyond capacity is rejected.

## Complexity
- Access by index: O(1) (direct address calculation).
- Insert/delete: O(n) (linear shifts).
