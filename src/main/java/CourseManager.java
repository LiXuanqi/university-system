public class CourseManager {

    private Database db = Database.getInstance();

    private Course createOnlineCourse(String name, int capacity, int credit, String time, String url) {
        return new OnlineCourse(name, capacity, credit, time, url);
    }

    private Course createOfflineCourse(String name, int capacity, int credit, String time, String location) {
        return new OfflineCourse(name, capacity, credit, time, location);
    }

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

    public void updateCourse() {

    }

    public void browseCourse(String name) {
        Course course = db.findCourseByName(name);
        System.out.println(course);
    }


}
