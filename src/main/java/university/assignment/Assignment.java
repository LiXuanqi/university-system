package university.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment {
    private final int id;
    private static AtomicInteger count = new AtomicInteger();
    public int getId() {
        return id;
    }

    private List<Question> questions;
    private List<Submission> submissions;

    public Assignment() {
        this.questions = new ArrayList<>();
        this.submissions = new ArrayList<>();
        this.id = count.getAndIncrement();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addSubmission(Submission submission) {
        submissions.add(submission);
    }

    public void deleteSubmissionByStudentName(String studentName) {
        submissions.remove(getSubmissionByStudentName(studentName));
    }

    public Submission getSubmissionByStudentName(String studentName) {
        for (Submission submission : submissions) {
            if (submission.getStudentName().equals(studentName)) {
                return submission;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", questions=" + questions +
                '}';
    }
}
