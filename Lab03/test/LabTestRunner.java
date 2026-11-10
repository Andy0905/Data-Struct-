import java.util.Arrays;

/**
 * Acceptance runner for Lab 3 - Doubly Linked List.
 * Run with assertions enabled:
 *   javac -d out src/*.java test/*.java
 *   java -ea -cp out LabTestRunner
 */
public class LabTestRunner {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("LAB 3: Doubly Linked List - acceptance runner");
        System.out.println("==================================================");
        System.out.println();

        run("Empty List", LabTestRunner::testEmpty);
        run("Single Node", LabTestRunner::testSingle);
        run("Head Deletion", LabTestRunner::testHeadDeletion);
        run("Tail Deletion", LabTestRunner::testTailDeletion);
        run("Missing Value", LabTestRunner::testMissing);
        run("Duplicates", LabTestRunner::testDuplicates);
        run("Bidirectional Traversal", LabTestRunner::testBidirectional);
        run("Invariants After Mutations", LabTestRunner::testInvariantsAfterMutations);
        run("Aliases", LabTestRunner::testAliases);
        run("Measurement / Complexity", LabTestRunner::testMeasurement);

        System.out.println();
        System.out.println("==================================================");
        System.out.println("RESULTS: " + passed + " passed, " + failed + " failed");
        System.out.println("==================================================");
        if (failed > 0) {
            System.exit(1);
        }
        System.out.println("ALL TESTS PASSED");
    }

    private static void run(String name, Runnable test) {
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: " + name);
        System.out.println("--------------------------------------------------");
        try {
            test.run();
            passed++;
            System.out.println("OK: " + name);
        } catch (Throwable t) {
            failed++;
            System.out.println("FAIL: " + name + " -> " + t);
        }
        System.out.println();
    }

    // ---------------------------------------------------------------
    // Test cases
    // ---------------------------------------------------------------

    static void testEmpty() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        assert list.isEmpty() : "empty list should be empty";
        assert list.size() == 0 : "empty size should be 0";
        assert Arrays.equals(list.toArray(), new int[]{}) : "forward snapshot empty";
        assert Arrays.equals(list.toBackwardArray(), new int[]{}) : "backward snapshot empty";
        assert list.checkInvariants() : "empty invariants";
        assert list.getHead() == null && list.getTail() == null : "endpoints null";
        assert !list.deleteByValue(42) : "delete missing on empty returns false";
        assert list.size() == 0 : "size unchanged after failed delete";
        assert list.indexOf(42) == -1 : "indexOf missing";
        assert !list.contains(42) : "contains missing";
    }

    static void testSingle() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.insertAtHead(7);
        assert !list.isEmpty();
        assert list.size() == 1;
        assert Arrays.equals(list.toArray(), new int[]{7});
        assert Arrays.equals(list.toBackwardArray(), new int[]{7});
        assert list.getHead() == list.getTail() : "single node head==tail";
        assert list.getHead().prev == null && list.getHead().next == null;
        assert list.checkInvariants();
        assert list.contains(7) && list.indexOf(7) == 0;
        // delete the only node
        assert list.deleteByValue(7) : "delete single returns true";
        assert list.isEmpty() && list.size() == 0;
        assert list.getHead() == null && list.getTail() == null;
        assert list.checkInvariants();
    }

    static void testHeadDeletion() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.insertAtTail(1);
        list.insertAtTail(2);
        list.insertAtTail(3);
        assert Arrays.equals(list.toArray(), new int[]{1, 2, 3});
        assert list.deleteByValue(1) : "head deletion returns true";
        assert Arrays.equals(list.toArray(), new int[]{2, 3}) : "after head delete";
        assert Arrays.equals(list.toBackwardArray(), new int[]{3, 2}) : "backward after head delete";
        assert list.size() == 2;
        assert list.getHead().data == 2 && list.getHead().prev == null;
        assert list.checkInvariants();
        // deleting a missing head value leaves list intact
        assert !list.deleteByValue(999);
        assert Arrays.equals(list.toArray(), new int[]{2, 3});
    }

    static void testTailDeletion() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.insertAtTail(1);
        list.insertAtTail(2);
        list.insertAtTail(3);
        assert list.deleteByValue(3) : "tail deletion returns true";
        assert Arrays.equals(list.toArray(), new int[]{1, 2});
        assert Arrays.equals(list.toBackwardArray(), new int[]{2, 1});
        assert list.getTail().data == 2 && list.getTail().next == null;
        assert list.checkInvariants();
        // delete last remaining tail twice
        list.deleteByValue(2);
        list.deleteByValue(1);
        assert list.isEmpty();
        assert list.checkInvariants();
    }

    static void testMissing() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.insertAtHead(5);
        list.insertAtTail(6);
        int sizeBefore = list.size();
        int[] before = list.toArray();
        assert !list.deleteByValue(42) : "missing returns false";
        assert list.size() == sizeBefore : "size unchanged";
        assert Arrays.equals(list.toArray(), before) : "list unchanged";
        assert list.checkInvariants();
        // empty list missing
        DoublyIntLinkedList empty = new DoublyIntLinkedList();
        assert !empty.deleteByValue(0);
        assert empty.checkInvariants();
    }

    static void testDuplicates() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.insertAtTail(5);
        list.insertAtTail(5);
        list.insertAtTail(5);
        assert list.size() == 3;
        assert list.deleteByValue(5) : "first duplicate removed";
        assert list.size() == 2;
        assert Arrays.equals(list.toArray(), new int[]{5, 5}) : "only first removed";
        assert list.checkInvariants();
        // remove all remaining
        assert list.deleteByValue(5);
        assert list.deleteByValue(5);
        assert list.isEmpty();
        assert list.checkInvariants();
    }

    static void testBidirectional() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.insertAtTail(10);
        list.insertAtHead(1);
        list.insertAtTail(20);
        // expected order: 1, 10, 20
        assert Arrays.equals(list.toForwardArray(), new int[]{1, 10, 20}) : "forward";
        assert Arrays.equals(list.toBackwardArray(), new int[]{20, 10, 1}) : "backward";
        // cross-check: backward is reverse of forward
        int[] fwd = list.toArray();
        int[] bwd = list.toBackwardArray();
        for (int i = 0; i < fwd.length; i++) {
            assert fwd[i] == bwd[fwd.length - 1 - i] : "reverse relationship";
        }
        list.traverseForward();
        list.traverseBackward();
        assert list.checkInvariants();
    }

    static void testInvariantsAfterMutations() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        // mixed operations
        list.insertAtHead(1);
        assert list.checkInvariants();
        list.insertAtTail(2);
        assert list.checkInvariants();
        list.insertAtHead(0);
        assert list.checkInvariants();
        list.deleteByValue(1);
        assert list.checkInvariants();
        list.deleteByValue(0);
        assert list.checkInvariants();
        list.deleteByValue(2);
        assert list.checkInvariants();
        assert list.isEmpty();
        // rebuild and delete tail repeatedly
        for (int i = 0; i < 5; i++) {
            list.insertAtTail(i);
        }
        assert list.checkInvariants();
        for (int i = 4; i >= 0; i--) {
            assert list.deleteByValue(i) : "delete " + i;
            assert list.checkInvariants();
        }
        assert list.isEmpty();
    }

    static void testAliases() {
        DoublyIntLinkedList list = new DoublyIntLinkedList();
        list.addFirst(2);
        list.addLast(4);
        list.insertAtHead(1);
        list.insertAtTail(5);
        assert Arrays.equals(list.forwardValues(), new int[]{1, 2, 4, 5});
        assert Arrays.equals(list.getForwardValues(), new int[]{1, 2, 4, 5});
        assert Arrays.equals(list.toArrayForward(), new int[]{1, 2, 4, 5});
        assert Arrays.equals(list.toReverseArray(), new int[]{5, 4, 2, 1});
        assert Arrays.equals(list.reverseValues(), new int[]{5, 4, 2, 1});
        assert Arrays.equals(list.backwardValues(), new int[]{5, 4, 2, 1});
        assert list.removeValue(2);
        assert list.removeFirstOccurrence(4);
        assert list.deleteFirst(1);
        assert Arrays.equals(list.toArray(), new int[]{5});
        assert list.validateInvariants();
        assert list.invariantsHold();
        assert list.checkLinks();
        list.clear();
        assert list.isEmpty() && list.checkInvariants();
    }

    static void testMeasurement() {
        System.out.println("Complexity / measurement (timing is informational):");
        int[] sizes = {100, 1000, 10000};
        for (int n : sizes) {
            DoublyIntLinkedList list = new DoublyIntLinkedList();
            for (int i = 0; i < n; i++) {
                list.insertAtTail(i);
            }
            assert list.checkInvariants();

            // deleteByValue on a MISSING value -> O(n) search
            long t0 = System.nanoTime();
            boolean r = list.deleteByValue(-1);
            long t1 = System.nanoTime();
            assert !r : "missing delete returns false";
            System.out.println("  n=" + n + " deleteByValue(missing) took "
                    + (t1 - t0) + " ns  (O(n) search)");

            // insertAtHead -> O(1)
            t0 = System.nanoTime();
            list.insertAtHead(-999);
            t1 = System.nanoTime();
            System.out.println("  n=" + n + " insertAtHead took "
                    + (t1 - t0) + " ns  (O(1))");
            assert list.checkInvariants();

            // insertAtTail -> O(1)
            t0 = System.nanoTime();
            list.insertAtTail(-888);
            t1 = System.nanoTime();
            System.out.println("  n=" + n + " insertAtTail took "
                    + (t1 - t0) + " ns  (O(1))");
            assert list.checkInvariants();

            // invariant check -> O(n)
            t0 = System.nanoTime();
            boolean ok = list.checkInvariants();
            t1 = System.nanoTime();
            assert ok;
            System.out.println("  n=" + n + " checkInvariants took "
                    + (t1 - t0) + " ns  (O(n))");

            // snapshot -> O(n)
            t0 = System.nanoTime();
            int[] snap = list.toArray();
            t1 = System.nanoTime();
            assert snap.length == list.size();
            System.out.println("  n=" + n + " toArray took "
                    + (t1 - t0) + " ns  (O(n)), len=" + snap.length);
        }
        System.out.println("Auxiliary space: snapshots allocate O(n) ints; list itself O(n) nodes.");
        System.out.println("Best/avg/worst: add head/tail O(1); delete O(1) first node, O(n) worst;");
        System.out.println("traverse O(n); invariant check O(n).");
    }
}
