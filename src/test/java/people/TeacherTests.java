package people;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import university.people.CourseManager;
import university.people.Teacher;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeacherTests {

    Teacher teacher;

    @BeforeAll
    void setUp() {
        CourseManager manager = new CourseManager();
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

        teacher.createAssignment("CourseName1", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment1");
        teacher.createAssignment("CourseName1", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment2");

        teacher.browseMyAllAssignments();

    }

    @Test
    void deleteAssignment() {
        teacher.createAssignment("CourseName1", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment1");
        teacher.createAssignment("CourseName1", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment2");

        teacher.browseMyAllAssignments();
        
        teacher.deleteAssignment("CourseName1", 0);
        teacher.browseMyAllAssignments();

    }

    @Test
    void gradeSubmission() {

    }
}
