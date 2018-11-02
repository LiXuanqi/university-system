package university.assignment;

import java.util.ArrayList;
import java.util.List;

public class Submission {
    private String studentName;
    private List<Answer> answers;
    private int grade;

    public Submission(String studentName) {
        this.studentName = studentName;
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void setGrade(int grade) {
        if (grade < 0 || grade > 100) {
            return;
        }
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "studentName='" + studentName + '\'' +
                ", answers=" + answers +
                ", grade=" + grade +
                '}';
    }
}
