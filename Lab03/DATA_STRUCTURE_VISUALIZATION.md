# Data Structure Visualization - Lab 3

## Node anatomy
```
+-------------------+
| data (int)        |
| prev -> Node|null |
| next -> Node|null |
+-------------------+
```

## Empty list
```
head -> null
tail -> null
size = 0
```

## Three nodes: [5] <-> [10] <-> [20]
```
head
  |
  v
+----+      +----+      +----+
| 5  |<---->| 10 |<---->| 20 |
|prev|      |prev|      |prev|
|null|      | 5  |      | 10 |
|next|      |next|      |next|
| 10 |      | 20 |      |null|
+----+      +----+      +----+
                              |
                              v
                             tail
```

## Insert at head (value 1)
```
before:  head -> [5] <-> [10] <-> [20] <- tail
after:   head -> [1] <-> [5] <-> [10] <-> [20] <- tail
rewired: [1].next=[5], [5].prev=[1], head=[1]
```

## Delete middle (value 10)
```
before:  [5] <-> [10] <-> [20]
after:   [5] <-> [20]
rewired: [5].next=[20], [20].prev=[5]
(no shifting of data)
```

## Forward vs backward traversal
```
Forward  (head -> tail): 5 -> 10 -> 20
Backward (tail -> head): 20 -> 10 -> 5
```
