# Algorithm Flowchart - Lab 1

## insertAt(index, value)
```
START
  |
  v
[Is 0 <= index <= size?] --No--> [Reject: invalid index] --> END
  | Yes
  v
[Is size < capacity?] --No--> [Reject: array full] --> END
  | Yes
  v
[i = size]
  |
  v
[i > index?] --Yes--> [scores[i] = scores[i-1]; i--; shifts++] --> (loop)
  | No
  v
[scores[index] = value]
  |
  v
[size++]
  |
  v
[Report shifts & cost O(n)]
  |
  v
END
```

## deleteAt(index)
```
START
  |
  v
[Is 0 <= index < size?] --No--> [Reject: invalid index] --> END
  | Yes
  v
[removed = scores[index]]
  |
  v
[i = index]
  |
  v
[i < size-1?] --Yes--> [scores[i] = scores[i+1]; i++; shifts++] --> (loop)
  | No
  v
[size--; scores[size] = 0]
  |
  v
[Report shifts & cost O(n)]
  |
  v
END
```

## updateScore(index, value)
```
START -> [valid index?] --No--> Reject -> END
            | Yes
            v
        [scores[index] = value] -> Report O(1) -> END
```
