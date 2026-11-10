import java.util.Arrays;
import java.util.List;

public class LabTestRunner {

    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name);
        }
    }

    private static int[] copyOf(int[] a) { return Arrays.copyOf(a, a.length); }

    public static void main(String[] args) {
        testEmptyInput();
        testSingleItem();
        testAlreadySorted();
        testReverseSorted();
        testDuplicates();
        testStableTies();
        testObjectSorts();
        testNullThrows();
        testInstrumentation();
        testStudentAuthoredAssertion();

        System.out.println();
        System.out.println("Results: " + passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }

    // ---------- arrays ----------

    private static void runArraySorts(int[] input, int[] expected) {
        // bubble
        int[] a = copyOf(input);
        BasicSorts.bubbleSort(a);
        check("bubbleSort " + Arrays.toString(input), Arrays.equals(a, expected));
        // selection
        a = copyOf(input);
        BasicSorts.selectionSort(a);
        check("selectionSort " + Arrays.toString(input), Arrays.equals(a, expected));
        // insertion
        a = copyOf(input);
        BasicSorts.insertionSort(a);
        check("insertionSort " + Arrays.toString(input), Arrays.equals(a, expected));
    }

    private static void testEmptyInput() {
        runArraySorts(new int[]{}, new int[]{});
    }

    private static void testSingleItem() {
        runArraySorts(new int[]{42}, new int[]{42});
    }

    private static void testAlreadySorted() {
        runArraySorts(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5});
    }

    private static void testReverseSorted() {
        runArraySorts(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5});
    }

    private static void testDuplicates() {
        runArraySorts(new int[]{3, 1, 2, 1, 3, 2}, new int[]{1, 1, 2, 2, 3, 3});
    }

    // ---------- stable ties (students) ----------

    private static void testStableTies() {
        // Equal grades must retain input order under insertion sort.
        Student[] input = new Student[] {
            new Student("Zed", 80),
            new Student("Amy", 80),
            new Student("Bob", 90)
        };
        Student[] expectedGrades = new Student[] {
            new Student("Zed", 80), // stable: Zed before Amy
            new Student("Amy", 80),
            new Student("Bob", 90)
        };
        // insertion sort by grade
        Student[] a = Arrays.copyOf(input, input.length);
        BasicSorts.insertionSortByGrade(a);
        check("insertionSort stable ties - length", a.length == expectedGrades.length);
        check("insertionSort stable ties - first 80 is Zed", a[0].name.equals("Zed") && a[0].grade == 80);
        check("insertionSort stable ties - second 80 is Amy", a[1].name.equals("Amy") && a[1].grade == 80);
        check("insertionSort stable ties - sorted", BasicSorts.isSortedByGrade(a));

        // selection sort is unstable: don't require stability, only sortedness
        a = Arrays.copyOf(input, input.length);
        BasicSorts.selectionSortByGrade(a);
        check("selectionSort ties sorted", BasicSorts.isSortedByGrade(a));
    }

    private static void testObjectSorts() {
        Student[] s = new Student[] {
            new Student("Charlie", 70),
            new Student("Alice", 90),
            new Student("Bob", 80)
        };
        // by grade
        Student[] a = Arrays.copyOf(s, s.length);
        BasicSorts.bubbleSortByGrade(a);
        check("bubbleSortByGrade sorted", BasicSorts.isSortedByGrade(a));
        a = Arrays.copyOf(s, s.length);
        BasicSorts.selectionSortByGrade(a);
        check("selectionSortByGrade sorted", BasicSorts.isSortedByGrade(a));
        a = Arrays.copyOf(s, s.length);
        BasicSorts.insertionSortByGrade(a);
        check("insertionSortByGrade sorted", BasicSorts.isSortedByGrade(a));

        // by name
        a = Arrays.copyOf(s, s.length);
        BasicSorts.bubbleSortByName(a);
        check("bubbleSortByName sorted", BasicSorts.isSortedByName(a));
        a = Arrays.copyOf(s, s.length);
        BasicSorts.selectionSortByName(a);
        check("selectionSortByName sorted", BasicSorts.isSortedByName(a));
        a = Arrays.copyOf(s, s.length);
        BasicSorts.insertionSortByName(a);
        check("insertionSortByName sorted", BasicSorts.isSortedByName(a));

        // aliases
        a = Arrays.copyOf(s, s.length);
        BasicSorts.sortStudentsByGrade(a);
        check("sortStudentsByGrade alias", BasicSorts.isSortedByGrade(a));

        // List overload
        List<Student> list = Arrays.asList(
            new Student("Z", 5), new Student("A", 1));
        BasicSorts.insertionSort(list);
        check("List overload sorted", list.get(0).grade == 1 && list.get(1).grade == 5);
    }

    private static void testNullThrows() {
        boolean thrown = false;
        try { BasicSorts.bubbleSort((int[]) null); } catch (IllegalArgumentException e) { thrown = true; }
        check("bubbleSort null throws", thrown);

        thrown = false;
        try { BasicSorts.selectionSort((int[]) null); } catch (IllegalArgumentException e) { thrown = true; }
        check("selectionSort null throws", thrown);

        thrown = false;
        try { BasicSorts.insertionSort((int[]) null); } catch (IllegalArgumentException e) { thrown = true; }
        check("insertionSort null throws", thrown);
    }

    private static void testInstrumentation() {
        int[] a = {5, 4, 3, 2, 1};
        BasicSorts.bubbleSort(a);
        check("bubble comparisons > 0", BasicSorts.comparisons > 0);
        check("bubble swaps > 0", BasicSorts.swaps > 0);

        a = new int[]{5, 4, 3, 2, 1};
        BasicSorts.insertionSort(a);
        check("insertion writes > 0", BasicSorts.writes > 0);

        // already sorted: optimized bubble should do n-1 comparisons, 0 swaps
        a = new int[]{1, 2, 3, 4, 5};
        BasicSorts.bubbleSort(a);
        check("bubble sorted swaps 0", BasicSorts.swaps == 0);
        check("bubble sorted comparisons 4", BasicSorts.comparisons == 4);
    }

    // Student-authored assertion (test-first checkpoint)
    private static void testStudentAuthoredAssertion() {
        // A single element is trivially sorted and no comparisons/swaps occur for selection? 
        // We assert insertion sort on a single element leaves it unchanged and counters zero comparisons.
        int[] a = {7};
        BasicSorts.insertionSort(a);
        check("student: single element unchanged", a[0] == 7);
        check("student: single element zero comparisons", BasicSorts.comparisons == 0);
    }
}
