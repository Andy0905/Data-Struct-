import java.util.Arrays;

/**
 * Acceptance runner for Lab 4 - Basic Sorting.
 * Run with assertions enabled:  java -ea -cp out LabTestRunner
 */
public class LabTestRunner {

    static int passed = 0;
    static int failed = 0;

    static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name);
            throw new AssertionError("Assertion failed: " + name);
        }
    }

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) return false;
        }
        return true;
    }

    static int[] cloneOf(int[] a) {
        return a == null ? null : a.clone();
    }

    // --------------------------- empty input
    static void testEmpty() {
        int[] a = {};
        BasicSorts.Metrics mb = BasicSorts.bubbleSort(a);
        check("empty: bubble sorted", isSorted(a) && a.length == 0);
        check("empty: bubble metrics zero", mb.comparisons == 0 && mb.writes == 0 && mb.swaps == 0);

        int[] b = {};
        BasicSorts.Metrics ms = BasicSorts.selectionSort(b);
        check("empty: selection sorted", isSorted(b));
        check("empty: selection metrics zero", ms.comparisons == 0);

        int[] c = {};
        BasicSorts.Metrics mi = BasicSorts.insertionSort(c);
        check("empty: insertion sorted", isSorted(c));
        check("empty: insertion metrics zero", mi.comparisons == 0);
    }

    // --------------------------- single item
    static void testSingle() {
        int[] a = { 42 };
        BasicSorts.Metrics m = BasicSorts.bubbleSort(a);
        check("single: bubble unchanged", a[0] == 42);
        check("single: bubble metrics zero", m.comparisons == 0 && m.swaps == 0);

        int[] b = { 7 };
        BasicSorts.insertionSort(b);
        check("single: insertion unchanged", b[0] == 7);

        int[] c = { -3 };
        BasicSorts.selectionSort(c);
        check("single: selection unchanged", c[0] == -3);
    }

    // --------------------------- already sorted
    static void testAlreadySorted() {
        int[] base = { 1, 2, 3, 4, 5 };
        int[] a = base.clone();
        BasicSorts.Metrics mb = BasicSorts.bubbleSort(a);
        check("sorted: bubble result", Arrays.equals(a, base));
        check("sorted: bubble no swaps", mb.swaps == 0);
        check("sorted: bubble n-1 comparisons", mb.comparisons == base.length - 1);

        int[] b = base.clone();
        BasicSorts.Metrics ms = BasicSorts.selectionSort(b);
        check("sorted: selection result", Arrays.equals(b, base));
        check("sorted: selection no swaps", ms.swaps == 0);
        check("sorted: selection quadratic comparisons", ms.comparisons == (long) base.length * (base.length - 1) / 2);

        int[] c = base.clone();
        BasicSorts.Metrics mi = BasicSorts.insertionSort(c);
        check("sorted: insertion result", Arrays.equals(c, base));
        check("sorted: insertion n-1 comparisons", mi.comparisons == base.length - 1);
    }

    // --------------------------- reverse sorted
    static void testReverseSorted() {
        int[] base = { 5, 4, 3, 2, 1 };
        int[] a = base.clone();
        BasicSorts.Metrics mb = BasicSorts.bubbleSort(a);
        check("reverse: bubble sorted", isSorted(a));
        check("reverse: bubble max swaps", mb.swaps == (long) base.length * (base.length - 1) / 2);

        int[] b = base.clone();
        BasicSorts.selectionSort(b);
        check("reverse: selection sorted", isSorted(b));

        int[] c = base.clone();
        BasicSorts.Metrics mi = BasicSorts.insertionSort(c);
        check("reverse: insertion sorted", isSorted(c));
        check("reverse: insertion many comparisons", mi.comparisons == (long) base.length * (base.length - 1) / 2);
    }

    // --------------------------- duplicates
    static void testDuplicates() {
        int[] a = { 2, 1, 2, 1, 3, 3 };
        BasicSorts.bubbleSort(a);
        check("duplicates: bubble sorted", isSorted(a));

        int[] b = { 4, 4, 4, 4 };
        BasicSorts.selectionSort(b);
        check("duplicates: selection sorted", isSorted(b));

        int[] c = { 3, 1, 2, 1, 2, 3 };
        BasicSorts.insertionSort(c);
        check("duplicates: insertion sorted", isSorted(c));
    }

    // --------------------------- stable ties (insertion sort on students)
    static void testStableTies() {
        BasicSorts.Student[] s = {
            new BasicSorts.Student("Anna", 80),
            new BasicSorts.Student("Ben", 90),
            new BasicSorts.Student("Cara", 80),
            new BasicSorts.Student("Dan", 70)
        };
        BasicSorts.insertionSortStudentsByGrade(s);
        check("stable ties: grades ascending", s[0].grade == 70 && s[1].grade == 80 && s[2].grade == 80 && s[3].grade == 90);
        check("stable ties: equal grades keep input order", s[1].name.equals("Anna") && s[2].name.equals("Cara"));

        // bubble sort is also stable
        BasicSorts.Student[] s2 = {
            new BasicSorts.Student("X", 5),
            new BasicSorts.Student("Y", 5),
            new BasicSorts.Student("Z", 1)
        };
        BasicSorts.bubbleSortStudentsByGrade(s2);
        check("stable ties: bubble keeps order", s2[1].name.equals("X") && s2[2].name.equals("Y"));
    }

    // --------------------------- object sort by name
    static void testNameSort() {
        BasicSorts.Student[] s = {
            new BasicSorts.Student("Charlie", 10),
            new BasicSorts.Student("Alice", 20),
            new BasicSorts.Student("Bob", 30)
        };
        BasicSorts.selectionSortStudentsByName(s);
        check("name sort: alphabetical", s[0].name.equals("Alice") && s[1].name.equals("Bob") && s[2].name.equals("Charlie"));
    }

    // --------------------------- string arrays
    static void testStringArrays() {
        String[] names = { "Dave", "Alice", "Bob" };
        BasicSorts.selectionSort(names);
        check("string sort", names[0].equals("Alice") && names[1].equals("Bob") && names[2].equals("Dave"));
    }

    // --------------------------- null handling
    static void testNull() {
        boolean thrown = false;
        try { BasicSorts.bubbleSort(null); } catch (IllegalArgumentException e) { thrown = true; }
        check("null: bubble throws", thrown);

        thrown = false;
        try { BasicSorts.selectionSort((int[]) null); } catch (IllegalArgumentException e) { thrown = true; }
        check("null: selection throws", thrown);

        thrown = false;
        try { BasicSorts.insertionSort(null); } catch (IllegalArgumentException e) { thrown = true; }
        check("null: insertion throws", thrown);
    }

    // --------------------------- measured comparison (informational)
    static void benchmark() {
        System.out.println("\n--- measured comparison (n=100) ---");
        int n = 100;
        int[] sorted = new int[n];
        int[] reverse = new int[n];
        int[] dup = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i;
            reverse[i] = n - i;
            dup[i] = i % 5;
        }

        System.out.println("bubble sorted:   " + BasicSorts.bubbleSort(sorted.clone()));
        System.out.println("bubble reverse:  " + BasicSorts.bubbleSort(reverse.clone()));
        System.out.println("selection sorted: " + BasicSorts.selectionSort(sorted.clone()));
        System.out.println("selection reverse:" + BasicSorts.selectionSort(reverse.clone()));
        System.out.println("insertion sorted: " + BasicSorts.insertionSort(sorted.clone()));
        System.out.println("insertion reverse:" + BasicSorts.insertionSort(reverse.clone()));
        System.out.println("insertion dup:   " + BasicSorts.insertionSort(dup.clone()));
    }

    public static void main(String[] args) {
        System.out.println("=== Lab 4 Basic Sorting - Test Runner ===");
        try { testEmpty(); } catch (AssertionError e) { /* recorded */ }
        try { testSingle(); } catch (AssertionError e) { }
        try { testAlreadySorted(); } catch (AssertionError e) { }
        try { testReverseSorted(); } catch (AssertionError e) { }
        try { testDuplicates(); } catch (AssertionError e) { }
        try { testStableTies(); } catch (AssertionError e) { }
        try { testNameSort(); } catch (AssertionError e) { }
        try { testStringArrays(); } catch (AssertionError e) { }
        try { testNull(); } catch (AssertionError e) { }
        benchmark();

        System.out.println("\n=== Results: " + passed + " passed, " + failed + " failed ===");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
