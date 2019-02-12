package university.people;

import university.Database;
import university.assignment.Answer;
import university.assignment.Assignment;
import university.assignment.Submission;
import university.course.Course;
import university.utils.FileUtil;

import java.util.*;

public class Student extends User {

    private Database db = Database.getInstance();

    private String country;
    private int creditSum; // should between 16 to 20 for each semester.
    private double tuition;
    private Set<Course> choosedCourses;

    public Student(String name, String country) {
        super(name);
        this.country = country;
        this.choosedCourses = new HashSet<>();
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public double getTuition() {
        return tuition;
    }

    public String getCountry() {
        return country;
    }

    public Set<Course> getChoosedCourses() {
        return choosedCourses;
    }

    public boolean isCreditSumValid() {
        return creditSum >= 16 && creditSum <= 20;
    }

    public char getGpa() {
        Random random = new Random();
        int val = random.nextInt(100);
        if (val >= 80) {
            return 'A';
        } else if (val >= 60) {
            return 'B';
        } else {
            return 'C';
        }
    }

    public void browseCourses() {
        db.getCourses().values().stream().forEach(x -> browseCourse(x.getName()));
    }

    public void browseCourse(String name) {
        Course course = db.findCourseByName(name);
        System.out.println(course);
    }

    public void viewAllSchedules() {
        for (Course course : db.getCourses().values()) {
            System.out.println(course);
        }
    }

    public void signupCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        if (course.isFull()) {
            return;
        }
        if (isCreditValidIfChoosedCourse(course) && course.addStudent(this.getName())) {
            choosedCourses.add(course);
            creditSum += course.getCREDIT();
            tuition += Course.getCreditRate() * course.getCREDIT();
            System.out.println(this.getName() + " registered course - " + courseName);
        }
    }

    private boolean isCreditValidIfChoosedCourse(Course course) {
        return creditSum + course.getCREDIT() <= 20;
    }

    public void dropCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        if (choosedCourses.contains(course) && course.deleteStudent(this.getName())) {
            choosedCourses.remove(course);
            creditSum -= course.getCREDIT();
            tuition -= Course.getCreditRate() * course.getCREDIT();
            System.out.println(this.getName() + " drop the course - " + courseName);
        }
    }

    public void addSubmissionToCourse(String courseName, int assignmentId, String filepath) {
        Course course = db.findCourseByName(courseName);
        Assignment assignment = course.getAssignmentById(assignmentId);
        if (assignment == null) {
            return;
        }
        Submission submission = new Submission(this.getName());
        List<Answer> answers = FileUtil.readAnswersFromFile(filepath);
        for (Answer answer : answers) {
            submission.addAnswer(answer);
        }
        assignment.addSubmission(submission);
        System.out.println(submission.getStudentName() + "'s submission uploaded.");
    }

    public void browseSubmission(String courseName, int assignmentId) {
        Course course = db.findCourseByName(courseName);
        if (course == null) {
            return;
        }
        Assignment assignment = course.getAssignmentById(assignmentId);
        if (assignment == null) {
            return;
        }
        Submission submission = assignment.getSubmissionByStudentName(this.getName());
        if (submission == null) {
            return;
        }
        System.out.println(submission);
    }

    public void deleteSubmission(String courseName, int assignmentId) {
        Course course = db.findCourseByName(courseName);
        Assignment assignment = course.getAssignmentById(assignmentId);

        if (assignment != null && assignment.deleteSubmissionByStudentName(this.getName())) {
            System.out.println("Assignment: " + assignment.getId() + " of " + courseName + " is deleted.");
        }
    }

    public void updateSubmission(String courseName, int assignmentId, String filepath) {
        deleteSubmission(courseName, assignmentId);
        addSubmissionToCourse(courseName, assignmentId, filepath);
    }

    public void viewTranscript() {
        System.out.println("StudentName: " + this.getName());
        for (Course course : choosedCourses) {
            String grade = getGradeByCourseName(course.getName());
            System.out.println(course.getName() + " - " + grade);
        }
    }

    private String getGradeByCourseName(String courseName) {
        Course course = db.findCourseByName(courseName);
        return course.getGradeByStudentName(getName());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(this.getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return "university.people.Student: " + this.getName();
    }
}
