package university.course;

import university.course.Course;
import university.course.Evaluable;
import university.people.Student;

import java.util.List;
import java.util.Map;

public class PointsBasedEvaLuation implements Evaluable {
    private static final int A = 80;
    private static final int B = 60;


    @Override
    public void evaluate(Map<Student, List<Integer>> assignmentGrades, Map<Student, Course.Grade> studentToGrade) {
        for (Map.Entry<Student, List<Integer>> entry : assignmentGrades.entrySet()) {
            Course.Grade grade = calculateGrade(entry.getValue());
            studentToGrade.put(entry.getKey(), grade);
        }
    }

    private Course.Grade calculateGrade(List<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        int average = sum / grades.size();
        if (average >= A) {
            return Course.Grade.A;
        } else if (average >= B) {
            return Course.Grade.B;
        } else {
            return Course.Grade.C;
        }
    }

}
