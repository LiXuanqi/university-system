package university.course;

import university.*;
import university.assignment.Assignment;
import university.assignment.Submission;
import university.people.Student;

import java.util.*;

public abstract class Course {

    private Database db = Database.getInstance();

    enum Grade {
        A, B, C;
    }

    private String name;

    private final int CAPACITY; // the maximum number of course.
    private final int CREDIT;

    private static int creditRate;

    private String time; // course schedule.

    private Set<Student> students;

    private Map<Student, List<Integer>> assignmentGrades;
    private Map<Student, Grade> studentToGrade;
    private Evaluable evaluationRule;

    private List<Assignment> assignments;

    public void setName(String name) {
        db.deleteCourse(this.name);
        this.name = name;
        db.addCourse(this);
    }

    public void setEvaluationRule(String evaluationType) {
        Evaluable evaluationRule = null;
        if (evaluationType.equalsIgnoreCase("RankBased")) {
            evaluationRule = new RankBasedEvaluation();
        } else {
            evaluationRule = new PointsBasedEvaLuation();
        }
        this.evaluationRule = evaluationRule;
    }

    public void grade() {
        evaluationRule.evaluate(assignmentGrades, studentToGrade);
    }

    public void gradeSubmission(int assignmentId, String studentName, int mark) {
        Assignment assignment = getAssignmentById(assignmentId);
        Submission submission = assignment.getSubmissionByStudentName(studentName);
        submission.setGrade(mark);

        Student student = db.findStudentByName(studentName);
        if (!assignmentGrades.containsKey(student)) {
            assignmentGrades.put(student, new ArrayList<Integer>());
        }
        assignmentGrades.get(student).add(mark);
    }

    public Assignment getAssignmentById(int id) {
        for (Assignment assignment : assignments) {
            if (assignment.getId() == id) {
                return assignment;
            }
        }
        return null;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public Course(String name, int capacity, int credit, String time) {
        this.name = name;
        this.CAPACITY = capacity;
        this.CREDIT = credit;
        this.time = time;
        students = new HashSet<>();
        assignmentGrades = new HashMap<>();
        assignments = new ArrayList<>();
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void deleteAssignment(int id) {
        Assignment assignment = getAssignmentById(id);
        if (assignment != null) {
            assignments.remove(assignment);
        }
    }

    public boolean isFull() {
        return students.size() >= CAPACITY;
    }

    public void addStudent(String studentName) {
        if (!isFull()) {
            Student student = db.findStudentByName(studentName);
            students.add(student);
        }
    }

    public void deleteStudent(String studentName) {
        Student student = db.findStudentByName(studentName);
        students.remove(student);
    }

    public static void setCreditRate(int creditRate) {
        Course.creditRate = creditRate;
    }

    public int getCREDIT() {
        return CREDIT;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " | " + time;
    }


}
