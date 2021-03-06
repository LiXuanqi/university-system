package university.people;

import university.assignment.Assignment;
import university.Database;
import university.assignment.Question;
import university.course.Course;
import university.utils.FileUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Teacher extends User {

    private Database db = Database.getInstance();

    private Set<Course> teachedCourses;

    public Teacher(String name) {
        super(name);
        this.teachedCourses = new HashSet<>();
    }

    public void addTeachedCourse(String name) {
        teachedCourses.add(db.findCourseByName(name));
    }

    public void browseAssignmentsByCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        if (!isTeach(course)) {
            return;
        }
        for (Assignment assignment : course.getAssignments()) {
            // TODO: @Override toString() of university.assignment.
            System.out.println(assignment);
        }
    }

    public void browseMyAllAssignments() {
        for (Course course : teachedCourses) {
            browseAssignmentsByCourse(course.getName());
        }
    }

    public void createAssignment(String courseName, String filepath) {
        Course course = db.findCourseByName(courseName);
        if (!isTeach(course)) {
            return;
        }
        List<Question> questions = FileUtil.readQuestionsFromFile(filepath);
        Assignment assignment = new Assignment();
        for (Question question : questions) {
            assignment.addQuestion(question);
        }
        course.addAssignment(assignment);
        System.out.println("Add assignment: " + assignment.getId() + " to " + courseName);
    }

    public void deleteAssignment(String courseName, int id) {
        Course course = db.findCourseByName(courseName);
        if (!isTeach(course)) {
            return;
        }
        if (course.deleteAssignment(id)) {
            System.out.println("Assignment: " + id + " is deleted.");
        }

    }

    public void gradeCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        course.grade();
    }

    public void gradeSubmission(String courseName, int assignmentId, String studentName, int mark) {
        Course course = db.findCourseByName(courseName);
        if (course.gradeSubmission(assignmentId, studentName, mark)) {
            System.out.println(studentName + " in class: " + courseName + "'s assignment: " + assignmentId + " get " + mark);
        }
    }

    public void setCourseEvaluationRule(String courseName, String evaluationType) {
        Course course = db.findCourseByName(courseName);
        if (course.setEvaluationRule(evaluationType)) {
            System.out.println(courseName + "'s evaluation rule is set to " + evaluationType);
        }
    }

    private boolean isTeach(Course course) {
        return teachedCourses.contains(course);
    }
}
