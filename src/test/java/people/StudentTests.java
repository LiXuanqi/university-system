package people;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import university.Database;
import university.course.Course;
import university.people.CourseManager;
import university.people.Student;
import university.people.Teacher;
import university.utils.FileUtil;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTests {

    private Student student;
    private Database db = Database.getInstance();

    @BeforeAll
    void setUp() {
        CourseManager manager = new CourseManager();
        manager.addCourse("CourseName1", 10, 4, "10:00 - 12:00", "ONLINE", "http://www.course.com");
        manager.addCourse("CourseName2", 15, 8, "1:00 - 3:00", "OFFLINE", "IoT Room");
        Teacher teacher = new Teacher("Nancy");
        teacher.createAssignment("CourseName1", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment1");

    }

    @BeforeEach
    void setUpEach() {
        student = new Student("Jack", "CHINA");
        db.addStudent(student);
    }

    @Test
    void browseCourseTest() {
        student.browseCourse("CourseName1");
        student.browseCourse("CourseName2");
    }

    @Test
    void signupCourseTest() {
        assertTrue(student.getChoosedCourses().size() == 0);
        assertTrue(!isStudentInCourseList("CourseName1", "Jack"));

        student.signupCourse("CourseName1");

        assertTrue(isStudentInCourseList("CourseName1", "Jack"));
        assertTrue(hasChooseCourse(student.getChoosedCourses(), "CourseName1"));

        student.signupCourse("CourseName2");

        assertTrue(student.getChoosedCourses().size() == 2);

    }

    @Test
    void dropCourseTest() {
        student.signupCourse("CourseName1");
        assertTrue(isStudentInCourseList("CourseName1", "Jack"));
        student.dropCourse("CourseName1");
        assertTrue(!isStudentInCourseList("CourseName1", "Jack"));

    }

    @Test
    void viewScheduleTest() {
        student.viewAllSchedules();
    }

    @Test
    void viewTranscriptTest() {

    }

    @Test
    void browseSubmissionTest() {
        student.addSubmissionToCourse("CourseName1", 0, "/Users/lixuanqi/Github/university-system/src/main/resources/submission1");
        student.browseSubmission("CourseName1", 0);
    }

    @Test
    void createSubmissionTest() {
        student.addSubmissionToCourse("CourseName1", 0, "/Users/lixuanqi/Github/university-system/src/main/resources/submission1");
        student.browseSubmission("CourseName1", 0);

    }

    @Test
    void deleteSubmissionTest() {

    }

    @Test
    void updateSubmissionTest() {

    }

    private boolean isStudentInCourseList(String courseName, String studentName) {
        Course course = db.findCourseByName(courseName);
        Set<Student> students = course.getStudents();
        // FIXME: There is a bug.
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasChooseCourse(Set<Course> set, String name) {
        for (Course course : set) {
            if (course.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
