import java.util.List;
import java.util.Set;

public abstract class Course {

    private Database db = Database.getInstance();

    enum Grade {
        A, B, C;
    }
    private final String name;

    public int getCREDIT() {
        return CREDIT;
    }

    public String getName() {
        return name;
    }

    private final int CAPACITY; // the maximum number of course.
    private final int CREDIT;

    private static int creditRate;

    public static void setCreditRate(int creditRate) {
        Course.creditRate = creditRate;
    }

    private String time; // course schedule.

    private Set<Student> students;



    private List<Assignment> assignments;

    public Assignment getAssignmentById(int id) {
        for (Assignment assignment : assignments) {
            if (assignment.getId() == id) {
                return assignment;
            }
        }
        return null;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public Course(String name, int capacity, int credit, String time) {
        this.name = name;
        this.CAPACITY = capacity;
        this.CREDIT = credit;
        this.time = time;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void deleteAssignment(int id) {
        Assignment assignment = getAssignmentById(id);
        if (assignment != null) {
            assignments.remove(assignment);
        }
    }

    public boolean isFull() {
        return students.size() >= CAPACITY;
    }

    void addStudent(String studentName) {
        if (!isFull()) {
            Student student = db.findStudentByName(studentName);
            students.add(student);
        }
    }

    void deleteStudent(String studentName) {
        Student student = db.findStudentByName(studentName);
        students.remove(student);
    }


    @Override
    public String toString() {
        return name + " | " + time;
    }


}
