package university.course;

import university.course.Course;
import university.course.Evaluable;
import university.people.Student;

import java.util.*;

public class RankBasedEvaluation implements Evaluable {
    private final static double A = 0.2;
    private final static double B = 0.4;

    private class StudentGrade {
        Student student;
        int grade;
        public StudentGrade(Student student, int grade) {
            this.student = student;
            this.grade = grade;
        }
    }

    @Override
    public void evaluate(Map<Student, List<Integer>> assignmentGrades, Map<Student, Course.Grade> studentToGrade) {
        List<StudentGrade> averageList = new ArrayList<>();
        for (Map.Entry<Student, List<Integer>> assignmentGrade : assignmentGrades.entrySet()) {
            averageList.add(new StudentGrade(assignmentGrade.getKey(), average(assignmentGrade.getValue())));
        }
        Collections.sort(averageList, new Comparator<StudentGrade>() {
            @Override
            public int compare(StudentGrade o1, StudentGrade o2) {
                return o1.grade > o2.grade ? -1 : 1;
            }
        });
        int total = assignmentGrades.size();
        int numA = (int) (total * A);
        int numB = (int) (total * B);
        for (int i = 0; i < averageList.size(); i++) {
            StudentGrade studentGrade = averageList.get(i);
            Student student = studentGrade.student;
            if (i <= numA) {
                studentToGrade.put(student, Course.Grade.A);
            } else if (i <= numB) {
                studentToGrade.put(student, Course.Grade.B);
            } else {
                studentToGrade.put(student, Course.Grade.C);
            }
        }
    }
    private int average(List<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
}
