package university.course;

import university.course.Course;
import university.people.Student;

import java.util.List;
import java.util.Map;

public interface Evaluable {
    void evaluate(Map<Student, List<Integer>> assignmentGrades, Map<Student, Course.Grade> studentToGrade);
}
