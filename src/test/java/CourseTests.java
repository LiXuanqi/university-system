import org.junit.jupiter.api.Test;
import university.course.OfflineCourse;
import university.course.OnlineCourse;

public class CourseTests {
    @Test
    void createOnlineCourseTest() {
        OnlineCourse onlineCourse = new OnlineCourse("Online1", 20, 4, "8th June - 30th Oct","http://my.neu.edu");
        System.out.println(onlineCourse);
    }

    @Test
    void createOfflineCourseTest() {
        OfflineCourse offlineCourse = new OfflineCourse("Online1", 20, 4, "8th June - 30th Oct","IDT");
        System.out.println(offlineCourse);
    }
}
