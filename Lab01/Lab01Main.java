/**
 * Lab 1 demo / test driver.
 * Runs the required manual trace and all test cases, printing results.
 */
public class Lab01Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("LAB 1: Arrays and Complexity Basics");
        System.out.println("ScoreArrayManager - fixed int[] with operation cost");
        System.out.println("==================================================");
        System.out.println();

        // ---- Manual trace: insert 88 at index 1 into [72, 90, 84, 0, 0] ----
        System.out.println("--------------------------------------------------");
        System.out.println("MANUAL TRACE: insert 88 at index 1 into [72, 90, 84, 0, 0]");
        System.out.println("--------------------------------------------------");
        ScoreArrayManager trace = new ScoreArrayManager(5);
        trace.insertAt(0, 72);
        trace.insertAt(1, 90);
        trace.insertAt(2, 84);
        System.out.print("Before insert: ");
        trace.display();
        System.out.println("Action: insertAt(1, 88)");
        trace.insertAt(1, 88);
        System.out.print("After insert:  ");
        trace.display();
        System.out.println("Shifted values: 90 moved 90->index2, 84 moved 84->index3.");
        System.out.println("Final active elements: [72, 88, 90, 84] (slot 4 unused = 0).");
        System.out.println();

        // ---- Access first and last valid indexes ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Access first and last valid indexes");
        System.out.println("--------------------------------------------------");
        System.out.println("First valid index (0) value  = " + trace.getScore(0));
        System.out.println("Last valid index (" + (trace.size() - 1) + ") value = " + trace.getScore(trace.size() - 1));
        System.out.println();

        // ---- Insert at index 0 and in the middle ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Insert at index 0 and in the middle");
        System.out.println("--------------------------------------------------");
        ScoreArrayManager t2 = new ScoreArrayManager(5);
        t2.insertAt(0, 10);
        t2.insertAt(1, 20);
        t2.insertAt(2, 30);
        System.out.print("Start:            ");
        t2.display();
        t2.insertAt(0, 5);
        System.out.print("After insert@0:   ");
        t2.display();
        t2.insertAt(2, 15);
        System.out.print("After insert@mid: ");
        t2.display();
        System.out.println();

        // ---- Update score ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: updateScore");
        System.out.println("--------------------------------------------------");
        t2.updateScore(2, 99);
        System.out.print("After update:     ");
        t2.display();
        System.out.println();

        // ---- Delete final active element ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Delete final active element");
        System.out.println("--------------------------------------------------");
        System.out.print("Before delete:    ");
        t2.display();
        t2.deleteAt(t2.size() - 1);
        System.out.print("After delete:     ");
        t2.display();
        System.out.println();

        // ---- Reject invalid index ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Reject invalid index");
        System.out.println("--------------------------------------------------");
        t2.insertAt(10, 7);
        t2.deleteAt(-1);
        t2.updateScore(100, 1);
        t2.getScore(50);
        System.out.print("Unchanged:        ");
        t2.display();
        System.out.println();

        // ---- Reject full array ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Reject full array");
        System.out.println("--------------------------------------------------");
        ScoreArrayManager full = new ScoreArrayManager(3);
        full.insertAt(0, 1);
        full.insertAt(1, 2);
        full.insertAt(2, 3);
        System.out.print("Full array:       ");
        full.display();
        full.insertAt(1, 9);
        System.out.print("Still full:       ");
        full.display();
        System.out.println();

        // ---- Complexity summary ----
        System.out.println("--------------------------------------------------");
        System.out.println("COMPLEXITY SUMMARY");
        System.out.println("--------------------------------------------------");
        System.out.println("arr[index] access  : O(1)  (direct address computation)");
        System.out.println("updateScore(index)  : O(1)");
        System.out.println("insertAt(index)     : O(n)  (shifts up to n elements)");
        System.out.println("deleteAt(index)     : O(n)  (shifts up to n elements)");
        System.out.println("display()           : O(n)");
        System.out.println();
        System.out.println("All required operations executed. Lab 1 complete.");
    }
}
