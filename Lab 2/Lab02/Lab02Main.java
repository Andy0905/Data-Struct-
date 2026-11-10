/**
 * Lab 2 demo / test driver.
 * Runs the required manual trace and all test cases, printing results.
 */
public class Lab02Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("LAB 2: Linear Search in Real Applications");
        System.out.println("LibraryFinder - unsorted String[] linear search");
        System.out.println("==================================================");
        System.out.println();

        String[] shelf = {"C", "Java", "DSA", "Java", "SQL"};

        // ---- Manual trace: search 'Java' and 'Python' ----
        System.out.println("--------------------------------------------------");
        System.out.println("MANUAL TRACE: search 'Java' and 'Python' in [C, Java, DSA, Java, SQL]");
        System.out.println("--------------------------------------------------");
        System.out.print("Shelf: ");
        printArray(shelf);
        System.out.println();

        System.out.println("Action: findFirst(shelf, \"Java\")");
        int idxJava = LibraryFinder.findFirst(shelf, "Java");
        System.out.println("Result: first index = " + idxJava + " (comparisons made above)");
        System.out.println();

        System.out.println("Action: countMatches(shelf, \"Java\")");
        int cntJava = LibraryFinder.countMatches(shelf, "Java");
        System.out.println("Result: count = " + cntJava);
        System.out.println();

        System.out.println("Action: findFirst(shelf, \"Python\")");
        int idxPy = LibraryFinder.findFirst(shelf, "Python");
        System.out.println("Result: first index = " + idxPy + " (not found)");
        System.out.println();

        // ---- Test: target at first position ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Target at first position");
        System.out.println("--------------------------------------------------");
        String[] t1 = {"Alpha", "Beta", "Gamma"};
        System.out.print("Array: "); printArray(t1);
        System.out.println("findFirst(t1, \"Alpha\") = " + LibraryFinder.findFirst(t1, "Alpha"));
        System.out.println();

        // ---- Test: target at last position ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Target at last position");
        System.out.println("--------------------------------------------------");
        System.out.print("Array: "); printArray(t1);
        System.out.println("findFirst(t1, \"Gamma\") = " + LibraryFinder.findFirst(t1, "Gamma"));
        System.out.println();

        // ---- Test: target missing ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Target missing");
        System.out.println("--------------------------------------------------");
        System.out.print("Array: "); printArray(t1);
        System.out.println("findFirst(t1, \"Delta\") = " + LibraryFinder.findFirst(t1, "Delta"));
        System.out.println("countMatches(t1, \"Delta\") = " + LibraryFinder.countMatches(t1, "Delta"));
        System.out.println();

        // ---- Test: repeated titles including adjacent duplicates ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Repeated titles including adjacent duplicates");
        System.out.println("--------------------------------------------------");
        String[] t2 = {"Java", "Java", "DSA", "Java"};
        System.out.print("Array: "); printArray(t2);
        System.out.println("findFirst(t2, \"Java\") = " + LibraryFinder.findFirst(t2, "Java"));
        System.out.println("countMatches(t2, \"Java\") = " + LibraryFinder.countMatches(t2, "Java"));
        System.out.println();

        // ---- Edge: empty array ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Empty array");
        System.out.println("--------------------------------------------------");
        String[] empty = {};
        System.out.print("Array: "); printArray(empty);
        System.out.println("findFirst(empty, \"X\") = " + LibraryFinder.findFirst(empty, "X"));
        System.out.println("countMatches(empty, \"X\") = " + LibraryFinder.countMatches(empty, "X"));
        System.out.println();

        // ---- Edge: case sensitivity ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Case sensitivity (\"java\" vs \"Java\")");
        System.out.println("--------------------------------------------------");
        System.out.print("Array: "); printArray(shelf);
        System.out.println("findFirst(shelf, \"java\") = " + LibraryFinder.findFirst(shelf, "java"));
        System.out.println();

        // ---- Numbers overload demo ----
        System.out.println("--------------------------------------------------");
        System.out.println("TEST: Linear search on numbers (int[])");
        System.out.println("--------------------------------------------------");
        int[] nums = {5, 3, 7, 3, 9};
        System.out.print("Array: "); printIntArray(nums);
        System.out.println("findFirst(nums, 3) = " + LibraryFinder.findFirst(nums, 3));
        System.out.println("countMatches(nums, 3) = " + LibraryFinder.countMatches(nums, 3));
        System.out.println();

        // ---- Complexity summary ----
        System.out.println("--------------------------------------------------");
        System.out.println("COMPLEXITY SUMMARY");
        System.out.println("--------------------------------------------------");
        System.out.println("Linear search (findFirst) : O(n) time, O(1) space");
        System.out.println("  best case  : O(1) (target at index 0)");
        System.out.println("  worst case : O(n) (target at last index or missing)");
        System.out.println("countMatches               : O(n) time (always scans all n)");
        System.out.println("Works on unsorted data because every element is checked in order.");
        System.out.println();
        System.out.println("All required operations executed. Lab 2 complete.");
    }

    private static void printArray(String[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private static void printIntArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
