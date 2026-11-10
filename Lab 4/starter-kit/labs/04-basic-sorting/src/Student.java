/**
 * Student data used for the object-based sorting tasks.
 */
public class Student {
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
