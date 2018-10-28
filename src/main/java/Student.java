import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Student {

    private Database db = Database.getInstance();

    private String name;
    private int creditSum; // should between 16 to 20 for each semester.
    private Set<Course> choosedCourses;
    private Map<Course, Transcript> courseToTranscript;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void browseCourse(String name) {
        Course course = db.findCourseByName(name);
        System.out.println(course);
    }

    public void signupCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        if (course.isFull()) {
            return;
        }
        choosedCourses.add(course);
        course.addStudent(name);
        creditSum += course.getCREDIT();
    }

    public void dropCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        choosedCourses.remove(course);
        course.deleteStudent(name);
    }

    public void viewTranscript(String courseName) {
        System.out.println(getTranscript(courseName));
    }

    public void viewTranscripts() {
        for (Course course : courseToTranscript.keySet()) {
            viewTranscript(course.getName());
        }
    }

    private Transcript getTranscript(String courseName) {
        Course course = db.findCourseByName(courseName);
        return courseToTranscript.get(course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student: " + name;
    }
}
