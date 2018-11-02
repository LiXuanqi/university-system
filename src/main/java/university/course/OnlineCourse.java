package university.course;

public class OnlineCourse extends Course {
    private String url;

    @Override
    public String toString() {
        return super.toString() + " | " + url;
    }

    public OnlineCourse(String name, int capacity, int credit, String time, String url) {
        super(name, capacity, credit, time);
        this.url = url;
    }
}
