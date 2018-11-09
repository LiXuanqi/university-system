package university;

import university.course.Course;
import university.people.CourseManager;
import university.people.FinanceManager;
import university.people.Student;
import university.people.Teacher;
import university.scholarship.Scholarship;

import java.util.*;

public class Database {
    private static Database instance = new Database();

    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Scholarship> scholarships = new HashMap<>();
    private Map<String, Teacher> teachers = new HashMap<>();
    private Map<String, FinanceManager> financeManagers = new HashMap<>();
    private Map<String, CourseManager> courseManagers = new HashMap<>();

    private Map<Scholarship, Set<Student>> scholarshipToStudents = new HashMap<>();

    public Map<Scholarship, Set<Student>> getScholarshipToStudents() {
        return scholarshipToStudents;
    }

    public void attachScholarshipToStudent(Scholarship scholarship, Student student) {
        if (!scholarshipToStudents.containsKey(scholarship)) {
            scholarshipToStudents.put(scholarship, new HashSet<Student>());
        }
        scholarshipToStudents.get(scholarship).add(student);
    }

    public void deleteScholarshipFromStudent(Scholarship scholarship, Student student) {
        if (!scholarshipToStudents.containsKey(scholarship)) {
            return;
        }
        scholarshipToStudents.get(scholarship).remove(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(), teacher);
    }

    public Teacher findTeacherByName(String name) {
        return teachers.get(name);
    }

    public void addFinanceManager(FinanceManager financeManager) {
        financeManagers.put(financeManager.getName(), financeManager);
    }

    public FinanceManager findFinanceManagerByName(String name) {
        return financeManagers.get(name);
    }

    public void addCourseManager(CourseManager courseManager) {
        courseManagers.put(courseManager.getName(), courseManager);
    }

    public CourseManager findCourseManagerByName(String name) {
        return courseManagers.get(name);
    }

    public void addScholarship(Scholarship scholarship) {
        scholarships.put(scholarship.getName(), scholarship);
    }

    public void deleteScholarship(String name) {
        scholarships.remove(name);
    }

    public Scholarship findScholarshipByName(String name) {
        return scholarships.get(name);
    }

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
