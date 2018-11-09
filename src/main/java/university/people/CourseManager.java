package university.people;

import university.Database;
import university.course.Course;
import university.course.OfflineCourse;
import university.course.OnlineCourse;

public class CourseManager extends User {

    private Database db = Database.getInstance();

    public CourseManager(String name) {
        super(name);
    }

    // PUBLIC METHODS

    public void addCourse(String name, int capacity, int credit, String time, String type, String source) {
        Course course = null;
        if (type.equalsIgnoreCase("ONLINE")) {
            course = createOnlineCourse(name, capacity, credit, time, source);
        } else if (type.equalsIgnoreCase("OFFLINE")) {
            course = createOfflineCourse(name, capacity, credit, time, source);
        }
        db.addCourse(course);
    }

    public void deleteCourse(String name) {
        db.deleteCourse(name);
    }

    public void updateCourseName(String courseName, String newName) {
        Course course = db.findCourseByName(courseName);
        course.setName(newName);
    }

    public void browseCourse(String name) {
        Course course = db.findCourseByName(name);
        System.out.println(course);

    }

    // PRIVATE METHODS

    private Course createOnlineCourse(String name, int capacity, int credit, String time, String url) {
        return new OnlineCourse(name, capacity, credit, time, url);
    }

    private Course createOfflineCourse(String name, int capacity, int credit, String time, String location) {
        return new OfflineCourse(name, capacity, credit, time, location);
    }

}
