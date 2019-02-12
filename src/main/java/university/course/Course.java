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


    public Course(String name, int capacity, int credit, String time) {
        this.name = name;
        this.CAPACITY = capacity;
        this.CREDIT = credit;
        this.time = time;
        students = new HashSet<>();
        assignmentGrades = new HashMap<>();
        assignments = new ArrayList<>();
        studentToGrade = new HashMap<>();
    }

    public static int getCreditRate() {
        return creditRate;
    }

    public String getGradeByStudentName(String studentName) {
        Student student = db.findStudentByName(studentName);;

        return studentToGrade.get(student).toString();
    }

    public void setName(String name) {
        db.deleteCourse(this.name);
        this.name = name;
        db.addCourse(this);
    }

    public Evaluable getEvaluationRule() {
        return evaluationRule;
    }

    public boolean setEvaluationRule(String evaluationType) {
        Evaluable evaluationRule = null;
        if (evaluationType.equalsIgnoreCase("RANK")) {
            evaluationRule = new RankBasedEvaluation();
        } else if (evaluationType.equalsIgnoreCase("POINT")) {
            evaluationRule = new PointsBasedEvaluation();
        }
        this.evaluationRule = evaluationRule;
        return true;
    }

    public void grade() {
        evaluationRule.evaluate(assignmentGrades, studentToGrade);
    }

    public boolean gradeSubmission(int assignmentId, String studentName, int mark) {
        Assignment assignment = getAssignmentById(assignmentId);
        if (assignment == null) {
            return false;
        }
        Submission submission = assignment.getSubmissionByStudentName(studentName);
        if (submission == null) {
            return false;
        }
        submission.setGrade(mark);

        Student student = db.findStudentByName(studentName);
        if (!assignmentGrades.containsKey(student)) {
            assignmentGrades.put(student, new ArrayList<Integer>());
        }
        assignmentGrades.get(student).add(mark);
        return true;
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


    public Set<Student> getStudents() {
        return students;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public boolean deleteAssignment(int id) {
        Assignment assignment = getAssignmentById(id);
        if (assignment != null) {
            return assignments.remove(assignment);
        }
        return false;
    }

    public boolean isFull() {
        return students.size() >= CAPACITY;
    }

    public boolean addStudent(String studentName) {
        if (!isFull()) {
            Student student = db.findStudentByName(studentName);
            students.add(student);
            return true;
        } else {
            System.out.println("Course: " + this.name + " is Full.");
            return false;
        }
    }

    public boolean deleteStudent(String studentName) {
        Student student = db.findStudentByName(studentName);
        if (students.contains(student)) {
            students.remove(student);
            return true;
        }
        return false;
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
