# Algorithm Flowchart - Lab 3

## insertAtHead(value)
```
START
  newNode = Node(value)
  IF head == null:
      head = tail = newNode
  ELSE:
      newNode.next = head
      head.prev = newNode
      head = newNode
  size = size + 1
END
```

## insertAtTail(value)
```
START
  newNode = Node(value)
  IF tail == null:
      head = tail = newNode
  ELSE:
      newNode.prev = tail
      tail.next = newNode
      tail = newNode
  size = size + 1
END
```

## deleteByValue(value)
```
START
  cur = head
  WHILE cur != null:
      IF cur.data == value:
          IF cur.prev != null: cur.prev.next = cur.next
          ELSE: head = cur.next
          IF cur.next != null: cur.next.prev = cur.prev
          ELSE: tail = cur.prev
          cur.prev = cur.next = null
          size = size - 1
          RETURN true
      cur = cur.next
  RETURN false
END
```

## toArray (forward)
```
START
  out[0..size-1]
  cur = head; i = 0
  WHILE cur != null:
      out[i] = cur.data; i++; cur = cur.next
  RETURN out
END
```

## checkInvariants()
```
START
  IF size == 0: RETURN (head==null && tail==null)
  IF head==null OR tail==null: RETURN false
  IF head.prev != null OR tail.next != null: RETURN false
  forward: walk head->tail counting nodes, verify prev links, count==size, last==tail
  backward: walk tail->head counting nodes, verify next links, count==size, last==head
  RETURN true
END
```
