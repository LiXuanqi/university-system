package university.scholarship;

import university.Database;
import university.people.Student;

public class FixedScholarship extends Scholarship {

    private Database db = Database.getInstance();

    private int money = 2000;

    public FixedScholarship(String name) {
        super(name);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void reward(String studentName) {
        Student student = db.findStudentByName(studentName);
        if (!isMatched(student)) {
            return;
        }
        student.setTuition(student.getTuition() - money);
    }

    public boolean isMatched(Student student) {
        return getRewardCondition().isMatched(student);
    }
}
