package university;

import university.course.Course;
import university.people.Student;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance = new Database();

    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.getName(), course);
    }

    public void deleteCourse(String name) {
        courses.remove(name);
    }

    public Course findCourseByName(String name) {
        return courses.get(name);
    }

    public void addStudent(Student student) {
        students.put(student.getName(), student);
    }

    public void deleteStudent(String name) {
        students.remove(name);
    }

    public Student findStudentByName(String name) {
        return students.get(name);
    }

    public void clearAll() {
        students.clear();
        courses.clear();
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    private Database() {};

    public static Database getInstance() {
        return instance;
    }
}
