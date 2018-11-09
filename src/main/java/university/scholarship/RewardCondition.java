package university.scholarship;

import university.people.Student;

public interface RewardCondition {
    boolean isMatched(Student student);

    void addTarget(String target);

    void deleteTarget(String target);

}
