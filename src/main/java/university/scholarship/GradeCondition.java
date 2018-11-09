package university.scholarship;

import university.people.Student;

import java.util.HashSet;
import java.util.Set;

public class GradeCondition implements RewardCondition {

    private Set<Character> targetGrades;

    public GradeCondition() {
        targetGrades = new HashSet<>();
    }

    public void addTargetGrade(char grade) {
        targetGrades.add(grade);
    }

    public void deleteTargetGrade(char grade) {
        targetGrades.remove(grade);
    }

    @Override
    public boolean isMatched(Student student) {
        return targetGrades.contains(student.getGpa());
    }

    @Override
    public void addTarget(String target) {
        if (target.length() != 1) {
            return;
        }
        addTargetGrade(target.charAt(0));
    }

    @Override
    public void deleteTarget(String target) {
        if (target.length() != 1) {
            return;
        }
        deleteTargetGrade(target.charAt(0));
    }

    @Override
    public String toString() {
        return "GradeCondition{" +
                "targetGrades=" + targetGrades +
                '}';
    }
}
