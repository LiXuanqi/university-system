public class Submission {
    private String studentName;
    private String content;
    private int grade;

    public void setGrade(int grade) {
        if (grade < 0 || grade > 100) {
            return;
        }
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }
}
