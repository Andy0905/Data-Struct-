/**
 * Lab 2 - Linear Search in Real Applications
 * LibraryFinder searches an unsorted String[] of book titles
 * (linear search) and reports every inspected index.
 *
 * Required methods:
 *   findFirst(String[] items, String target)
 *   countMatches(String[] items, String target)
 *
 * Bonus overloads for int[] (numbers) are provided so the same
 * algorithm can be traced on numeric data.
 */
public class LibraryFinder {

    /** Find the first index where items[i] equals target (case-sensitive).
     *  Returns -1 when not found. Prints every inspected index. */
    public static int findFirst(String[] items, String target) {
        if (items == null) {
            System.out.println("findFirst: REJECTED - items array is null");
            return -1;
        }
        if (target == null) {
            System.out.println("findFirst: REJECTED - target is null");
            return -1;
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println("  inspect index " + i + ": \"" + items[i] + "\"");
            if (target.equals(items[i])) {
                System.out.println("  -> match at index " + i);
                return i;
            }
        }
        System.out.println("  -> no match found (end of list)");
        return -1;
    }

    /** Count how many entries equal target. Prints every inspected index. */
    public static int countMatches(String[] items, String target) {
        if (items == null) {
            System.out.println("countMatches: REJECTED - items array is null");
            return 0;
        }
        if (target == null) {
            System.out.println("countMatches: REJECTED - target is null");
            return 0;
        }
        int count = 0;
        for (int i = 0; i < items.length; i++) {
            System.out.println("  inspect index " + i + ": \"" + items[i] + "\"");
            if (target.equals(items[i])) {
                count++;
                System.out.println("     match #" + count);
            }
        }
        System.out.println("  -> total matches = " + count);
        return count;
    }

    // ---------- int overloads (numbers) ----------
    public static int findFirst(int[] items, int target) {
        if (items == null) {
            System.out.println("findFirst: REJECTED - items array is null");
            return -1;
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println("  inspect index " + i + ": " + items[i]);
            if (items[i] == target) {
                System.out.println("  -> match at index " + i);
                return i;
            }
        }
        System.out.println("  -> no match found (end of list)");
        return -1;
    }

    public static int countMatches(int[] items, int target) {
        if (items == null) {
            System.out.println("countMatches: REJECTED - items array is null");
            return 0;
        }
        int count = 0;
        for (int i = 0; i < items.length; i++) {
            System.out.println("  inspect index " + i + ": " + items[i]);
            if (items[i] == target) {
                count++;
                System.out.println("     match #" + count);
            }
        }
        System.out.println("  -> total matches = " + count);
        return count;
    }
}
