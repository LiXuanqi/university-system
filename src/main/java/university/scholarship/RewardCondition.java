package university.scholarship;

import university.people.Student;

public interface RewardCondition {
    boolean isMatched(Student student);
}
