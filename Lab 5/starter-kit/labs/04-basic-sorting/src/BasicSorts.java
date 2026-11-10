import java.util.Arrays;

/**
 * Basic sorting algorithms: Bubble, Selection, and Insertion.
 *
 * All sorts operate in place on arrays and return a {@link Metrics} object
 * describing how many comparisons, array writes and swaps were performed.
 *
 * Nothing here uses java.util.Arrays.sort or any other library sort.
 */
public class BasicSorts {

    /** Counters for one sort run. */
    public static final class Metrics {
        public long comparisons;
        public long writes;   // assignments to array slots
        public long swaps;    // number of swap operations

        public Metrics() { }

        public Metrics(long comparisons, long writes, long swaps) {
            this.comparisons = comparisons;
            this.writes = writes;
            this.swaps = swaps;
        }

        public void reset() {
            comparisons = 0;
            writes = 0;
            swaps = 0;
        }

        @Override
        public String toString() {
            return "comparisons=" + comparisons + ", writes=" + writes + ", swaps=" + swaps;
        }
    }

    /** Student record used for object-based sorting. */
    public static class Student {
        public String name;
        public int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return name + " (" + grade + ")";
        }
    }

    // ------------------------------------------------------------------ guards

    private static void requireArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array must not be null");
        }
    }

    private static void requireArray(Student[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array must not be null");
        }
    }

    private static void requireArray(String[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array must not be null");
        }
    }

    // ------------------------------------------------------------------ helpers

    private static void swap(int[] arr, int a, int b, Metrics m) {
        int tmp = arr[a];
        arr[a] = arr[b];
        m.writes++;
        arr[b] = tmp;
        m.writes++;
        m.swaps++;
    }

    private static void swap(Student[] arr, int a, int b, Metrics m) {
        Student tmp = arr[a];
        arr[a] = arr[b];
        m.writes++;
        arr[b] = tmp;
        m.writes++;
        m.swaps++;
    }

    private static void swap(String[] arr, int a, int b, Metrics m) {
        String tmp = arr[a];
        arr[a] = arr[b];
        m.writes++;
        arr[b] = tmp;
        m.writes++;
        m.swaps++;
    }

    private static int compareNames(String a, String b) {
        String left = (a == null) ? "" : a;
        String right = (b == null) ? "" : b;
        return left.compareTo(right);
    }

    // ------------------------------------------------------------------ arrays

    /** Bubble sort (ascending). Optimised: stops early if a pass makes no swap. */
    public static Metrics bubbleSort(int[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                m.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1, m);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return m;
    }

    /** Selection sort (ascending). */
    public static Metrics selectionSort(int[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex, m);
            }
        }
        return m;
    }

    /** Insertion sort (ascending). Stable. */
    public static Metrics insertionSort(int[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                m.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    m.writes++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            m.writes++;
        }
        return m;
    }

    // ------------------------------------------------------------- string arrays

    /** Selection sort for strings (alphabetical, ascending). */
    public static Metrics selectionSort(String[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (compareNames(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex, m);
            }
        }
        return m;
    }

    // ----------------------------------------------------------- student objects

    /** Bubble sort students by grade (ascending). Stable. */
    public static Metrics bubbleSortStudentsByGrade(Student[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                m.comparisons++;
                if (arr[j].grade > arr[j + 1].grade) {
                    swap(arr, j, j + 1, m);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return m;
    }

    /** Insertion sort students by grade (ascending). Stable: equal grades keep input order. */
    public static Metrics insertionSortStudentsByGrade(Student[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                m.comparisons++;
                if (arr[j].grade > key.grade) {
                    arr[j + 1] = arr[j];
                    m.writes++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            m.writes++;
        }
        return m;
    }

    /** Selection sort students by name (alphabetical, ascending). */
    public static Metrics selectionSortStudentsByName(Student[] arr) {
        requireArray(arr);
        Metrics m = new Metrics();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (compareNames(arr[j].name, arr[minIndex].name) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex, m);
            }
        }
        return m;
    }

    // ------------------------------------------------------------------ demo

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printStudents(Student[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] data = { 5, 3, 8, 4, 2 };
        System.out.println("Bubble sort demo");
        System.out.print("Before: ");
        printArray(data.clone());
        int[] a = data.clone();
        Metrics mb = bubbleSort(a);
        System.out.print("After:  ");
        printArray(a);
        System.out.println(mb);

        int[] b = data.clone();
        Metrics ms = selectionSort(b);
        System.out.println("Selection sort: " + Arrays.toString(b) + " | " + ms);

        int[] c = data.clone();
        Metrics mi = insertionSort(c);
        System.out.println("Insertion sort: " + Arrays.toString(c) + " | " + mi);

        Student[] students = {
            new Student("Charlie", 85),
            new Student("Alice", 92),
            new Student("Bob", 85)
        };
        System.out.println("Before students: " + Arrays.toString(students));
        insertionSortStudentsByGrade(students);
        System.out.println("After students (by grade, stable): " + Arrays.toString(students));
    }
}
