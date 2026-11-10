# Manual Trace - Lab 3: Doubly Linked List

## Scenario A - Insert at tail then head
Start: empty list.

1. insertAtTail(10)
   head -> [10] <- tail,  prev=null, next=null, size=1
2. insertAtTail(20)
   head -> [10] <-> [20] <- tail
   [10].next=[20], [20].prev=[10], size=2
3. insertAtHead(5)
   head -> [5] <-> [10] <-> [20] <- tail
   [5].next=[10], [10].prev=[5], size=3

Forward snapshot:  [5, 10, 20]
Backward snapshot: [20, 10, 5]
Invariants: head.prev=null, tail.next=null, counts match size=3. PASS.

## Scenario B - Delete middle value 10
List before: [5] <-> [10] <-> [20], size=3
Operation: deleteByValue(10)
- cur = node(10); cur.prev=node(5), cur.next=node(20)
- cur.prev.next = cur.next  -> node(5).next = node(20)
- cur.next.prev = cur.prev  -> node(20).prev = node(5)
- size = 2
After: [5] <-> [20], head=node(5), tail=node(20)
Forward: [5, 20], Backward: [20, 5]. Invariants PASS.

## Scenario C - Delete head and tail
List: [5] <-> [20], size=2
deleteByValue(5): cur.prev==null -> head=cur.next (node20); node20.prev=null; size=1
  -> [20], head=tail=node(20)
deleteByValue(20): cur.next==null -> tail=cur.prev (null); head=null; size=0
  -> empty. Invariants PASS.

## Scenario D - Missing value
List: [1,2,3]; deleteByValue(999) walks 3 nodes, returns false, list unchanged.
Cost: O(n) (n comparisons, 0 pointer writes).

## Marked pointer changes
- Insert at head: 1 new node, 2 pointer writes (n.next=head, head.prev=n) + head reassign.
- Insert at tail: 2 pointer writes (n.prev=tail, tail.next=n) + tail reassign.
- Delete middle: 2 pointer writes (prev.next=next, next.prev=prev).
- No element shifting occurs (unlike arrays).
