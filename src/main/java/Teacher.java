import javax.xml.crypto.Data;
import java.util.List;
import java.util.Set;

public class Teacher {

    private Database db = Database.getInstance();

    private Set<Course> teachedCourses;

    public void browseAssignmentsByCourse(String courseName) {
        Course course = db.findCourseByName(courseName);
        if (!isTeach(course)) {
            return;
        }
        for (Assignment assignment : course.getAssignments()) {
            // TODO: @Override toString() of Assignment.
            System.out.println(assignment);
        }
    }

    public void browseMyAllAssignments() {
        for (Course course : teachedCourses) {
            browseAssignmentsByCourse(course.getName());
        }
    }

    public void createAssignment(String courseName, String filepath) {

    }

    public void deleteAssignment(String courseName, int id) {
        Course course = db.findCourseByName(courseName);
        if (!isTeach(course)) {
            return;
        }
        course.deleteAssignment(id);
    }

    public void gradeSubmission(String courseName, int assignmentId, String studentName, int mark) {
        Course course = db.findCourseByName(courseName);
        Assignment assignment = course.getAssignmentById(assignmentId);
        Submission submission = assignment.getSubmissionByStudentName(studentName);
        submission.setGrade(mark);
    }
    public void setCourseEvaluationRule() {

    }

    private boolean isTeach(Course course) {
        return teachedCourses.contains(course);
    }
}
