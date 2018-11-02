//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import university.*;
//import university.course.OfflineCourse;
//import university.course.OnlineCourse;
//import university.people.CourseManager;
//import university.people.Student;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class FeatureTests {
//    private static Database db = Database.getInstance();
//
//    @BeforeAll
//    static void setUp() {
//
//        db.addStudent(new Student("Aaron"));
//        db.addStudent(new Student("Abe"));
//        db.addStudent(new Student("Wilkinson"));
//        db.addStudent(new Student("Mabel"));
//        db.addStudent(new Student("Idell"));
//        db.addStudent(new Student("Padgett"));
//        db.addStudent(new Student("Pansy"));
//        db.addStudent(new Student("Gabriel"));
//        db.addStudent(new Student("Galen"));
//        db.addStudent(new Student("Garfield"));
//
//        db.addCourse(new OnlineCourse("Web Application Development and Tools", 10, 4, "7:00 - 11:00 Monday", "http://web-application-awesome.com"));
//        db.addCourse(new OnlineCourse("BlockChain", 8, 8, "2:00 - 4:00 Friday", "http://blockchain-is-the-future.com"));
//
//        db.addCourse(new OfflineCourse("OfflineCourse1", 5, 4, "5:00 - 6:00 Monday", "Java Room"));
//        db.addCourse(new OfflineCourse("OfflineCourse2", 10, 4, "7:00 - 9:00 Monday", "Python Room"));
//
//    }
//
//    @Test
//    void addCourseTest() {
//        db.clearAll();
//        CourseManager courseManager = new CourseManager();
//        courseManager.addCourse("Web Application Development and Tools", 10, 4, "7:00 - 11:00 Monday","online", "http://web-application-awesome.com");
//        courseManager.addCourse("OfflineCourse1", 5, 4, "5:00 - 6:00 Monday","offline", "Java Room");
//        assertTrue(db.getCourses().size() == 2);
//    }
//}
