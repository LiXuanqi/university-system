package people;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import university.Database;
import university.people.CourseManager;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseManagerTests {

    private Database db = Database.getInstance();


    @BeforeEach
    void setUp() {
        db.clearAll();
    }

    private boolean hasCourse(String name) {
        return db.getCourses().containsKey(name);
    }

    @Test
    void addCourseTest() {
        CourseManager manager = new CourseManager();
        manager.addCourse("CourseName1", 10, 4, "10:00 - 12:00", "ONLINE", "http://www.course.com");
        manager.addCourse("CourseName2", 15, 8, "1:00 - 3:00", "OFFLINE", "IoT Room");

        assertTrue(hasCourse("CourseName1"));
        assertTrue(hasCourse("CourseName2"));
        assertTrue(!hasCourse("CourseName3"));
    }

    @Test
    void deleteCourseTest() {
        CourseManager manager = new CourseManager();

        assertTrue(!hasCourse("CourseName1"));

        manager.addCourse("CourseName1", 10, 4, "10:00 - 12:00", "ONLINE", "http://www.course.com");
        manager.addCourse("CourseName2", 15, 8, "1:00 - 3:00", "OFFLINE", "IoT Room");

        assertTrue(hasCourse("CourseName1"));
        assertTrue(hasCourse("CourseName2"));

        manager.deleteCourse("CourseName1");

        assertTrue(!hasCourse("CourseName1"));

    }

    @Test
    void updateCourseTest() {
        CourseManager manager = new CourseManager();
        manager.addCourse("CourseName1", 10, 4, "10:00 - 12:00", "ONLINE", "http://www.course.com");
        assertTrue(hasCourse("CourseName1"));
        manager.updateCourseName("CourseName1", "NewCourse");
        assertTrue(!hasCourse("CourseName1"));
        assertTrue(hasCourse("NewCourse"));

    }

    @Test
    void browserCourseTest() {
        CourseManager manager = new CourseManager();
        manager.addCourse("CourseName1", 10, 4, "10:00 - 12:00", "ONLINE", "http://www.course.com");
        manager.addCourse("CourseName2", 15, 8, "1:00 - 3:00", "OFFLINE", "IoT Room");
        manager.browseCourse("CourseName1");
        manager.browseCourse("CourseName2");

    }
}
