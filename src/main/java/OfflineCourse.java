public class OfflineCourse extends Course {

    private String location;

    public OfflineCourse(String name, int capacity, int credit, String time, String location) {
        super(name, capacity, credit, time);
        this.location = location;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + location;
    }
}
