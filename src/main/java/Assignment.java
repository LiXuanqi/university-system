import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment {
    // TODO: change to thread safety.
    private int id;

    public int getId() {
        return id;
    }

    List<Question> questions;
    private List<Submission> submissions;

    public Submission getSubmissionByStudentName(String studentName) {
        for (Submission submission : submissions) {
            if (submission.getStudentName().equals(studentName)) {
                return submission;
            }
        }
        return null;
    }
}
