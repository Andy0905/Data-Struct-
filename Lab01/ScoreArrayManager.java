/**
 * Lab 1 - Arrays and Complexity Basics
 * ScoreArrayManager maintains a FIXED-size int[] of quiz scores
 * and reports the cost (number of shifts) of each operation.
 *
 * Required methods:
 *   display()
 *   updateScore(int index, int value)
 *   insertAt(int index, int value)
 *   deleteAt(int index)
 */
public class ScoreArrayManager {

    private final int[] scores;   // fixed-size backing array
    private int size;             // number of ACTIVE (valid) scores
    private final int capacity;

    /** Create an empty manager with the given fixed capacity. */
    public ScoreArrayManager(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.scores = new int[capacity];
        this.size = 0;
    }

    /** Seed a manager from an array, treating the first activeSize entries as valid. */
    public ScoreArrayManager(int[] initial, int activeSize) {
        this(initial.length > 0 ? initial.length : 1);
        int n = Math.min(activeSize, initial.length);
        for (int i = 0; i < n; i++) {
            scores[i] = initial[i];
        }
        this.size = n;
    }

    public int size() { return size; }
    public int capacity() { return capacity; }

    /** Print every slot of the backing array (zeros are unused slots). */
    public void display() {
        System.out.print("Array (size=" + size + ", capacity=" + capacity + "): [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(scores[i]);
            if (i < capacity - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /** Read a score by position (O(1)). */
    public int getScore(int index) {
        if (!validIndex(index)) {
            System.out.println("getScore(" + index + "): REJECTED - invalid index (valid 0.." + (size - 1) + ")");
            return -1;
        }
        return scores[index];
    }

    /** Update an existing score in place (O(1)). */
    public boolean updateScore(int index, int value) {
        if (!validIndex(index)) {
            System.out.println("updateScore(" + index + ", " + value + "): REJECTED - invalid index (valid 0.." + (size - 1) + ")");
            return false;
        }
        int old = scores[index];
        scores[index] = value;
        System.out.println("updateScore(" + index + ", " + value + "): OK, " + old + " -> " + value
                + ". Cost: O(1) (1 write, 0 shifts).");
        return true;
    }

    /** Insert value at index, shifting later elements to the right (O(n)). */
    public boolean insertAt(int index, int value) {
        // Insertion point may be 0..size (appending at size is allowed).
        if (index < 0 || index > size) {
            System.out.println("insertAt(" + index + ", " + value + "): REJECTED - invalid index (valid 0.." + size + ")");
            return false;
        }
        if (size >= capacity) {
            System.out.println("insertAt(" + index + ", " + value + "): REJECTED - array is full "
                    + "(size=" + size + ", capacity=" + capacity + ")");
            return false;
        }
        int shifts = 0;
        // Shift elements from the end down to index, one step to the right.
        for (int i = size; i > index; i--) {
            scores[i] = scores[i - 1];
            shifts++;
        }
        scores[index] = value;
        size++;
        System.out.println("insertAt(" + index + ", " + value + "): OK. Shifts=" + shifts
                + ". Cost: O(n) (shifted " + shifts + " element(s)).");
        return true;
    }

    /** Delete the score at index, shifting later elements to the left (O(n)). */
    public boolean deleteAt(int index) {
        if (!validIndex(index)) {
            System.out.println("deleteAt(" + index + "): REJECTED - invalid index (valid 0.." + (size - 1) + ")");
            return false;
        }
        int removed = scores[index];
        int shifts = 0;
        for (int i = index; i < size - 1; i++) {
            scores[i] = scores[i + 1];
            shifts++;
        }
        size--;
        scores[size] = 0; // clear the now-unused slot
        System.out.println("deleteAt(" + index + "): OK, removed " + removed
                + ". Shifts=" + shifts + ". Cost: O(n) (shifted " + shifts + " element(s)).");
        return true;
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < size;
    }
}
