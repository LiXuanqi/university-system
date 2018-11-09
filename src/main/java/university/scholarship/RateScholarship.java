package university.scholarship;

import university.Database;
import university.people.Student;

public class RateScholarship extends Scholarship {
    private Database db = Database.getInstance();

    private double rate = 0.08;

    public RateScholarship(String name) {
        super(name);
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void reward(String studentName) {
        Student student = db.findStudentByName(studentName);
        if (!isMatched(student)) {
            return;
        }
        double newTuition = student.getTuition() * (1 - rate);
        student.setTuition(newTuition);
    }

    public boolean isMatched(Student student) {
        return getRewardCondition().isMatched(student);
    }
}
