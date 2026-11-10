/**
 * Lab 3 - Doubly Linked List of integers.
 *
 * Maintains head and tail references so that insertion at either end is O(1)
 * and traversal is possible in both directions. Every mutation keeps the
 * size counter and the prev/next links consistent; checkInvariants() can be
 * called at any time to prove that the forward/backward pointer invariants
 * still hold.
 *
 * Required operations (Part B acceptance):
 *   - insert at head and at tail (size + endpoints maintained)
 *   - delete the first matching value, reporting whether deletion occurred
 *   - traverse both directions, exposing values as arrays for testing
 *   - validate head, tail, next and previous-link invariants after mutations
 */
public class DoublyIntLinkedList {

    /** A single node of the doubly linked list. */
    public static class Node {
        public int data;
        public Node prev;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    private Node head;   // first node (null when empty)
    private Node tail;   // last node  (null when empty)
    private int size;    // number of nodes

    /** Create an empty list. */
    public DoublyIntLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /** Number of elements currently stored. */
    public int size() {
        return size;
    }

    /** True when the list contains no elements. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** First node (null if empty). Exposed for invariant inspection. */
    public Node getHead() {
        return head;
    }

    /** Last node (null if empty). Exposed for invariant inspection. */
    public Node getTail() {
        return tail;
    }

    // ------------------------------------------------------------------
    // Insertion
    // ------------------------------------------------------------------

    /** Insert value before the current head (O(1)). */
    public void insertAtHead(int value) {
        Node n = new Node(value);
        if (head == null) {          // empty list
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
        size++;
    }

    /** Alias for insertAtHead. */
    public void addFirst(int value) {
        insertAtHead(value);
    }

    /** Insert value after the current tail (O(1)). */
    public void insertAtTail(int value) {
        Node n = new Node(value);
        if (tail == null) {          // empty list
            head = n;
            tail = n;
        } else {
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
        size++;
    }

    /** Alias for insertAtTail. */
    public void addLast(int value) {
        insertAtTail(value);
    }

    /** Convenience alias matching the singly-list skeleton naming. */
    public void insertAtTailAlias(int value) {
        insertAtTail(value);
    }

    // ------------------------------------------------------------------
    // Deletion
    // ------------------------------------------------------------------

    /**
     * Delete the FIRST node whose data equals value.
     * Returns true if a node was removed, false otherwise (value missing).
     */
    public boolean deleteByValue(int value) {
        Node cur = head;
        while (cur != null) {
            if (cur.data == value) {
                // unlink cur, fixing neighbours and endpoints
                if (cur.prev != null) {
                    cur.prev.next = cur.next;
                } else {
                    head = cur.next;          // deleting the head
                }
                if (cur.next != null) {
                    cur.next.prev = cur.prev;
                } else {
                    tail = cur.prev;          // deleting the tail
                }
                // help the garbage collector
                cur.prev = null;
                cur.next = null;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false; // not found
    }

    /** Aliases for deleteByValue. */
    public boolean removeValue(int value) {
        return deleteByValue(value);
    }

    public boolean removeFirstOccurrence(int value) {
        return deleteByValue(value);
    }

    public boolean deleteFirst(int value) {
        return deleteByValue(value);
    }

    /** Remove all elements. */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // ------------------------------------------------------------------
    // Traversal / snapshots
    // ------------------------------------------------------------------

    /** Print the list from head to tail. */
    public void traverseForward() {
        System.out.print("Forward:  ");
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data);
            if (cur.next != null) {
                System.out.print(" <-> ");
            }
            cur = cur.next;
        }
        System.out.println("  (size=" + size + ")");
    }

    /** Print the list from tail to head. */
    public void traverseBackward() {
        System.out.print("Backward: ");
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.data);
            if (cur.prev != null) {
                System.out.print(" <-> ");
            }
            cur = cur.prev;
        }
        System.out.println("  (size=" + size + ")");
    }

    /** Snapshot of values from head to tail (for testing). */
    public int[] toArray() {
        return toForwardArray();
    }

    public int[] toForwardArray() {
        int[] out = new int[size];
        Node cur = head;
        int i = 0;
        while (cur != null) {
            out[i++] = cur.data;
            cur = cur.next;
        }
        return out;
    }

    /** Aliases for the forward snapshot. */
    public int[] forwardValues() {
        return toForwardArray();
    }

    public int[] getForwardValues() {
        return toForwardArray();
    }

    public int[] toArrayForward() {
        return toForwardArray();
    }

    /** Snapshot of values from tail to head (for testing). */
    public int[] toBackwardArray() {
        int[] out = new int[size];
        Node cur = tail;
        int i = 0;
        while (cur != null) {
            out[i++] = cur.data;
            cur = cur.prev;
        }
        return out;
    }

    /** Aliases for the backward snapshot. */
    public int[] backwardValues() {
        return toBackwardArray();
    }

    public int[] toReverseArray() {
        return toBackwardArray();
    }

    public int[] reverseValues() {
        return toBackwardArray();
    }

    // ------------------------------------------------------------------
    // Search helpers
    // ------------------------------------------------------------------

    public boolean contains(int value) {
        Node cur = head;
        while (cur != null) {
            if (cur.data == value) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int indexOf(int value) {
        Node cur = head;
        int i = 0;
        while (cur != null) {
            if (cur.data == value) {
                return i;
            }
            cur = cur.next;
            i++;
        }
        return -1;
    }

    // ------------------------------------------------------------------
    // Invariant validation
    // ------------------------------------------------------------------

    /**
     * Validate the doubly linked list invariants:
     *   - empty  <=> head == null && tail == null && size == 0
     *   - head.prev == null, tail.next == null
     *   - forward walk from head reaches tail in exactly size steps
     *   - backward walk from tail reaches head in exactly size steps
     *   - every node's prev.next == node and next.prev == node
     * No mutation is performed.
     */
    public boolean checkInvariants() {
        // Empty case.
        if (size == 0) {
            return head == null && tail == null;
        }
        if (head == null || tail == null) {
            return false;
        }
        if (head.prev != null) {
            return false;
        }
        if (tail.next != null) {
            return false;
        }

        // Forward walk.
        int count = 0;
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            if (cur.prev != prev) {
                return false;
            }
            prev = cur;
            cur = cur.next;
            count++;
            if (count > size) {        // cycle guard
                return false;
            }
        }
        if (count != size) {
            return false;
        }
        if (prev != tail) {
            return false;
        }

        // Backward walk.
        count = 0;
        cur = tail;
        Node nxt = null;
        while (cur != null) {
            if (cur.next != nxt) {
                return false;
            }
            nxt = cur;
            cur = cur.prev;
            count++;
            if (count > size) {        // cycle guard
                return false;
            }
        }
        if (count != size) {
            return false;
        }
        if (nxt != head) {
            return false;
        }
        return true;
    }

    /** Aliases for checkInvariants. */
    public boolean validateInvariants() {
        return checkInvariants();
    }

    public boolean invariantsHold() {
        return checkInvariants();
    }

    public boolean checkLinks() {
        return checkInvariants();
    }
}
