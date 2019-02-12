package people;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import university.Database;
import university.course.Evaluable;
import university.people.CourseManager;
import university.people.Student;
import university.people.Teacher;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeacherTests {

    private Database db = Database.getInstance();

    Teacher teacher;

    @BeforeAll
    void setUp() {
        CourseManager manager = new CourseManager("Annie");
        manager.addCourse("CourseName1", 10, 4, "10:00 - 12:00", "ONLINE", "http://www.course.com");
        manager.addCourse("CourseName2", 15, 8, "1:00 - 3:00", "OFFLINE", "IoT Room");
    }

    @BeforeEach
    void setUpEach() {
        teacher = new Teacher("Lily");
        teacher.addTeachedCourse("CourseName1");
    }

    @Test
    void browseAssignmentTest() {
        teacher.browseMyAllAssignments();
    }

    @Test
    void createAssignment() {

        teacher.createAssignment("CourseName1", Database.ASSIGNMENTS_PATHS[0]);
        teacher.createAssignment("CourseName1", Database.ASSIGNMENTS_PATHS[1]);

        teacher.browseMyAllAssignments();

    }

    @Test
    void deleteAssignment() {
        teacher.createAssignment("CourseName1", Database.ASSIGNMENTS_PATHS[0]);
        teacher.createAssignment("CourseName1", Database.ASSIGNMENTS_PATHS[1]);

        teacher.browseMyAllAssignments();
        
        teacher.deleteAssignment("CourseName1", 0);
        teacher.browseMyAllAssignments();

    }

    @Test
    void gradeSubmission() {
        teacher.createAssignment("CourseName1", Database.ASSIGNMENTS_PATHS[0]);
        Student student = new Student("Jack", "CHINA");
        db.addStudent(student);
        student.addSubmissionToCourse("CourseName1", 0, Database.SUBMISSION_PATHS[0]);
        teacher.gradeSubmission("CourseName1", 0, "Jack", 80);
        student.browseSubmission("CourseName1", 0);
    }

    @Test
    void setEvaluationRuleTest() {
        teacher.setCourseEvaluationRule("CourseName1", "RANK");
        Evaluable rule = db.findCourseByName("CourseName1").getEvaluationRule();
        System.out.println(rule);
    }
}
