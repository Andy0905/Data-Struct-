# Algorithm Flowchart - Lab 2

## findFirst(items, target)
```
START
  |
  v
[items == null or target == null?] --Yes--> [Reject / return -1] --> END
  | No
  v
[i = 0]
  |
  v
[i < items.length?] --No--> [Print "no match"; return -1] --> END
  | Yes
  v
[Print "inspect index i"]
  |
  v
[items[i].equals(target)?] --Yes--> [Print match; return i] --> END
  | No
  v
[i++]
  |
  v
(loop back to [i < items.length?])
```

## countMatches(items, target)
```
START
  |
  v
[items == null or target == null?] --Yes--> [Reject / return 0] --> END
  | No
  v
[count = 0; i = 0]
  |
  v
[i < items.length?] --No--> [Print total; return count] --> END
  | Yes
  v
[Print "inspect index i"]
  |
  v
[items[i].equals(target)?] --Yes--> [count++; print match #count]
  | No
  v
[i++]
  |
  v
(loop back to [i < items.length?])
```

## Complexity
- findFirst: best O(1), worst O(n), average O(n/2) ~ O(n); space O(1).
- countMatches: always O(n) (must scan every element); space O(1).
